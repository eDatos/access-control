package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.server.utils.WebExceptionUtils;
import org.siemac.metamac.access.control.web.shared.FindAllUsersAction;
import org.siemac.metamac.access.control.web.shared.FindAllUsersResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;


public class FindAllUsersActionHandler extends AbstractActionHandler<FindAllUsersAction, FindAllUsersResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    
    public FindAllUsersActionHandler() {
        super(FindAllUsersAction.class);
    }

    @Override
    public FindAllUsersResult execute(FindAllUsersAction action, ExecutionContext context) throws ActionException {
        try {
            List<UserDto> userDtos = accessControlBaseServiceFacade.findAllUsers(ServiceContextHelper.getServiceContext());
            return new FindAllUsersResult(userDtos);
        } catch (MetamacException e) {
            throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
        }
    }

    @Override
    public void undo(FindAllUsersAction action, FindAllUsersResult result, ExecutionContext context) throws ActionException {
        
    }
    
}
