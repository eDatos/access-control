package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.web.server.ServiceContextHolder;
import org.siemac.metamac.access.control.web.shared.CloseSessionAction;
import org.siemac.metamac.access.control.web.shared.CloseSessionResult;
import org.siemac.metamac.core.common.conf.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class CloseSessionActionHandler extends AbstractActionHandler<CloseSessionAction, CloseSessionResult> {

    private static String        PROP_CAS_SERVICE_LOGOUT_URL = "metamac.security.casServiceLogoutUrl";

    @Autowired
    private ConfigurationService configurationService        = null;

    public CloseSessionActionHandler() {
        super(CloseSessionAction.class);
    }

    @Override
    public CloseSessionResult execute(CloseSessionAction action, ExecutionContext context) throws ActionException {
        String casServiceLogoutUrl = configurationService.getConfig().getString(PROP_CAS_SERVICE_LOGOUT_URL);
        ServiceContextHolder.getCurrentRequest().getSession(false).invalidate();
        return new CloseSessionResult(casServiceLogoutUrl);
    }

    @Override
    public void undo(CloseSessionAction action, CloseSessionResult result, ExecutionContext context) throws ActionException {

    }

}
