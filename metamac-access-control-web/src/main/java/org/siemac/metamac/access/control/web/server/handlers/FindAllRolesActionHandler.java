package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.shared.FindAllRolesAction;
import org.siemac.metamac.access.control.web.shared.FindAllRolesResult;
import org.siemac.metamac.access.control.web.shared.exception.MetamacWebException;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;


public class FindAllRolesActionHandler extends AbstractActionHandler<FindAllRolesAction, FindAllRolesResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    
    public FindAllRolesActionHandler() {
        super(FindAllRolesAction.class);
    }

    @Override
    public FindAllRolesResult execute(FindAllRolesAction action, ExecutionContext context) throws ActionException {
        try {
            List<RoleDto> roleDtos = accessControlBaseServiceFacade.findAllRoles(ServiceContextHelper.getServiceContext());
            return new FindAllRolesResult(roleDtos);
        } catch (MetamacException e) {
            throw new MetamacWebException(e.getExceptionItems());
        }
    }

    @Override
    public void undo(FindAllRolesAction action, FindAllRolesResult result, ExecutionContext context) throws ActionException {
        
    }
    
}
