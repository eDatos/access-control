package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.shared.DeleteRoleListAction;
import org.siemac.metamac.access.control.web.shared.DeleteRoleListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteRoleListActionHandler extends AbstractActionHandler<DeleteRoleListAction, DeleteRoleListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public DeleteRoleListActionHandler() {
        super(DeleteRoleListAction.class);
    }

    @Override
    public DeleteRoleListResult execute(DeleteRoleListAction action, ExecutionContext context) throws ActionException {
        List<Long> ids = action.getRoleIds();
        for (Long id : ids) {
            try {
                accessControlBaseServiceFacade.deleteRole(ServiceContextHelper.getServiceContext(), id);
            } catch (MetamacException e) {
                throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
            }
        }
        return new DeleteRoleListResult();
    }

    @Override
    public void undo(DeleteRoleListAction action, DeleteRoleListResult result, ExecutionContext context) throws ActionException {

    }

}
