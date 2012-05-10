package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.ServiceContextHolder;
import org.siemac.metamac.access.control.web.shared.SaveAppAction;
import org.siemac.metamac.access.control.web.shared.SaveAppResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.AppDto;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
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
                appDto = accessControlBaseServiceFacade.createApp(ServiceContextHolder.getCurrentServiceContext(), appToSave);
            } else {
                appDto = accessControlBaseServiceFacade.updateApp(ServiceContextHolder.getCurrentServiceContext(), appToSave);
            }
            return new SaveAppResult(appDto);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(SaveAppAction action, SaveAppResult result, ExecutionContext context) throws ActionException {

    }

}
