package org.siemac.metamac.access.control.core.conf;

import org.siemac.metamac.core.common.conf.ConfigurationService;
import org.siemac.metamac.core.common.exception.MetamacException;

public interface AccessControlConfigurationService extends ConfigurationService {

    public String retrieveUserGuideFileName() throws MetamacException;

    public String retrieveDocsPath() throws MetamacException;
}
