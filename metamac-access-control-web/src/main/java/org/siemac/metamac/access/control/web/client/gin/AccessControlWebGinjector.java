package org.siemac.metamac.access.control.web.client.gin;

import org.siemac.metamac.access.control.web.client.presenter.MainPagePresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

@GinModules({DispatchAsyncModule.class, ClientModule.class})
public interface AccessControlWebGinjector extends Ginjector {
	
	PlaceManager getPlaceManager();
	EventBus getEventBus();
		
	Provider<MainPagePresenter> getMainPagePresenter();

	

}