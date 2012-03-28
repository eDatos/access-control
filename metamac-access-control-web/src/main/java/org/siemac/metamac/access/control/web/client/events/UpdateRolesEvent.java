package org.siemac.metamac.access.control.web.client.events;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class UpdateRolesEvent extends GwtEvent<UpdateRolesEvent.UpdateRolesHandler> {

    public interface UpdateRolesHandler extends EventHandler {

        void onUpdateRoles(UpdateRolesEvent event);
    }

    private static Type<UpdateRolesHandler> TYPE = new Type<UpdateRolesHandler>();

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<UpdateRolesHandler> getAssociatedType() {
        return TYPE;
    }

    // TODO HasEventBus should be used instead of HasHandlers ¿?
    public static void fire(HasHandlers source, List<RoleDto> rolesDtos) {
        if (TYPE != null) {
            source.fireEvent(new UpdateRolesEvent(rolesDtos));
        }
    }

    @Override
    protected void dispatch(UpdateRolesHandler handler) {
        handler.onUpdateRoles(this);
    }

    private final List<RoleDto> roleDtos;

    public UpdateRolesEvent(List<RoleDto> roleDtos) {
        this.roleDtos = roleDtos;
    }

    public List<RoleDto> getRoles() {
        return roleDtos;
    }

    public static Type<UpdateRolesHandler> getType() {
        return TYPE;
    }

}
