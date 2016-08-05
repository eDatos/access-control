package org.siemac.metamac.access.control.security.shared;

import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.access.control.core.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.sso.client.MetamacPrincipalAccess;

public class SharedSecurityUtils {

    /**
     * Checks user has requested role
     */
    public static boolean isUserInRol(MetamacPrincipal metamacPrincipal, AccessControlRoleEnum role) {

        if (AccessControlRoleEnum.ANY_ROLE_ALLOWED.equals(role)) {
            return isAnyAccessControlRole(metamacPrincipal);
        } else {
            return isRoleInAccesses(metamacPrincipal, role);
        }
    }

    public static Boolean isAdministrador(MetamacPrincipal metamacPrincipal) {
        return isRoleInAccesses(metamacPrincipal, AccessControlRoleEnum.ADMINISTRADOR);
    }

    /**
     * Checks if user has access with role
     */
    public static Boolean isRoleInAccesses(MetamacPrincipal metamacPrincipal, AccessControlRoleEnum role) {
        for (MetamacPrincipalAccess metamacPrincipalAccess : metamacPrincipal.getAccesses()) {
            if (AccessControlConstants.APPLICATION_ID.equals(metamacPrincipalAccess.getApplication()) && metamacPrincipalAccess.getRole().equals(role.name())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static Boolean isAnyAccessControlRole(MetamacPrincipal metamacPrincipal) {
        return isAdministrador(metamacPrincipal) || isRoleInAccesses(metamacPrincipal, AccessControlRoleEnum.TECNICO_PLANIFICACION);
    }

}
