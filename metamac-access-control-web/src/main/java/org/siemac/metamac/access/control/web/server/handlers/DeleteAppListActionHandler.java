package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.server.utils.WebExceptionUtils;
import org.siemac.metamac.access.control.web.shared.DeleteAppListAction;
import org.siemac.metamac.access.control.web.shared.DeleteAppListResult;
import org.siemac.metamac.access.control.web.shared.exception.MetamacWebException;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;


public class DeleteAppListActionHandler extends AbstractActionHandler<DeleteAppListAction, DeleteAppListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;
 
    public DeleteAppListActionHandler() {
        super(DeleteAppListAction.class);
    }

    @Override
    public DeleteAppListResult execute(DeleteAppListAction action, ExecutionContext context) throws ActionException {
        List<Long> ids = action.getAppIds();
        for (Long id : ids) {
            try {
                accessControlBaseServiceFacade.deleteApp(ServiceContextHelper.getServiceContext(), id);
            } catch (MetamacException e) {
                throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
            }
        }
        return new DeleteAppListResult();
    }

    @Override
    public void undo(DeleteAppListAction action, DeleteAppListResult result, ExecutionContext context) throws ActionException {
        
    }
    
}
