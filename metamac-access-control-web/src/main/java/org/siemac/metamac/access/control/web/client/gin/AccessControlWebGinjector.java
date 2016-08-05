package org.siemac.metamac.access.control.web.client.gin;

import org.siemac.metamac.access.control.web.client.AccessControlWebConstants;
import org.siemac.metamac.access.control.web.client.AccessControlWebMessages;
import org.siemac.metamac.access.control.web.client.LoggedInGatekeeper;
import org.siemac.metamac.access.control.web.client.presenter.ErrorPagePresenter;
import org.siemac.metamac.access.control.web.client.presenter.MainPagePresenter;
import org.siemac.metamac.access.control.web.client.presenter.RoleHistoryPresenter;
import org.siemac.metamac.access.control.web.client.presenter.UnauthorizedPagePresenter;
import org.siemac.metamac.access.control.web.client.presenter.UsersListPresenter;
import org.siemac.metamac.web.common.client.gin.MetamacWebGinjector;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;

@GinModules({DispatchAsyncModule.class, ClientModule.class})
public interface AccessControlWebGinjector extends MetamacWebGinjector {

    LoggedInGatekeeper getLoggedInGatekeeper();

    Provider<MainPagePresenter> getMainPagePresenter();

    AsyncProvider<UsersListPresenter> getUsersListPresenter();
    AsyncProvider<RoleHistoryPresenter> getRoleHistoryPresenter();

    AsyncProvider<ErrorPagePresenter> getErrorPagePresenter();
    AsyncProvider<UnauthorizedPagePresenter> getUnauthorizedPagePresenter();

    public AccessControlWebConstants getAccessControlWebConstants();
    public AccessControlWebMessages getAccessControlWebMessages();

}
