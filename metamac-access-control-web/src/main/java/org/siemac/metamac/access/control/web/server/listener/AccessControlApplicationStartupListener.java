package org.siemac.metamac.access.control.web.server.listener;

import org.siemac.metamac.access.control.constants.AccessControlConfigurationConstants;
import org.siemac.metamac.core.common.constants.shared.ConfigurationConstants;
import org.siemac.metamac.web.common.server.listener.InternalApplicationStartupListener;

public class AccessControlApplicationStartupListener extends InternalApplicationStartupListener {

    @Override
    public void checkDatasourceProperties() {
        checkRequiredProperty(AccessControlConfigurationConstants.DB_DRIVER_NAME);
        checkRequiredProperty(AccessControlConfigurationConstants.DB_URL);
        checkRequiredProperty(AccessControlConfigurationConstants.DB_USERNAME);
        checkRequiredProperty(AccessControlConfigurationConstants.DB_PASSWORD);
        checkRequiredProperty(AccessControlConfigurationConstants.DB_DIALECT);
    }

    @Override
    public void checkWebApplicationsProperties() {
        checkRequiredProperty(ConfigurationConstants.WEB_APPLICATION_STATISTICAL_OPERATIONS_INTERNAL_WEB);
    }

    @Override
    public void checkApiProperties() {
        checkRequiredProperty(ConfigurationConstants.ENDPOINT_STATISTICAL_OPERATIONS_INTERNAL_API);
        checkRequiredProperty(ConfigurationConstants.ENDPOINT_ACCESS_CONTROL_INTERNAL_API);
    }

    @Override
    public void checkOtherModuleProperties() {
        checkRequiredProperty(AccessControlConfigurationConstants.USER_GUIDE_FILE_NAME);
    }

    @Override
    public String projectName() {
        return "access-control-internal";
    }
}
