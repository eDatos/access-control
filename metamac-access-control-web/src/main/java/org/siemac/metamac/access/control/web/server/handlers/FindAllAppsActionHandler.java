package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.FindAllAppsAction;
import org.siemac.metamac.access.control.web.shared.FindAllAppsResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class FindAllAppsActionHandler extends SecurityActionHandler<FindAllAppsAction, FindAllAppsResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public FindAllAppsActionHandler() {
        super(FindAllAppsAction.class);
    }

    @Override
    public FindAllAppsResult executeSecurityAction(FindAllAppsAction action) throws ActionException {
        try {
            List<AppDto> appDtos = accessControlBaseServiceFacade.findAllApps(ServiceContextHolder.getCurrentServiceContext());
            return new FindAllAppsResult(appDtos);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(FindAllAppsAction action, FindAllAppsResult result, ExecutionContext context) throws ActionException {

    }

}
