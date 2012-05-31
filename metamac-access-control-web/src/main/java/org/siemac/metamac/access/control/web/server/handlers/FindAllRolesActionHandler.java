package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.FindAllRolesAction;
import org.siemac.metamac.access.control.web.shared.FindAllRolesResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.RoleDto;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class FindAllRolesActionHandler extends AbstractActionHandler<FindAllRolesAction, FindAllRolesResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public FindAllRolesActionHandler() {
        super(FindAllRolesAction.class);
    }

    @Override
    public FindAllRolesResult execute(FindAllRolesAction action, ExecutionContext context) throws ActionException {
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
