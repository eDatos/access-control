package org.siemac.metamac.access.control.web.client.utils;

import org.siemac.metamac.access.control.security.shared.SharedSecurityUtils;
import org.siemac.metamac.access.control.web.client.AccessControlWeb;
import org.siemac.metamac.domain.access.control.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.sso.client.MetamacPrincipal;

public class ClientSecurityUtils {

    /**
     * Checks if logged user has one of the allowed roles
     * 
     * @param roles
     * @return
     */
    private static boolean isRoleAllowed(AccessControlRoleEnum... roles) {
        MetamacPrincipal userPrincipal = AccessControlWeb.getCurrentUser();
        // Administration has total control
        if (SharedSecurityUtils.isAdministrador(userPrincipal)) {
            return true;
        }
        // Checks user has any role of requested
        if (roles != null) {
            for (int i = 0; i < roles.length; i++) {
                AccessControlRoleEnum role = roles[i];
                if (SharedSecurityUtils.isUserInRol(userPrincipal, role)) {
                    return true;
                }
            }
        }
        return false;
    }

}
