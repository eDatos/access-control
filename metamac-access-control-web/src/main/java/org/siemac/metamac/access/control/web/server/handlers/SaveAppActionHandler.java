package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.server.utils.WebExceptionUtils;
import org.siemac.metamac.access.control.web.shared.SaveAppAction;
import org.siemac.metamac.access.control.web.shared.SaveAppResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;


public class SaveAppActionHandler extends AbstractActionHandler<SaveAppAction, SaveAppResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    
    public SaveAppActionHandler() {
        super(SaveAppAction.class);
    }

    @Override
    public SaveAppResult execute(SaveAppAction action, ExecutionContext context) throws ActionException {
        AppDto appToSave = action.getAppToSave();
        try {
            AppDto appDto = null;
            if (appToSave.getId() == null) {
                appDto = accessControlBaseServiceFacade.createApp(ServiceContextHelper.getServiceContext(), appToSave);
            } else {
                appDto = accessControlBaseServiceFacade.updateApp(ServiceContextHelper.getServiceContext(), appToSave);
            }
            return new SaveAppResult(appDto);
        } catch (MetamacException e) {
            throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
        }
    }


    @Override
    public void undo(SaveAppAction action, SaveAppResult result, ExecutionContext context) throws ActionException {
        
    }
    
}