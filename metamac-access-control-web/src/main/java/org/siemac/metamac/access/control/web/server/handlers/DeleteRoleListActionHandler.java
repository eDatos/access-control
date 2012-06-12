package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.DeleteRoleListAction;
import org.siemac.metamac.access.control.web.shared.DeleteRoleListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class DeleteRoleListActionHandler extends SecurityActionHandler<DeleteRoleListAction, DeleteRoleListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public DeleteRoleListActionHandler() {
        super(DeleteRoleListAction.class);
    }

    @Override
    public DeleteRoleListResult executeSecurityAction(DeleteRoleListAction action) throws ActionException {
        List<Long> ids = action.getRoleIds();
        for (Long id : ids) {
            try {
                accessControlBaseServiceFacade.deleteRole(ServiceContextHolder.getCurrentServiceContext(), id);
            } catch (MetamacException e) {
                throw WebExceptionUtils.createMetamacWebException(e);
            }
        }
        return new DeleteRoleListResult();
    }

    @Override
    public void undo(DeleteRoleListAction action, DeleteRoleListResult result, ExecutionContext context) throws ActionException {

    }

}
