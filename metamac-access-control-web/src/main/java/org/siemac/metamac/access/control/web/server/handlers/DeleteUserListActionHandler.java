package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.DeleteUserListAction;
import org.siemac.metamac.access.control.web.shared.DeleteUserListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class DeleteUserListActionHandler extends SecurityActionHandler<DeleteUserListAction, DeleteUserListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public DeleteUserListActionHandler() {
        super(DeleteUserListAction.class);
    }

    @Override
    public DeleteUserListResult executeSecurityAction(DeleteUserListAction action) throws ActionException {
        List<Long> ids = action.getUserIds();
        for (Long id : ids) {
            try {
                accessControlBaseServiceFacade.deleteUser(ServiceContextHolder.getCurrentServiceContext(), id);
            } catch (MetamacException e) {
                throw WebExceptionUtils.createMetamacWebException(e);
            }
        }
        return new DeleteUserListResult();
    }

    @Override
    public void undo(DeleteUserListAction action, DeleteUserListResult result, ExecutionContext context) throws ActionException {

    }

}
