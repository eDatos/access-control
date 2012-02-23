package org.siemac.metamac.access.control.web.client.events;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.AppDto;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class UpdateApplicationsEvent extends GwtEvent<UpdateApplicationsEvent.UpdateApplicationsHandler> {

	public interface UpdateApplicationsHandler extends EventHandler {
		void onUpdateApplications(UpdateApplicationsEvent event);
	}

	private static Type<UpdateApplicationsHandler> TYPE = new Type<UpdateApplicationsHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UpdateApplicationsHandler> getAssociatedType() {
		return TYPE;
	}
	
	// TODO HasEventBus should be used instead of HasHandlers ¿?
	public static void fire(HasHandlers source, List<AppDto> appDtos) {
		if (TYPE != null) {
			source.fireEvent(new UpdateApplicationsEvent(appDtos));
		}
	}

	@Override
	protected void dispatch(UpdateApplicationsHandler handler) {
		handler.onUpdateApplications(this);
	}
	
	private final List<AppDto> appDtos;
	
	public UpdateApplicationsEvent(List<AppDto> appDtos) {
		this.appDtos = appDtos;
	}
	
	public List<AppDto> getApplications() {
		return appDtos;
	}

	public static Type<UpdateApplicationsHandler> getType() {
		return TYPE;
	}

}
