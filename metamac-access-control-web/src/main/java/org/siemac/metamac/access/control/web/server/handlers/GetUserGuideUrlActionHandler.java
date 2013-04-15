package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.constants.AccessControlConfigurationConstants;
import org.siemac.metamac.access.control.web.client.constants.AccessControlWebConstants;
import org.siemac.metamac.access.control.web.shared.GetUserGuideUrlAction;
import org.siemac.metamac.access.control.web.shared.GetUserGuideUrlResult;
import org.siemac.metamac.core.common.conf.ConfigurationService;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class GetUserGuideUrlActionHandler extends SecurityActionHandler<GetUserGuideUrlAction, GetUserGuideUrlResult> {

    @Autowired
    private ConfigurationService configurationService = null;

    public GetUserGuideUrlActionHandler() {
        super(GetUserGuideUrlAction.class);
    }

    @Override
    public GetUserGuideUrlResult executeSecurityAction(GetUserGuideUrlAction action) throws ActionException {
        String dataUrl = configurationService.getConfig().getString(AccessControlWebConstants.ENVIRONMENT_DATA_URL);
        String userGuideFileName = configurationService.getConfig().getString(AccessControlConfigurationConstants.USER_GUIDE_FILE_NAME);
        return new GetUserGuideUrlResult(dataUrl + "/access-control/docs/" + userGuideFileName);
    }
}
