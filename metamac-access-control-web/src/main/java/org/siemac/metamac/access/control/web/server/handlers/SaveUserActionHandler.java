package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.ServiceContextHolder;
import org.siemac.metamac.access.control.web.shared.SaveUserAction;
import org.siemac.metamac.access.control.web.shared.SaveUserResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.UserDto;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class SaveUserActionHandler extends AbstractActionHandler<SaveUserAction, SaveUserResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public SaveUserActionHandler() {
        super(SaveUserAction.class);
    }

    @Override
    public SaveUserResult execute(SaveUserAction action, ExecutionContext context) throws ActionException {
        UserDto userToSave = action.getUserToSave();
        try {
            UserDto userDto = null;
            if (userToSave.getId() == null) {
                userDto = accessControlBaseServiceFacade.createUser(ServiceContextHolder.getCurrentServiceContext(), userToSave);
            } else {
                userDto = accessControlBaseServiceFacade.updateUser(ServiceContextHolder.getCurrentServiceContext(), userToSave);
            }
            return new SaveUserResult(userDto);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(SaveUserAction action, SaveUserResult result, ExecutionContext context) throws ActionException {

    }

}
