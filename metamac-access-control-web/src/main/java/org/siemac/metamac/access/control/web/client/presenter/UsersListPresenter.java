package org.siemac.metamac.access.control.web.client.presenter;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.List;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.web.client.AccessControlWeb;
import org.siemac.metamac.access.control.web.client.LoggedInGatekeeper;
import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.events.UpdateApplicationsEvent;
import org.siemac.metamac.access.control.web.client.events.UpdateApplicationsEvent.UpdateApplicationsHandler;
import org.siemac.metamac.access.control.web.client.events.UpdateRolesEvent;
import org.siemac.metamac.access.control.web.client.events.UpdateRolesEvent.UpdateRolesHandler;
import org.siemac.metamac.access.control.web.client.view.handlers.UsersListUiHandlers;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListAction;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListResult;
import org.siemac.metamac.access.control.web.shared.DeleteUserListAction;
import org.siemac.metamac.access.control.web.shared.DeleteUserListResult;
import org.siemac.metamac.access.control.web.shared.FindAccessByUserAction;
import org.siemac.metamac.access.control.web.shared.FindAccessByUserResult;
import org.siemac.metamac.access.control.web.shared.FindAllUsersAction;
import org.siemac.metamac.access.control.web.shared.FindAllUsersResult;
import org.siemac.metamac.access.control.web.shared.GetOperationPaginatedListAction;
import org.siemac.metamac.access.control.web.shared.GetOperationPaginatedListResult;
import org.siemac.metamac.access.control.web.shared.SaveAccessListAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessListResult;
import org.siemac.metamac.access.control.web.shared.SaveUserAction;
import org.siemac.metamac.access.control.web.shared.SaveUserResult;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.web.common.client.events.SetTitleEvent;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent;
import org.siemac.metamac.web.common.client.utils.WaitingAsyncCallbackHandlingError;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

public class UsersListPresenter extends Presenter<UsersListPresenter.UsersListView, UsersListPresenter.UsersListProxy> implements UsersListUiHandlers, UpdateRolesHandler, UpdateApplicationsHandler {

    private final DispatchAsync            dispatcher;

    private final ToolStripPresenterWidget toolStripPresenterWidget;

    @ProxyCodeSplit
    @NameToken(NameTokens.usersListPage)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface UsersListProxy extends Proxy<UsersListPresenter>, Place {
    }

    public interface UsersListView extends View, HasUiHandlers<UsersListUiHandlers> {

        void setUsersList(List<UserDto> userDtos);

        void onUserSaved(List<UserDto> userDtos, UserDto userDto);

        void setUserAccess(List<AccessDto> accessDtos);

        void onAccessSaved(List<AccessDto> accessDtos, AccessDto accessDto);

        void setRoleList(List<RoleDto> roles);

        void setApplicationList(List<AppDto> apps);

        void setOperations(List<ExternalItemDto> operations, int firstResult, int totalResults);

    }

    @Inject
    public UsersListPresenter(EventBus eventBus, UsersListView view, UsersListProxy proxy, DispatchAsync dispatcher, ToolStripPresenterWidget toolStripPresenterWidget) {
        super(eventBus, view, proxy);
        this.dispatcher = dispatcher;
        getView().setUiHandlers(this);
        this.toolStripPresenterWidget = toolStripPresenterWidget;
    }

    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContentToolBar = new Type<RevealContentHandler<?>>();

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetContextAreaContent, this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        retrieveUsersList();
    }

    @Override
    protected void onReset() {
        super.onReset();
        SetTitleEvent.fire(UsersListPresenter.this, getMessages().metamacAccessControl());
    }

    @Override
    protected void onReveal() {
        super.onReveal();
        setInSlot(TYPE_SetContextAreaContentToolBar, toolStripPresenterWidget);
    }

    private void retrieveUsersList() {
        dispatcher.execute(new FindAllUsersAction(), new WaitingAsyncCallbackHandlingError<FindAllUsersResult>(this) {

            @Override
            public void onWaitSuccess(FindAllUsersResult result) {
                getView().setUsersList(result.getUsers());
            }
        });
    }

    @Override
    public void deleteUsers(final List<Long> selectedUsers) {
        dispatcher.execute(new DeleteUserListAction(selectedUsers), new WaitingAsyncCallbackHandlingError<DeleteUserListResult>(this) {

            @Override
            public void onWaitSuccess(DeleteUserListResult result) {
                ShowMessageEvent.fireSuccessMessage(UsersListPresenter.this, selectedUsers.size() > 1 ? getMessages().usersDeleted() : getMessages().userDeleted());
            }

            @Override
            protected void afterResult() {
                retrieveUsersList();
            }
        });
    }

    @Override
    public void saveUser(UserDto userDto) {
        dispatcher.execute(new SaveUserAction(userDto), new WaitingAsyncCallbackHandlingError<SaveUserResult>(this) {

            @Override
            public void onWaitSuccess(SaveUserResult result) {
                final UserDto userSaved = result.getUserDto();
                ShowMessageEvent.fireSuccessMessage(UsersListPresenter.this, getMessages().userSaved());
                dispatcher.execute(new FindAllUsersAction(), new WaitingAsyncCallbackHandlingError<FindAllUsersResult>(UsersListPresenter.this) {

                    @Override
                    public void onWaitSuccess(FindAllUsersResult result) {
                        getView().onUserSaved(result.getUsers(), userSaved);
                    }
                });
            }
        });
    }

    @Override
    public void retrieveUserAccess(String username) {
        dispatcher.execute(new FindAccessByUserAction(username), new WaitingAsyncCallbackHandlingError<FindAccessByUserResult>(this) {

            @Override
            public void onWaitSuccess(FindAccessByUserResult result) {
                getView().setUserAccess(result.getAccessDtos());
            }
        });
    }

    @Override
    public void saveAccess(final List<AccessDto> accessDtos) {
        dispatcher.execute(new SaveAccessListAction(accessDtos), new WaitingAsyncCallbackHandlingError<SaveAccessListResult>(this) {

            @Override
            public void onWaitSuccess(SaveAccessListResult result) {

                final UserDto updatedUserDto = result.getUserDto();

                dispatcher.execute(new FindAllUsersAction(), new WaitingAsyncCallbackHandlingError<FindAllUsersResult>(UsersListPresenter.this) {

                    @Override
                    public void onWaitSuccess(FindAllUsersResult result) {
                        getView().onUserSaved(result.getUsers(), updatedUserDto);
                    }
                });

                retrieveUserAccess(accessDtos.get(0).getUser().getUsername());

                if (result.getNotificationException() != null) {
                    ShowMessageEvent.fireWarningMessageWithError(UsersListPresenter.this, AccessControlWeb.getMessages().accessSavedWithNotificationError(), result.getNotificationException());
                } else {
                    ShowMessageEvent.fireSuccessMessage(UsersListPresenter.this, getMessages().accessSaved());
                }
            }
        });
    }

    @Override
    public void deleteAccess(List<AccessDto> selectedAccess, final String username) {
        dispatcher.execute(new DeleteAccessListAction(selectedAccess), new WaitingAsyncCallbackHandlingError<DeleteAccessListResult>(this) {

            @Override
            public void onWaitSuccess(DeleteAccessListResult result) {
                if (result.getNotificationException() != null) {
                    ShowMessageEvent.fireWarningMessageWithError(UsersListPresenter.this, AccessControlWeb.getMessages().accessDeletedWithNotificationError(), result.getNotificationException());
                } else {
                    ShowMessageEvent.fireSuccessMessage(UsersListPresenter.this, getMessages().accessDeleted());
                }
            }

            @Override
            protected void afterResult() {
                retrieveUserAccess(username);
            }
        });
    }

    @ProxyEvent
    @Override
    public void onUpdateApplications(UpdateApplicationsEvent event) {
        getView().setApplicationList(event.getApplications());
    }

    @ProxyEvent
    @Override
    public void onUpdateRoles(UpdateRolesEvent event) {
        getView().setRoleList(event.getRoles());
    }

    @Override
    public void retrievePaginatedOperations(int firstResult, int maxResults, String operationCode) {
        dispatcher.execute(new GetOperationPaginatedListAction(firstResult, maxResults, operationCode), new WaitingAsyncCallbackHandlingError<GetOperationPaginatedListResult>(this) {

            @Override
            public void onWaitSuccess(GetOperationPaginatedListResult result) {
                getView().setOperations(result.getOperations(), result.getFirstResultOut(), result.getTotalResults());
            }
        });
    }
}
