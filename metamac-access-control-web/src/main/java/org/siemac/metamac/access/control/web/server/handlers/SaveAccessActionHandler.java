package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.SaveAccessAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class SaveAccessActionHandler extends SecurityActionHandler<SaveAccessAction, SaveAccessResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public SaveAccessActionHandler() {
        super(SaveAccessAction.class);
    }

    @Override
    public SaveAccessResult executeSecurityAction(SaveAccessAction action) throws ActionException {
        AccessDto accessToSave = action.getAccessToSave();
        try {
            AccessDto accessDto = null;
            if (accessToSave.getId() == null) {
                accessDto = accessControlBaseServiceFacade.createAccess(ServiceContextHolder.getCurrentServiceContext(), accessToSave);
            } else {
                // DO NOT EDIT ACCESS: delete access and create a new one instead
                // accessDto = accessControlBaseServiceFacade.updateAccess(ServiceContextHelper.getCurrentServiceContext(), accessToSave);
            }
            return new SaveAccessResult(accessDto);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(SaveAccessAction action, SaveAccessResult result, ExecutionContext context) throws ActionException {

    }

}
