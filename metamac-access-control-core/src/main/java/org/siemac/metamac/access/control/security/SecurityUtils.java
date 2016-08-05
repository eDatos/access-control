package org.siemac.metamac.access.control.security;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.core.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
import org.siemac.metamac.access.control.security.shared.SharedSecurityUtils;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.sso.client.SsoClientConstants;

public class SecurityUtils extends SharedSecurityUtils {

    /**
     * Checks user can execute any operation, if has any role of requested roles
     */
    public static void checkServiceOperationAllowed(ServiceContext ctx, AccessControlRoleEnum... roles) throws MetamacException {

        MetamacPrincipal metamacPrincipal = getMetamacPrincipal(ctx);

        // Administration has total control
        if (isAdministrador(metamacPrincipal)) {
            return;
        }
        // Checks user has any role of requested
        if (roles != null) {
            for (int i = 0; i < roles.length; i++) {
                AccessControlRoleEnum role = roles[i];
                if (isUserInRol(metamacPrincipal, role)) {
                    return;
                }
            }
        }
        throw new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, metamacPrincipal.getUserId());
    }

    /**
     * Retrieves MetamacPrincipal in ServiceContext
     */
    private static MetamacPrincipal getMetamacPrincipal(ServiceContext ctx) throws MetamacException {
        Object principalProperty = ctx.getProperty(SsoClientConstants.PRINCIPAL_ATTRIBUTE);
        if (principalProperty == null) {
            throw new MetamacException(ServiceExceptionType.SECURITY_PRINCIPAL_NOT_FOUND);
        }
        MetamacPrincipal metamacPrincipal = (MetamacPrincipal) principalProperty;
        if (!metamacPrincipal.getUserId().equals(ctx.getUserId())) {
            throw new MetamacException(ServiceExceptionType.SECURITY_PRINCIPAL_NOT_FOUND);
        }
        return metamacPrincipal;
    }

}