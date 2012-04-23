package org.siemac.metamac.access.control.security;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.sso.client.MetamacPrincipalAccess;
import org.siemac.metamac.sso.client.SsoClientConstants;

public class SecurityUtils {

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
     * Checks user has requested rol
     */
    private static boolean isUserInRol(MetamacPrincipal metamacPrincipal, AccessControlRoleEnum role) throws MetamacException {

        if (AccessControlRoleEnum.ANY_ROLE_ALLOWED.equals(role)) {
            return isAnyAccessControlRole(metamacPrincipal);
        } else {
            return isRoleInAccesses(metamacPrincipal, role);
        }
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

    /**
     * Checks if user has access with role
     */
    private static Boolean isRoleInAccesses(MetamacPrincipal metamacPrincipal, AccessControlRoleEnum role) {
        for (MetamacPrincipalAccess metamacPrincipalAccess : metamacPrincipal.getAccesses()) {
            if (AccessControlConstants.SECURITY_APPLICATION_ID.equals(metamacPrincipalAccess.getApplication()) && metamacPrincipalAccess.getRole().equals(role.name())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private static Boolean isAnyAccessControlRole(MetamacPrincipal metamacPrincipal) {
        return isAdministrador(metamacPrincipal) || isRoleInAccesses(metamacPrincipal, AccessControlRoleEnum.TECNICO_PLANIFICACION);
    }

    private static Boolean isAdministrador(MetamacPrincipal metamacPrincipal) {
        return isRoleInAccesses(metamacPrincipal, AccessControlRoleEnum.ADMINISTRADOR);
    }

}