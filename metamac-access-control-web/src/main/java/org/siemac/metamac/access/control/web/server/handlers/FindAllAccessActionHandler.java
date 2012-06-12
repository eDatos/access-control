package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.FindAllAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllAccessResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.AccessDto;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class FindAllAccessActionHandler extends SecurityActionHandler<FindAllAccessAction, FindAllAccessResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public FindAllAccessActionHandler() {
        super(FindAllAccessAction.class);
    }

    @Override
    public FindAllAccessResult executeSecurityAction(FindAllAccessAction action) throws ActionException {
        try {
            List<AccessDto> accessDtos = accessControlBaseServiceFacade.findAccessByCondition(ServiceContextHolder.getCurrentServiceContext(), null, null, null, null, Boolean.valueOf(false));
            return new FindAllAccessResult(accessDtos);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(FindAllAccessAction action, FindAllAccessResult result, ExecutionContext context) throws ActionException {

    }

}
