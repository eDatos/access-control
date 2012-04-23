package org.siemac.metamac.access.control.base.serviceapi;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.common.test.MetamacBaseTests;
import org.siemac.metamac.domain.access.control.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.sso.client.MetamacPrincipalAccess;
import org.siemac.metamac.sso.client.SsoClientConstants;

public abstract class AccessControlBaseTest extends MetamacBaseTests {
    
    protected static Long                      NOT_EXISTS     = Long.valueOf(-1);

    protected static Long                      ROLE_1         = Long.valueOf(1);
    protected static Long                      ROLE_2         = Long.valueOf(2);

    protected static Long                      APP_1          = Long.valueOf(1);
    protected static Long                      APP_2          = Long.valueOf(2);

    protected static Long                      USER_1         = Long.valueOf(1);
    protected static Long                      USER_2         = Long.valueOf(2);

    protected static Long                      ACCESS_1       = Long.valueOf(1);
    protected static Long                      ACCESS_2       = Long.valueOf(2);
    protected static Long                      ACCESS_3       = Long.valueOf(3);

    // --------------------------------------------------------------------------------------------------------------
    // SERVICE CONTEXT
    // --------------------------------------------------------------------------------------------------------------

    @Override
    protected ServiceContext getServiceContextAdministrador() {
        ServiceContext serviceContext = super.getServiceContextWithoutPrincipal();
        putMetamacPrincipalInServiceContext(serviceContext, AccessControlRoleEnum.ADMINISTRADOR);
        return serviceContext;
    }

    protected ServiceContext getServiceContextTecnicoPlanificacion() {
        ServiceContext serviceContext = super.getServiceContextWithoutPrincipal();
        putMetamacPrincipalInServiceContext(serviceContext, AccessControlRoleEnum.TECNICO_PLANIFICACION);
        return serviceContext;
    }

    private void putMetamacPrincipalInServiceContext(ServiceContext serviceContext, AccessControlRoleEnum role) {
        MetamacPrincipal metamacPrincipal = new MetamacPrincipal();
        metamacPrincipal.setUserId(serviceContext.getUserId());
        metamacPrincipal.getAccesses().add(new MetamacPrincipalAccess(role.getName(), AccessControlConstants.SECURITY_APPLICATION_ID, null));
        serviceContext.setProperty(SsoClientConstants.PRINCIPAL_ATTRIBUTE, metamacPrincipal);
    }

    // --------------------------------------------------------------------------------------------------------------
    // DBUNIT CONFIGURATION
    // --------------------------------------------------------------------------------------------------------------

    @Override
    protected String getDataSetFile() {
        return "dbunit/AccessControlBaseServiceTest.xml";
    }

    @Override
    protected List<String> getTablesToRemoveContent() {
        List<String> tables = new ArrayList<String>();
        tables.add("TB_ACCESS");
        tables.add("TB_APPS");
        tables.add("TB_ROLES");
        tables.add("TB_USERS");
        return tables;
    }

    @Override
    protected List<String> getSequencesToRestart() {
        List<String> sequences = new ArrayList<String>();
        sequences.add("SEQ_ROLES");
        sequences.add("SEQ_APPS");
        sequences.add("SEQ_USERS");
        sequences.add("SEQ_ACCESS");
        return sequences;
    }

}
