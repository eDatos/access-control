package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.FindAllUsersAction;
import org.siemac.metamac.access.control.web.shared.FindAllUsersResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class FindAllUsersActionHandler extends SecurityActionHandler<FindAllUsersAction, FindAllUsersResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public FindAllUsersActionHandler() {
        super(FindAllUsersAction.class);
    }

    @Override
    public FindAllUsersResult executeSecurityAction(FindAllUsersAction action) throws ActionException {
        try {
            List<UserDto> userDtos = accessControlBaseServiceFacade.findAllUsers(ServiceContextHolder.getCurrentServiceContext());
            return new FindAllUsersResult(userDtos);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(FindAllUsersAction action, FindAllUsersResult result, ExecutionContext context) throws ActionException {

    }

}
