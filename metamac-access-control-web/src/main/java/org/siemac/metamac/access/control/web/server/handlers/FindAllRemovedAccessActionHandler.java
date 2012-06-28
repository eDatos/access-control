package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.FindAllRemovedAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllRemovedAccessResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class FindAllRemovedAccessActionHandler extends SecurityActionHandler<FindAllRemovedAccessAction, FindAllRemovedAccessResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public FindAllRemovedAccessActionHandler() {
        super(FindAllRemovedAccessAction.class);
    }

    @Override
    public FindAllRemovedAccessResult executeSecurityAction(FindAllRemovedAccessAction action) throws ActionException {
        try {
            List<AccessDto> accessDtos = accessControlBaseServiceFacade.findAccessByCondition(ServiceContextHolder.getCurrentServiceContext(), null, null, null, null, Boolean.valueOf(true));
            return new FindAllRemovedAccessResult(accessDtos);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(FindAllRemovedAccessAction action, FindAllRemovedAccessResult result, ExecutionContext context) throws ActionException {

    }

}
