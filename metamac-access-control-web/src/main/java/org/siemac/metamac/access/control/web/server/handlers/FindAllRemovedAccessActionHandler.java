package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.FindAllRemovedAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllRemovedAccessResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.AccessDto;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class FindAllRemovedAccessActionHandler extends AbstractActionHandler<FindAllRemovedAccessAction, FindAllRemovedAccessResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public FindAllRemovedAccessActionHandler() {
        super(FindAllRemovedAccessAction.class);
    }

    @Override
    public FindAllRemovedAccessResult execute(FindAllRemovedAccessAction action, ExecutionContext context) throws ActionException {
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
