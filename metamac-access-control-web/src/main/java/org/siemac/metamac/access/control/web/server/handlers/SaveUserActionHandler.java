package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.SaveUserAction;
import org.siemac.metamac.access.control.web.shared.SaveUserResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class SaveUserActionHandler extends SecurityActionHandler<SaveUserAction, SaveUserResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public SaveUserActionHandler() {
        super(SaveUserAction.class);
    }

    @Override
    public SaveUserResult executeSecurityAction(SaveUserAction action) throws ActionException {
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
}
