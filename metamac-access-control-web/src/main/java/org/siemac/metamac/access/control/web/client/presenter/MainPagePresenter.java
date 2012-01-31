package org.siemac.metamac.access.control.web.client.presenter;

import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.view.handlers.MainPageUiHandlers;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;

public class MainPagePresenter extends Presenter<MainPagePresenter.MainPageView, MainPagePresenter.MainPageProxy> implements MainPageUiHandlers {

	private final PlaceManager placeManager;
	private final DispatchAsync dispatcher;

	@ProxyStandard
	@NameToken(NameTokens.mainPage)
	public interface MainPageProxy extends Proxy<MainPagePresenter>, Place {

	}
	
	public interface MainPageView extends View, HasUiHandlers<MainPageUiHandlers> {
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
		getView().setUiHandlers(this);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
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

}
