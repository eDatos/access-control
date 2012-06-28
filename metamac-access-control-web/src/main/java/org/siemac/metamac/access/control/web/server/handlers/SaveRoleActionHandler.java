package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.SaveRoleAction;
import org.siemac.metamac.access.control.web.shared.SaveRoleResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class SaveRoleActionHandler extends SecurityActionHandler<SaveRoleAction, SaveRoleResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public SaveRoleActionHandler() {
        super(SaveRoleAction.class);
    }

    @Override
    public SaveRoleResult executeSecurityAction(SaveRoleAction action) throws ActionException {
        RoleDto roleToSave = action.getRoleToSave();
        try {
            RoleDto roleDto = null;
            if (roleToSave.getId() == null) {
                roleDto = accessControlBaseServiceFacade.createRole(ServiceContextHolder.getCurrentServiceContext(), roleToSave);
            } else {
                roleDto = accessControlBaseServiceFacade.updateRole(ServiceContextHolder.getCurrentServiceContext(), roleToSave);
            }
            return new SaveRoleResult(roleDto);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(SaveRoleAction action, SaveRoleResult result, ExecutionContext context) throws ActionException {

    }

}
