package org.siemac.metamac.access.control.web.client.gin;

import org.siemac.metamac.access.control.web.client.AccessControlWebConstants;
import org.siemac.metamac.access.control.web.client.AccessControlWebMessages;
import org.siemac.metamac.access.control.web.client.presenter.RoleHistoryPresenter;
import org.siemac.metamac.access.control.web.client.presenter.UsersListPresenter;
import org.siemac.metamac.access.control.web.client.presenter.MainPagePresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
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
    AsyncProvider<UsersListPresenter> getUsersListPresenter();
    AsyncProvider<RoleHistoryPresenter> getRoleHistoryPresenter();

    public AccessControlWebConstants getAccessControlWebConstants();
    public AccessControlWebMessages getAccessControlWebMessages();

}
