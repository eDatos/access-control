package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContextStore;
import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.access.control.web.shared.FindAllAppsAction;
import org.siemac.metamac.access.control.web.shared.FindAllAppsResult;
import org.siemac.metamac.access.control.web.shared.exception.MetamacWebException;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;


public class FindAllAppsActionHandler extends AbstractActionHandler<FindAllAppsAction, FindAllAppsResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    
    public FindAllAppsActionHandler() {
        super(FindAllAppsAction.class);
    }

    @Override
    public FindAllAppsResult execute(FindAllAppsAction action, ExecutionContext context) throws ActionException {
        try {
            List<AppDto> appDtos = accessControlBaseServiceFacade.findAllApps(ServiceContextStore.get());
            return new FindAllAppsResult(appDtos);
        } catch (MetamacException e) {
            throw new MetamacWebException(e.getExceptionItems());
        }
    }

    @Override
    public void undo(FindAllAppsAction action, FindAllAppsResult result, ExecutionContext context) throws ActionException {
        
    }
    
}
