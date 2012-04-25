package org.siemac.metamac.access.control.web.server;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.domain.access.control.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.sso.client.MetamacPrincipalAccess;
import org.siemac.metamac.sso.client.SsoClientConstants;

public class ServiceContextHelper {

    private static ServiceContext serviceContext;

    public static ServiceContext getServiceContext() {
        if (serviceContext == null) {

            serviceContext = new ServiceContext("user", "12345", AccessControlConstants.SECURITY_APPLICATION_ID);

            MetamacPrincipal metamacPrincipal = new MetamacPrincipal();
            metamacPrincipal.setUserId(serviceContext.getUserId());
            metamacPrincipal.getAccesses().add(new MetamacPrincipalAccess(AccessControlRoleEnum.ADMINISTRADOR.getName(), AccessControlConstants.SECURITY_APPLICATION_ID, null));
            serviceContext.setProperty(SsoClientConstants.PRINCIPAL_ATTRIBUTE, metamacPrincipal);

        }
        return serviceContext;
    }

}
