package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.ServiceContextHolder;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListAction;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class DeleteAccessListActionHandler extends AbstractActionHandler<DeleteAccessListAction, DeleteAccessListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public DeleteAccessListActionHandler() {
        super(DeleteAccessListAction.class);
    }

    @Override
    public DeleteAccessListResult execute(DeleteAccessListAction action, ExecutionContext context) throws ActionException {
        List<Long> ids = action.getAccessIds();
        for (Long id : ids) {
            try {
                accessControlBaseServiceFacade.removeAccess(ServiceContextHolder.getCurrentServiceContext(), id);
            } catch (MetamacException e) {
                throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
            }
        }
        return new DeleteAccessListResult();
    }

    @Override
    public void undo(DeleteAccessListAction action, DeleteAccessListResult result, ExecutionContext context) throws ActionException {

    }

}
