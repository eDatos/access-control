package org.siemac.metamac.access.control.web.client.presenter;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.view.handlers.RoleHistoryUiHandlers;
import org.siemac.metamac.access.control.web.shared.FindAllRemovedAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllRemovedAccessResult;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent;
import org.siemac.metamac.web.common.client.utils.ErrorUtils;

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


public class RoleHistoryPresenter extends Presenter<RoleHistoryPresenter.RoleHistoryView, RoleHistoryPresenter.RoleHistoryProxy> implements RoleHistoryUiHandlers {

    private final DispatchAsync dispatcher;
    
    @ProxyCodeSplit
    @NameToken(NameTokens.roleHistoryPage)
    public interface RoleHistoryProxy extends Proxy<RoleHistoryPresenter>, Place {
    
    }
    
    public interface RoleHistoryView extends View, HasUiHandlers<RoleHistoryUiHandlers> {
        void setRemovedAccess(List<AccessDto> accessDtos);
    }
    
    @Inject
    public RoleHistoryPresenter(EventBus eventBus, RoleHistoryView view, RoleHistoryProxy proxy, DispatchAsync dispatcher) {
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
        retrieveDischargedAccess();
    }
    
    private void retrieveDischargedAccess() {
        dispatcher.execute(new FindAllRemovedAccessAction(), new AsyncCallback<FindAllRemovedAccessResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(RoleHistoryPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingDischargedAccess()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onSuccess(FindAllRemovedAccessResult result) {
                getView().setRemovedAccess(result.getAccessDtos());
            }}
        );
    }
    
}
