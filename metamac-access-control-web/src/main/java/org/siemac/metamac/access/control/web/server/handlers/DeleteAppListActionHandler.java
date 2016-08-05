package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.DeleteAppListAction;
import org.siemac.metamac.access.control.web.shared.DeleteAppListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class DeleteAppListActionHandler extends SecurityActionHandler<DeleteAppListAction, DeleteAppListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public DeleteAppListActionHandler() {
        super(DeleteAppListAction.class);
    }

    @Override
    public DeleteAppListResult executeSecurityAction(DeleteAppListAction action) throws ActionException {
        List<Long> ids = action.getAppIds();
        for (Long id : ids) {
            try {
                accessControlBaseServiceFacade.deleteApp(ServiceContextHolder.getCurrentServiceContext(), id);
            } catch (MetamacException e) {
                throw WebExceptionUtils.createMetamacWebException(e);
            }
        }
        return new DeleteAppListResult();
    }

    @Override
    public void undo(DeleteAppListAction action, DeleteAppListResult result, ExecutionContext context) throws ActionException {

    }

}
