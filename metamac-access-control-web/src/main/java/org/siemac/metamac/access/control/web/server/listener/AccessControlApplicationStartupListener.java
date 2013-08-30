package org.siemac.metamac.access.control.web.server.listener;

import org.siemac.metamac.access.control.constants.AccessControlConfigurationConstants; 
import org.siemac.metamac.core.common.constants.shared.ConfigurationConstants;
import org.siemac.metamac.web.common.server.listener.ApplicationStartupListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AccessControlApplicationStartupListener extends ApplicationStartupListener {

    private static final Log     LOG = LogFactory.getLog(AccessControlApplicationStartupListener.class);
    
    @Override
    public void checkConfiguration() {

        LOG.info("****************************************************************");
        LOG.info("[metamac-access-control-web] Checking application configuration");
        LOG.info("****************************************************************");

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

        // WEB APPLICATIONS

        checkRequiredProperty(ConfigurationConstants.WEB_APPLICATION_STATISTICAL_OPERATIONS_INTERNAL_WEB);

        // API

        checkRequiredProperty(ConfigurationConstants.ENDPOINT_STATISTICAL_OPERATIONS_INTERNAL_API);

        // Access Control properties

        checkRequiredProperty(AccessControlConfigurationConstants.USER_GUIDE_FILE_NAME);
        
        LOG.info("****************************************************************");
        LOG.info("[metamac-access-control-web] Application configuration checked");
        LOG.info("****************************************************************");
    }
}
