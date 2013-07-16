package org.siemac.metamac.access.control.web.server.handlers;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.shared.SaveAccessListAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class SaveAccessListActionHandler extends SecurityActionHandler<SaveAccessListAction, SaveAccessListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public SaveAccessListActionHandler() {
        super(SaveAccessListAction.class);
    }

    @Override
    public SaveAccessListResult executeSecurityAction(SaveAccessListAction action) throws ActionException {
        List<AccessDto> accessSaved = new ArrayList<AccessDto>();
        List<AccessDto> accessDtos = action.getAccessToSave();
        for (AccessDto accessToSave : accessDtos) {
            try {
                AccessDto accessDto = null;
                if (accessToSave.getId() == null) {
                    // Avoid optimisticLockException
                    UserDto userDto = accessControlBaseServiceFacade.findUserById(ServiceContextHolder.getCurrentServiceContext(), accessToSave.getUser().getId());
                    accessToSave.setUser(userDto);

                    AppDto appDto = accessControlBaseServiceFacade.findAppById(ServiceContextHolder.getCurrentServiceContext(), accessToSave.getApp().getId());
                    accessToSave.setApp(appDto);

                    RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(ServiceContextHolder.getCurrentServiceContext(), accessToSave.getRole().getId());
                    accessToSave.setRole(roleDto);

                    accessDto = accessControlBaseServiceFacade.createAccess(ServiceContextHolder.getCurrentServiceContext(), accessToSave);
                    accessSaved.add(accessDto);
                } else {
                    // DO NOT EDIT ACCESS: delete access and create a new one instead
                    // accessDto = accessControlBaseServiceFacade.updateAccess(ServiceContextHelper.getCurrentServiceContext(), accessToSave);
                }
            } catch (MetamacException e) {
                throw WebExceptionUtils.createMetamacWebException(e);
            }
        }
        return new SaveAccessListResult(accessSaved);
    }

    @Override
    public void undo(SaveAccessListAction action, SaveAccessListResult result, ExecutionContext context) throws ActionException {

    }
}
