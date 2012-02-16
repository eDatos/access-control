package org.siemac.metamac.access.control.web.server.handlers;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.server.utils.WebExceptionUtils;
import org.siemac.metamac.access.control.web.shared.SaveRoleAction;
import org.siemac.metamac.access.control.web.shared.SaveRoleResult;
import org.siemac.metamac.access.control.web.shared.exception.MetamacWebException;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;


public class SaveRoleActionHandler extends AbstractActionHandler<SaveRoleAction, SaveRoleResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    
    public SaveRoleActionHandler() {
        super(SaveRoleAction.class);
    }

    @Override
    public SaveRoleResult execute(SaveRoleAction action, ExecutionContext context) throws ActionException {
        RoleDto roleToSave = action.getRoleToSave();
        try {
            RoleDto roleDto = null;
            if (roleToSave.getId() == null) {
                roleDto = accessControlBaseServiceFacade.createRole(ServiceContextHelper.getServiceContext(), roleToSave);
            } else {
                roleDto = accessControlBaseServiceFacade.updateRole(ServiceContextHelper.getServiceContext(), roleToSave);
            }
            return new SaveRoleResult(roleDto);
        } catch (MetamacException e) {
            throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
        }
    }


    @Override
    public void undo(SaveRoleAction action, SaveRoleResult result, ExecutionContext context) throws ActionException {
        
    }
    
}
