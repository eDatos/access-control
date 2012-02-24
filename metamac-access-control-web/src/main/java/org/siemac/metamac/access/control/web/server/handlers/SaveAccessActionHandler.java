package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.server.utils.WebExceptionUtils;
import org.siemac.metamac.access.control.web.shared.SaveAccessAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;


public class SaveAccessActionHandler extends AbstractActionHandler<SaveAccessAction, SaveAccessResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    
    public SaveAccessActionHandler() {
        super(SaveAccessAction.class);
    }

    @Override
    public SaveAccessResult execute(SaveAccessAction action, ExecutionContext context) throws ActionException {
        AccessDto accessToSave = action.getAccessToSave();
        try {
            AccessDto accessDto = null;
            if (accessToSave.getId() == null) {
                accessDto = accessControlBaseServiceFacade.createAccess(ServiceContextHelper.getServiceContext(), accessToSave);
            } else {
                // DO NOT EDIT ACCESS: delete access and create a new one instead
                // accessDto = accessControlBaseServiceFacade.updateAccess(ServiceContextHelper.getServiceContext(), accessToSave);
            }
            return new SaveAccessResult(accessDto);
        } catch (MetamacException e) {
            throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
        }
    }


    @Override
    public void undo(SaveAccessAction action, SaveAccessResult result, ExecutionContext context) throws ActionException {
        
    }
    
}
