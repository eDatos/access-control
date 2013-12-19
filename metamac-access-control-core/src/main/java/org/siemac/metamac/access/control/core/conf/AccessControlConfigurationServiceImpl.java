package org.siemac.metamac.access.control.core.conf;

import org.siemac.metamac.access.control.constants.AccessControlConfigurationConstants;
import org.siemac.metamac.core.common.conf.ConfigurationServiceImpl;
import org.siemac.metamac.core.common.exception.MetamacException;

public class AccessControlConfigurationServiceImpl extends ConfigurationServiceImpl implements AccessControlConfigurationService {

    @Override
    public String retrieveUserGuideFileName() throws MetamacException {
        return retrieveProperty(AccessControlConfigurationConstants.USER_GUIDE_FILE_NAME, Boolean.TRUE);
    }

}
