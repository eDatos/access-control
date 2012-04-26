package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.ServiceContextHolder;
import org.siemac.metamac.access.control.web.shared.FindAccessByUserAction;
import org.siemac.metamac.access.control.web.shared.FindAccessByUserResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.AccessDto;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class FindAccessByUserActionHandler extends AbstractActionHandler<FindAccessByUserAction, FindAccessByUserResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public FindAccessByUserActionHandler() {
        super(FindAccessByUserAction.class);
    }

    @Override
    public FindAccessByUserResult execute(FindAccessByUserAction action, ExecutionContext context) throws ActionException {
        try {
            List<AccessDto> accessDtos = accessControlBaseServiceFacade.findAccessByCondition(ServiceContextHolder.getCurrentServiceContext(), null, null, action.getUsername(), null, false);
            return new FindAccessByUserResult(accessDtos);
        } catch (MetamacException e) {
            throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
        }
    }

    @Override
    public void undo(FindAccessByUserAction action, FindAccessByUserResult result, ExecutionContext context) throws ActionException {

    }

}
