package org.siemac.metamac.access.control.web.client;

import org.siemac.metamac.access.control.web.client.gin.DefaultPlace;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

// see ClientModule
// bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.signInPage);

public class AccessControlPlaceManager extends PlaceManagerImpl {

    private final PlaceRequest defaultPlaceRequest;

    @Inject
    public AccessControlPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter, @DefaultPlace String defaultNameToken) {
        super(eventBus, tokenFormatter);
        this.defaultPlaceRequest = new PlaceRequest(defaultNameToken);
    }

    @Override
    public void revealDefaultPlace() {
        revealPlace(defaultPlaceRequest);
    }

    @Override
    public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
        PlaceRequest placeRequest = new PlaceRequest(NameTokens.errorPage);
        placeRequest = placeRequest.with("redirect", unauthorizedHistoryToken);
        revealPlace(placeRequest);
    }

    @Override
    public void revealErrorPlace(String invalidHistoryToken) {
        PlaceRequest placeRequest = new PlaceRequest(NameTokens.errorPage);
        revealPlace(placeRequest);
    }
}
