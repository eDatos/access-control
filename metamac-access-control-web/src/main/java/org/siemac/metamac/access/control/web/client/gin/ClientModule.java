package org.siemac.metamac.access.control.web.client.gin;

import org.siemac.metamac.access.control.web.client.AccessControlPlaceManager;
import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.presenter.MainPagePresenter;
import org.siemac.metamac.access.control.web.client.view.MainPageViewImpl;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
	    // Default implementation of standard resources
		//	 |_   bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		//	 |_   bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
		//	 |_   bind(RootPresenter.class).asEagerSingleton();
		//	 |_   bind(PlaceManager.class).to(MyPlaceManager.class).in(Singleton.class);
		//	 |_   bind(GoogleAnalytics.class).to(GoogleAnalyticsImpl.class).in(Singleton.class);		
	    install(new DefaultModule(AccessControlPlaceManager.class));
	    
	    // Constants
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.mainPage);
	    
		// Presenters
	    bindPresenter(MainPagePresenter.class, MainPagePresenter.MainPageView.class, MainPageViewImpl.class, MainPagePresenter.MainPageProxy.class);
	    
	}
	
}
