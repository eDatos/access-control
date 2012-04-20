package org.siemac.metamac.access.control.web.client.events;

import java.util.List;

import org.siemac.metamac.core.common.dto.ExternalItemBtDto;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class UpdateOperationsEvent extends GwtEvent<UpdateOperationsEvent.UpdateOperationsHandler> {

    public interface UpdateOperationsHandler extends EventHandler {

        void onUpdateOperations(UpdateOperationsEvent event);
    }

    private static Type<UpdateOperationsHandler> TYPE = new Type<UpdateOperationsHandler>();

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<UpdateOperationsHandler> getAssociatedType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, List<ExternalItemBtDto> operations) {
        if (TYPE != null) {
            source.fireEvent(new UpdateOperationsEvent(operations));
        }
    }

    @Override
    protected void dispatch(UpdateOperationsHandler handler) {
        handler.onUpdateOperations(this);
    }

    private final List<ExternalItemBtDto> operations;

    public UpdateOperationsEvent(List<ExternalItemBtDto> operations) {
        this.operations = operations;
    }

    public List<ExternalItemBtDto> getOperations() {
        return operations;
    }

    public static Type<UpdateOperationsHandler> getType() {
        return TYPE;
    }

}
