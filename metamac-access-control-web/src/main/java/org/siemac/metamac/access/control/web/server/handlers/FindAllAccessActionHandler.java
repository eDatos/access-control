package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContextStore;
import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.web.shared.FindAllAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllAccessResult;
import org.siemac.metamac.access.control.web.shared.exception.MetamacWebException;
import org.siemac.metamac.core.common.exception.MetamacException;
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
            List<AccessDto> accessDtos = accessControlBaseServiceFacade.findAllAccess(ServiceContextStore.get());
            return new FindAllAccessResult(accessDtos);
        } catch (MetamacException e) {
            throw new MetamacWebException(e.getExceptionItems());
        }
    }

    @Override
    public void undo(FindAllAccessAction action, FindAllAccessResult result, ExecutionContext context) throws ActionException {
        
    }
    
}
