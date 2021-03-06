package org.siemac.metamac.access.control.core.conf;

import org.siemac.metamac.access.control.constants.AccessControlConfigurationConstants;
import org.siemac.metamac.core.common.conf.ConfigurationServiceImpl;
import org.siemac.metamac.core.common.exception.MetamacException;

public class AccessControlConfigurationServiceImpl extends ConfigurationServiceImpl implements AccessControlConfigurationService {

    @Override
    public String retrieveHelpUrl() throws MetamacException {
        return retrieveProperty(AccessControlConfigurationConstants.HELP_URL);
    }

    @Override
    public String retrieveDocsPath() throws MetamacException {
        return retrieveProperty(AccessControlConfigurationConstants.DOCS_PATH);
    }
}
