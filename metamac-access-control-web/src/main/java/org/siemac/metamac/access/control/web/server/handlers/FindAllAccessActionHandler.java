package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.shared.FindAllAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllAccessResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.AccessDto;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class FindAllAccessActionHandler extends AbstractActionHandler<FindAllAccessAction, FindAllAccessResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public FindAllAccessActionHandler() {
        super(FindAllAccessAction.class);
    }

    @Override
    public FindAllAccessResult execute(FindAllAccessAction action, ExecutionContext context) throws ActionException {
        try {
            List<AccessDto> accessDtos = accessControlBaseServiceFacade.findAccessByCondition(ServiceContextHelper.getServiceContext(), null, null, null, null, Boolean.valueOf(false));
            return new FindAllAccessResult(accessDtos);
        } catch (MetamacException e) {
            throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
        }
    }

    @Override
    public void undo(FindAllAccessAction action, FindAllAccessResult result, ExecutionContext context) throws ActionException {

    }

}
