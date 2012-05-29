package org.siemac.metamac.access.control.web.client.presenter;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.List;

import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.events.UpdateApplicationsEvent;
import org.siemac.metamac.access.control.web.client.events.UpdateOperationsEvent;
import org.siemac.metamac.access.control.web.client.events.UpdateRolesEvent;
import org.siemac.metamac.access.control.web.client.utils.ErrorUtils;
import org.siemac.metamac.access.control.web.client.view.handlers.MainPageUiHandlers;
import org.siemac.metamac.access.control.web.shared.CloseSessionAction;
import org.siemac.metamac.access.control.web.shared.CloseSessionResult;
import org.siemac.metamac.access.control.web.shared.FindAllAppsAction;
import org.siemac.metamac.access.control.web.shared.FindAllAppsResult;
import org.siemac.metamac.access.control.web.shared.FindAllRolesAction;
import org.siemac.metamac.access.control.web.shared.FindAllRolesResult;
import org.siemac.metamac.access.control.web.shared.FindAllStatisticalOperationsAction;
import org.siemac.metamac.access.control.web.shared.FindAllStatisticalOperationsResult;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.events.HideMessageEvent;
import org.siemac.metamac.web.common.client.events.HideMessageEvent.HideMessageHandler;
import org.siemac.metamac.web.common.client.events.SetTitleEvent;
import org.siemac.metamac.web.common.client.events.SetTitleEvent.SetTitleHandler;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent.ShowMessageHandler;
import org.siemac.metamac.web.common.client.widgets.WaitingAsyncCallback;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.HasClickHandlers;

public class MainPagePresenter extends Presenter<MainPagePresenter.MainPageView, MainPagePresenter.MainPageProxy>
        implements
            MainPageUiHandlers,
            ShowMessageHandler,
            HideMessageHandler,
            SetTitleHandler {

    private final PlaceManager  placeManager;
    private final DispatchAsync dispatcher;

    @ProxyStandard
    @NameToken(NameTokens.mainPage)
    public interface MainPageProxy extends Proxy<MainPagePresenter>, Place {

    }

    public interface MainPageView extends View, HasUiHandlers<MainPageUiHandlers> {

        void showMessage(List<String> messages, MessageTypeEnum type);
        void hideMessages();
        void setTitle(String title);

        HasClickHandlers goToUsersListPage();
        HasClickHandlers goToRoleHistoryPage();
    }

    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     * Is used to define a type to use in child presenters when you want to
     * include them inside this page.
     */
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();

    @Inject
    public MainPagePresenter(EventBus eventBus, MainPageView view, MainPageProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
        super(eventBus, view, proxy);
        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getView().goToUsersListPage().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                placeManager.revealPlace(new PlaceRequest(NameTokens.usersListPage));
            }
        }));

        registerHandler(getView().goToRoleHistoryPage().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                placeManager.revealPlace(new PlaceRequest(NameTokens.roleHistoryPage));
            }
        }));

        // TODO Is this the proper place to load value lists?
        loadRoles();
        loadOperations();
        loadApplications();
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
    }

    @Override
    protected void onReveal() {
        super.onReveal();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    @ProxyEvent
    @Override
    public void onShowMessage(ShowMessageEvent event) {
        getView().showMessage(event.getMessages(), event.getMessageType());
    }

    @ProxyEvent
    @Override
    public void onHideMessage(HideMessageEvent event) {
        hideMessages();
    }

    private void hideMessages() {
        getView().hideMessages();
    }

    @ProxyEvent
    @Override
    public void onSetTitle(SetTitleEvent event) {
        getView().setTitle(event.getTitle());
    }

    private void loadRoles() {
        dispatcher.execute(new FindAllRolesAction(), new WaitingAsyncCallback<FindAllRolesResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(MainPagePresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingRoles()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(FindAllRolesResult result) {
                UpdateRolesEvent.fire(MainPagePresenter.this, result.getRoles());
            }
        });
    }

    private void loadOperations() {
        dispatcher.execute(new FindAllStatisticalOperationsAction(), new WaitingAsyncCallback<FindAllStatisticalOperationsResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(MainPagePresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingOperations()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(FindAllStatisticalOperationsResult result) {
                UpdateOperationsEvent.fire(MainPagePresenter.this, result.getOperations());
            }
        });
    }

    private void loadApplications() {
        dispatcher.execute(new FindAllAppsAction(), new WaitingAsyncCallback<FindAllAppsResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(MainPagePresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingApplications()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(FindAllAppsResult result) {
                UpdateApplicationsEvent.fire(MainPagePresenter.this, result.getApps());
            }
        });
    }

    @Override
    public void closeSession() {
        dispatcher.execute(new CloseSessionAction(), new AsyncCallback<CloseSessionResult>() {

            @Override
            public void onFailure(Throwable caught) {
                // TODO
            }
            @Override
            public void onSuccess(CloseSessionResult result) {
                Window.Location.assign(result.getLogoutPageUrl());
            }
        });
    }

}
