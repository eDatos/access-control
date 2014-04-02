package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.core.conf.AccessControlConfigurationService;
import org.siemac.metamac.access.control.web.client.constants.AccessControlWebConstants;
import org.siemac.metamac.access.control.web.shared.GetUserGuideUrlAction;
import org.siemac.metamac.access.control.web.shared.GetUserGuideUrlResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class GetUserGuideUrlActionHandler extends SecurityActionHandler<GetUserGuideUrlAction, GetUserGuideUrlResult> {

    @Autowired
    private AccessControlConfigurationService configurationService;

    public GetUserGuideUrlActionHandler() {
        super(GetUserGuideUrlAction.class);
    }

    @Override
    public GetUserGuideUrlResult executeSecurityAction(GetUserGuideUrlAction action) throws ActionException {
        try {
            String docsPath = configurationService.retrieveDocsPath();
            String userGuideFileName = configurationService.retrieveUserGuideFileName();
            return new GetUserGuideUrlResult(docsPath + "/" + userGuideFileName);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }
}
