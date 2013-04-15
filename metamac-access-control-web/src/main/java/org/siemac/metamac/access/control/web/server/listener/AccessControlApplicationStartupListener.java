package org.siemac.metamac.access.control.web.server.listener;

import org.siemac.metamac.access.control.constants.AccessControlConfigurationConstants;
import org.siemac.metamac.core.common.constants.shared.ConfigurationConstants;
import org.siemac.metamac.web.common.server.listener.ApplicationStartupListener;

public class AccessControlApplicationStartupListener extends ApplicationStartupListener {

    @Override
    public void checkConfiguration() {

        // SECURITY

        checkSecurityProperties();

        // DATASOURCE

        checkRequiredProperty(AccessControlConfigurationConstants.DB_DRIVER_NAME);
        checkRequiredProperty(AccessControlConfigurationConstants.DB_URL);
        checkRequiredProperty(AccessControlConfigurationConstants.DB_USERNAME);
        checkRequiredProperty(AccessControlConfigurationConstants.DB_PASSWORD);
        checkRequiredProperty(AccessControlConfigurationConstants.DB_DIALECT);

        // OTHER CONFIGURATION PROPERTIES

        // Common properties

        checkEditionLanguagesProperty();
        checkNavBarUrlProperty();
        checkOrganisationProperty();

        // API

        checkRequiredProperty(ConfigurationConstants.ENDPOINT_STATISTICAL_OPERATIONS_INTERNAL_API);

        // Access Control properties

        checkRequiredProperty(AccessControlConfigurationConstants.USER_GUIDE_FILE_NAME);
    }
}
