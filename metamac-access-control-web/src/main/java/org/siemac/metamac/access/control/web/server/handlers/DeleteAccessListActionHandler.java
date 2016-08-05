package org.siemac.metamac.access.control.web.server.handlers;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.rest.NoticesRestInternalService;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListAction;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.exception.MetamacExceptionItem;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.siemac.metamac.web.common.shared.exception.MetamacWebExceptionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class DeleteAccessListActionHandler extends SecurityActionHandler<DeleteAccessListAction, DeleteAccessListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    @Autowired
    private NoticesRestInternalService     noticesRestInternalService;

    public DeleteAccessListActionHandler() {
        super(DeleteAccessListAction.class);
    }

    @Override
    public DeleteAccessListResult executeSecurityAction(DeleteAccessListAction action) throws ActionException {
        List<MetamacExceptionItem> exceptionItems = new ArrayList<MetamacExceptionItem>();
        ServiceContext serviceContext = ServiceContextHolder.getCurrentServiceContext();

        List<AccessDto> accessDeleted = new ArrayList<AccessDto>();
        List<AccessDto> accessList = action.getAccess();
        for (AccessDto access : accessList) {
            try {
                accessControlBaseServiceFacade.removeAccess(serviceContext, access.getId());
                accessDeleted.add(access);
            } catch (MetamacException e) {
                exceptionItems.addAll(e.getExceptionItems());
            }
        }

        if (!accessDeleted.isEmpty()) {
            try {
                noticesRestInternalService.createNotificationForDeleteAccessList(serviceContext, accessDeleted);
            } catch (MetamacWebException notificationException) {
                List<MetamacWebExceptionItem> actionExceptionItems = WebExceptionUtils.getMetamacWebExceptionItems(null, exceptionItems);
                notificationException.getWebExceptionItems().addAll(actionExceptionItems);

                return new DeleteAccessListResult(notificationException);
            }
        }

        if (!exceptionItems.isEmpty()) {
            throw WebExceptionUtils.createMetamacWebException(new MetamacException(exceptionItems));
        }

        return new DeleteAccessListResult(null);
    }

    @Override
    public void undo(DeleteAccessListAction action, DeleteAccessListResult result, ExecutionContext context) throws ActionException {

    }

}
