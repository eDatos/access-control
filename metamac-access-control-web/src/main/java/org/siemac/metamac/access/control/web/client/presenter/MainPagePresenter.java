package org.siemac.metamac.access.control.web.client.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.events.UpdateApplicationsEvent;
import org.siemac.metamac.access.control.web.client.events.UpdateRolesEvent;
import org.siemac.metamac.access.control.web.client.view.handlers.MainPageUiHandlers;
import org.siemac.metamac.access.control.web.shared.FindAllAppsAction;
import org.siemac.metamac.access.control.web.shared.FindAllAppsResult;
import org.siemac.metamac.access.control.web.shared.FindAllRolesAction;
import org.siemac.metamac.access.control.web.shared.FindAllRolesResult;
import org.siemac.metamac.access.control.web.shared.GetHelpUrlAction;
import org.siemac.metamac.access.control.web.shared.GetHelpUrlResult;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.events.HideMessageEvent;
import org.siemac.metamac.web.common.client.events.HideMessageEvent.HideMessageHandler;
import org.siemac.metamac.web.common.client.events.SetTitleEvent;
import org.siemac.metamac.web.common.client.events.SetTitleEvent.SetTitleHandler;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent.ShowMessageHandler;
import org.siemac.metamac.web.common.client.utils.WaitingAsyncCallbackHandlingError;
import org.siemac.metamac.web.common.shared.CloseSessionAction;
import org.siemac.metamac.web.common.shared.CloseSessionResult;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MainPagePresenter extends Presenter<MainPagePresenter.MainPageView, MainPagePresenter.MainPageProxy>
        implements
            MainPageUiHandlers,
            ShowMessageHandler,
            HideMessageHandler,
            SetTitleHandler {

    private static Logger       logger = Logger.getLogger(MainPagePresenter.class.getName());

    private final DispatchAsync dispatcher;

    @ProxyStandard
    @NameToken(NameTokens.mainPage)
    @NoGatekeeper
    public interface MainPageProxy extends Proxy<MainPagePresenter>, Place {

    }

    public interface MainPageView extends View, HasUiHandlers<MainPageUiHandlers> {

        void showMessage(Throwable throwable, String message, MessageTypeEnum type);

        void hideMessages();

        void setTitle(String title);
    }

    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     * Is used to define a type to use in child presenters when you want to
     * include them inside this page.
     */
    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();

    @Inject
    public MainPagePresenter(EventBus eventBus, MainPageView view, MainPageProxy proxy, DispatchAsync dispatcher) {
        super(eventBus, view, proxy);
        this.dispatcher = dispatcher;
        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        super.onBind();

        loadRoles();
        loadApplications();
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    @ProxyEvent
    @Override
    public void onShowMessage(ShowMessageEvent event) {
        getView().showMessage(event.getThrowable(), event.getMessage(), event.getMessageType());
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
        dispatcher.execute(new FindAllRolesAction(), new WaitingAsyncCallbackHandlingError<FindAllRolesResult>(this) {

            @Override
            public void onWaitSuccess(FindAllRolesResult result) {
                UpdateRolesEvent.fire(MainPagePresenter.this, result.getRoles());
            }
        });
    }

    private void loadApplications() {
        dispatcher.execute(new FindAllAppsAction(), new WaitingAsyncCallbackHandlingError<FindAllAppsResult>(this) {

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
                logger.log(Level.SEVERE, "Error closing session");
                ShowMessageEvent.fireErrorMessage(MainPagePresenter.this, caught);
            }

            @Override
            public void onSuccess(CloseSessionResult result) {
                Window.Location.assign(result.getLogoutPageUrl());
            }
        });
    }

    @Override
    public void openHelpUrl() {
        dispatcher.execute(new GetHelpUrlAction(), new WaitingAsyncCallbackHandlingError<GetHelpUrlResult>(this) {

            @Override
            public void onWaitSuccess(GetHelpUrlResult result) {
                Window.open(result.getHelpUrl(), "_blank", "");
            }
        });
    }
}
