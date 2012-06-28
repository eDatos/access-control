package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.FindAllRolesAction;
import org.siemac.metamac.access.control.web.shared.FindAllRolesResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class FindAllRolesActionHandler extends SecurityActionHandler<FindAllRolesAction, FindAllRolesResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public FindAllRolesActionHandler() {
        super(FindAllRolesAction.class);
    }

    @Override
    public FindAllRolesResult executeSecurityAction(FindAllRolesAction action) throws ActionException {
        try {
            List<RoleDto> roleDtos = accessControlBaseServiceFacade.findAllRoles(ServiceContextHolder.getCurrentServiceContext());
            return new FindAllRolesResult(roleDtos);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(FindAllRolesAction action, FindAllRolesResult result, ExecutionContext context) throws ActionException {

    }

}
