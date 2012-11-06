package org.siemac.metamac.access.control.core.serviceapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.access.control.core.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.common.test.MetamacBaseTests;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.sso.client.MetamacPrincipalAccess;
import org.siemac.metamac.sso.client.SsoClientConstants;
import org.springframework.beans.factory.annotation.Value;

public abstract class AccessControlBaseTest extends MetamacBaseTests {

    protected static Long NOT_EXISTS = Long.valueOf(-1);

    protected static Long ROLE_1     = Long.valueOf(1);
    protected static Long ROLE_2     = Long.valueOf(2);

    protected static Long APP_1      = Long.valueOf(1);
    protected static Long APP_2      = Long.valueOf(2);

    protected static Long USER_1     = Long.valueOf(1);
    protected static Long USER_2     = Long.valueOf(2);

    protected static Long ACCESS_1   = Long.valueOf(1);
    protected static Long ACCESS_2   = Long.valueOf(2);
    protected static Long ACCESS_3   = Long.valueOf(3);

    @Value("${metamac.access.control.db.provider}")
    private String        databaseProvider;

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
    protected List<String> getTableNamesOrderedByFKDependency() {
        List<String> tables = new ArrayList<String>();
        tables.add("TB_SEQUENCES");
        tables.add("TB_USERS");
        tables.add("TB_ROLES");
        tables.add("TB_APPS");
        tables.add("TB_INTERNATIONAL_STRINGS");
        tables.add("TB_EXTERNAL_ITEMS");
        tables.add("TB_LOCALISED_STRINGS");
        tables.add("TB_ACCESS");
        return tables;
    }

    @Override
    protected Map<String, List<String>> getTablePrimaryKeys() {
        Map<String, List<String>> primaryKeys = new HashMap<String, List<String>>();
        primaryKeys.put("TB_SEQUENCES", Arrays.asList("SEQUENCE_NAME"));
        return primaryKeys;
    }

    @Override
    protected DataBaseProvider getDatabaseProvider() {
        return DataBaseProvider.valueOf(databaseProvider);
    }

}