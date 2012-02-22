package org.siemac.metamac.access.control.web.client.presenter;

import java.util.List;

import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.view.handlers.MainPageUiHandlers;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.events.SetTitleEvent;
import org.siemac.metamac.web.common.client.events.SetTitleEvent.SetTitleHandler;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent.ShowMessageHandler;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MainPagePresenter extends Presenter<MainPagePresenter.MainPageView, MainPagePresenter.MainPageProxy> implements MainPageUiHandlers, ShowMessageHandler, SetTitleHandler {

	@ProxyStandard
	@NameToken(NameTokens.mainPage)
	public interface MainPageProxy extends Proxy<MainPagePresenter>, Place {

	}
	
	public interface MainPageView extends View, HasUiHandlers<MainPageUiHandlers> {
	    void showMessage(List<String> messages, MessageTypeEnum type);
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
	public MainPagePresenter(EventBus eventBus, MainPageView view, MainPageProxy proxy) {
		super(eventBus, view, proxy);
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
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
    public void onSetTitle(SetTitleEvent event) {
        getView().setTitle(event.getTitle());
    }

}
