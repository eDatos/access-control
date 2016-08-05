package org.siemac.metamac.access.control.web.client.gin;

import org.siemac.metamac.access.control.web.client.AccessControlPlaceManager;
import org.siemac.metamac.access.control.web.client.AccessControlWebConstants;
import org.siemac.metamac.access.control.web.client.AccessControlWebMessages;
import org.siemac.metamac.access.control.web.client.LoggedInGatekeeper;
import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.presenter.ErrorPagePresenter;
import org.siemac.metamac.access.control.web.client.presenter.MainPagePresenter;
import org.siemac.metamac.access.control.web.client.presenter.RoleHistoryPresenter;
import org.siemac.metamac.access.control.web.client.presenter.ToolStripPresenterWidget;
import org.siemac.metamac.access.control.web.client.presenter.UnauthorizedPagePresenter;
import org.siemac.metamac.access.control.web.client.presenter.UsersListPresenter;
import org.siemac.metamac.access.control.web.client.view.ErrorPageViewImpl;
import org.siemac.metamac.access.control.web.client.view.MainPageViewImpl;
import org.siemac.metamac.access.control.web.client.view.RoleHistoryViewImpl;
import org.siemac.metamac.access.control.web.client.view.ToolStripViewImpl;
import org.siemac.metamac.access.control.web.client.view.UnauthorizedPageViewImpl;
import org.siemac.metamac.access.control.web.client.view.UsersListViewImpl;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        // Default implementation of standard resources
        // |_ bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        // |_ bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
        // |_ bind(RootPresenter.class).asEagerSingleton();
        // |_ bind(PlaceManager.class).to(MyPlaceManager.class).in(Singleton.class);
        // |_ bind(GoogleAnalytics.class).to(GoogleAnalyticsImpl.class).in(Singleton.class);
        install(new DefaultModule(AccessControlPlaceManager.class));

        // Constants
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.usersListPage);

        // Gate keeper
        bind(LoggedInGatekeeper.class).in(Singleton.class);

        // Presenters
        bindPresenter(MainPagePresenter.class, MainPagePresenter.MainPageView.class, MainPageViewImpl.class, MainPagePresenter.MainPageProxy.class);
        bindPresenter(UsersListPresenter.class, UsersListPresenter.UsersListView.class, UsersListViewImpl.class, UsersListPresenter.UsersListProxy.class);
        bindPresenter(RoleHistoryPresenter.class, RoleHistoryPresenter.RoleHistoryView.class, RoleHistoryViewImpl.class, RoleHistoryPresenter.RoleHistoryProxy.class);

        // PresenterWidgets
        bindSingletonPresenterWidget(ToolStripPresenterWidget.class, ToolStripPresenterWidget.ToolStripView.class, ToolStripViewImpl.class);

        // Error pages
        bindPresenter(ErrorPagePresenter.class, ErrorPagePresenter.ErrorPageView.class, ErrorPageViewImpl.class, ErrorPagePresenter.ErrorPageProxy.class);
        bindPresenter(UnauthorizedPagePresenter.class, UnauthorizedPagePresenter.UnauthorizedPageView.class, UnauthorizedPageViewImpl.class, UnauthorizedPagePresenter.UnauthorizedPageProxy.class);

        bind(AccessControlWebConstants.class).in(Singleton.class);
        bind(AccessControlWebMessages.class).in(Singleton.class);

    }

}
