package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.server.utils.WebExceptionUtils;
import org.siemac.metamac.access.control.web.shared.DeleteUserListAction;
import org.siemac.metamac.access.control.web.shared.DeleteUserListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;


public class DeleteUserListActionHandler extends AbstractActionHandler<DeleteUserListAction, DeleteUserListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;
 
    public DeleteUserListActionHandler() {
        super(DeleteUserListAction.class);
    }

    @Override
    public DeleteUserListResult execute(DeleteUserListAction action, ExecutionContext context) throws ActionException {
        List<Long> ids = action.getUserIds();
        for (Long id : ids) {
            try {
                accessControlBaseServiceFacade.deleteUser(ServiceContextHelper.getServiceContext(), id);
            } catch (MetamacException e) {
                throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
            }
        }
        return new DeleteUserListResult();
    }

    @Override
    public void undo(DeleteUserListAction action, DeleteUserListResult result, ExecutionContext context) throws ActionException {
        
    }
    
}
