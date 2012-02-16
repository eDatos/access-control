package org.siemac.metamac.access.control.web.client.presenter;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.utils.MessageUtils;
import org.siemac.metamac.access.control.web.client.view.handlers.AccessUiHandlers;
import org.siemac.metamac.access.control.web.shared.FindAllAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllAccessResult;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;


public class AccessPresenter extends Presenter<AccessPresenter.AccessView, AccessPresenter.AccessProxy> implements AccessUiHandlers {
    
    private final DispatchAsync dispatcher;
    
    @ProxyCodeSplit
    @NameToken(NameTokens.accessPage)
    public interface AccessProxy extends Proxy<AccessPresenter>, Place {
    }
    
    public interface AccessView extends View, HasUiHandlers<AccessUiHandlers> {
        void setAccessList(List<AccessDto> accessDtos);
    }
    
    @Inject
    public AccessPresenter(EventBus eventBus, AccessView view, AccessProxy proxy, DispatchAsync dispatcher) {
        super(eventBus, view, proxy);
        this.dispatcher = dispatcher;
        getView().setUiHandlers(this);
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetContextAreaContent, this);
    }
    
    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        retrieveAccessList();
    }
    
    private void retrieveAccessList() {
        dispatcher.execute(new FindAllAccessAction(), new AsyncCallback<FindAllAccessResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(AccessPresenter.this, MessageUtils.getErrorMessages(caught, "Error al obtener accesos"), MessageTypeEnum.ERROR);
                // TODO ERROR
                System.out.println();
            }
            @Override
            public void onSuccess(FindAllAccessResult result) {
                getView().setAccessList(result.getAccess());
            }
        });
    }
    
}
