package org.siemac.metamac.access.control.web.client.gin;

import org.siemac.metamac.access.control.web.client.AccessControlPlaceManager;
import org.siemac.metamac.access.control.web.client.AccessControlWebConstants;
import org.siemac.metamac.access.control.web.client.AccessControlWebMessages;
import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.presenter.AccessPresenter;
import org.siemac.metamac.access.control.web.client.presenter.MainPagePresenter;
import org.siemac.metamac.access.control.web.client.view.AccessViewImpl;
import org.siemac.metamac.access.control.web.client.view.MainPageViewImpl;

import com.google.inject.Singleton;
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
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.accessPage);
	    
		// Presenters
	    bindPresenter(MainPagePresenter.class, MainPagePresenter.MainPageView.class, MainPageViewImpl.class, MainPagePresenter.MainPageProxy.class);
	    bindPresenter(AccessPresenter.class, AccessPresenter.AccessView.class, AccessViewImpl.class, AccessPresenter.AccessProxy.class);
	    
	    bind(AccessControlWebConstants.class).in(Singleton.class);
	    bind(AccessControlWebMessages.class).in(Singleton.class);
	    
	}
	
}
