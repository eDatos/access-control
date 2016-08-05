package org.siemac.metamac.access.control.web.server.handlers;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.rest.NoticesRestInternalService;
import org.siemac.metamac.access.control.web.shared.SaveAccessListAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.exception.MetamacExceptionItem;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.siemac.metamac.web.common.shared.exception.MetamacWebExceptionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class SaveAccessListActionHandler extends SecurityActionHandler<SaveAccessListAction, SaveAccessListResult> {

    @Autowired
    private NoticesRestInternalService     noticesRestInternalService;

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public SaveAccessListActionHandler() {
        super(SaveAccessListAction.class);
    }

    @Override
    public SaveAccessListResult executeSecurityAction(SaveAccessListAction action) throws ActionException {
        List<MetamacExceptionItem> exceptionItems = new ArrayList<MetamacExceptionItem>();
        ServiceContext serviceContext = ServiceContextHolder.getCurrentServiceContext();

        List<AccessDto> accessSaved = new ArrayList<AccessDto>();
        List<AccessDto> accessDtos = action.getAccessToSave();
        UserDto updatedUserDto = null;
        for (AccessDto accessToSave : accessDtos) {
            try {
                AccessDto accessDto = null;

                // DO NOT EDIT ACCESS: delete access and create a new one instead
                if (accessToSave.getId() == null) {
                    // Avoid optimisticLockException
                    UserDto userDto = accessControlBaseServiceFacade.findUserById(serviceContext, accessToSave.getUser().getId());
                    accessToSave.setUser(userDto);

                    AppDto appDto = accessControlBaseServiceFacade.findAppById(serviceContext, accessToSave.getApp().getId());
                    accessToSave.setApp(appDto);

                    RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(serviceContext, accessToSave.getRole().getId());
                    accessToSave.setRole(roleDto);

                    accessDto = accessControlBaseServiceFacade.createAccess(serviceContext, accessToSave);
                    accessSaved.add(accessDto);
                }

            } catch (MetamacException e) {
                exceptionItems.addAll(e.getExceptionItems());
            }
        }

        if (!accessSaved.isEmpty()) {

            try {
                updatedUserDto = accessControlBaseServiceFacade.findUserById(ServiceContextHolder.getCurrentServiceContext(), accessSaved.get(0).getUser().getId());
            } catch (MetamacException e) {
                exceptionItems.addAll(e.getExceptionItems());
            }

            try {
                noticesRestInternalService.createNotificationForSaveAccessList(serviceContext, accessSaved);
            } catch (MetamacWebException notificationException) {
                List<MetamacWebExceptionItem> actionExceptionItems = WebExceptionUtils.getMetamacWebExceptionItems(null, exceptionItems);
                notificationException.getWebExceptionItems().addAll(actionExceptionItems);
                return new SaveAccessListResult(updatedUserDto, accessSaved, notificationException);
            }
        }

        if (!exceptionItems.isEmpty()) {
            throw WebExceptionUtils.createMetamacWebException(new MetamacException(exceptionItems));
        }

        return new SaveAccessListResult(updatedUserDto, accessSaved, null);
    }

    @Override
    public void undo(SaveAccessListAction action, SaveAccessListResult result, ExecutionContext context) throws ActionException {

    }
}
