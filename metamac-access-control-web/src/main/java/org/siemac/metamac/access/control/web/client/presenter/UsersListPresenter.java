package org.siemac.metamac.access.control.web.client.presenter;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.List;

import org.siemac.metamac.access.control.web.client.LoggedInGatekeeper;
import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.events.UpdateApplicationsEvent;
import org.siemac.metamac.access.control.web.client.events.UpdateApplicationsEvent.UpdateApplicationsHandler;
import org.siemac.metamac.access.control.web.client.events.UpdateOperationsEvent;
import org.siemac.metamac.access.control.web.client.events.UpdateOperationsEvent.UpdateOperationsHandler;
import org.siemac.metamac.access.control.web.client.events.UpdateRolesEvent;
import org.siemac.metamac.access.control.web.client.events.UpdateRolesEvent.UpdateRolesHandler;
import org.siemac.metamac.access.control.web.client.utils.ErrorUtils;
import org.siemac.metamac.access.control.web.client.view.handlers.UsersListUiHandlers;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListAction;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListResult;
import org.siemac.metamac.access.control.web.shared.DeleteUserListAction;
import org.siemac.metamac.access.control.web.shared.DeleteUserListResult;
import org.siemac.metamac.access.control.web.shared.FindAccessByUserAction;
import org.siemac.metamac.access.control.web.shared.FindAccessByUserResult;
import org.siemac.metamac.access.control.web.shared.FindAllUsersAction;
import org.siemac.metamac.access.control.web.shared.FindAllUsersResult;
import org.siemac.metamac.access.control.web.shared.SaveAccessAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessListAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessListResult;
import org.siemac.metamac.access.control.web.shared.SaveAccessResult;
import org.siemac.metamac.access.control.web.shared.SaveUserAction;
import org.siemac.metamac.access.control.web.shared.SaveUserResult;
import org.siemac.metamac.core.common.dto.ExternalItemBtDto;
import org.siemac.metamac.domain.access.control.dto.AccessDto;
import org.siemac.metamac.domain.access.control.dto.AppDto;
import org.siemac.metamac.domain.access.control.dto.RoleDto;
import org.siemac.metamac.domain.access.control.dto.UserDto;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.events.SetTitleEvent;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent;
import org.siemac.metamac.web.common.client.widgets.WaitingAsyncCallback;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
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

public class UsersListPresenter extends Presenter<UsersListPresenter.UsersListView, UsersListPresenter.UsersListProxy>
        implements
            UsersListUiHandlers,
            UpdateRolesHandler,
            UpdateApplicationsHandler,
            UpdateOperationsHandler {

    // private static Logger logger = Logger.getLogger(UsersListPresenter.class.getName());

    private final DispatchAsync      dispatcher;

    private ToolStripPresenterWidget toolStripPresenterWidget;

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
        void setOperationList(List<ExternalItemBtDto> operations);
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
        dispatcher.execute(new FindAllUsersAction(), new WaitingAsyncCallback<FindAllUsersResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUsers()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(FindAllUsersResult result) {
                getView().setUsersList(result.getUsers());
            }
        });
    }

    @Override
    public void deleteUsers(final List<Long> selectedUsers) {
        dispatcher.execute(new DeleteUserListAction(selectedUsers), new WaitingAsyncCallback<DeleteUserListResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorDeletingUser()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(DeleteUserListResult result) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getMessageList(selectedUsers.size() > 1 ? getMessages().usersDeleted() : getMessages().userDeleted()),
                        MessageTypeEnum.SUCCESS);
                retrieveUsersList();
            }
        });
    }

    @Override
    public void saveUser(UserDto userDto) {
        dispatcher.execute(new SaveUserAction(userDto), new WaitingAsyncCallback<SaveUserResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorSavingUser()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(SaveUserResult result) {
                final UserDto userSaved = result.getUserDto();
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getMessageList(getMessages().userSaved()), MessageTypeEnum.SUCCESS);
                dispatcher.execute(new FindAllUsersAction(), new WaitingAsyncCallback<FindAllUsersResult>() {

                    @Override
                    public void onWaitFailure(Throwable caught) {
                        ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUsers()), MessageTypeEnum.ERROR);
                    }
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
        dispatcher.execute(new FindAccessByUserAction(username), new WaitingAsyncCallback<FindAccessByUserResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUserAccess()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(FindAccessByUserResult result) {
                getView().setUserAccess(result.getAccessDtos());
            }
        });
    }

    @Override
    public void saveAccess(final AccessDto accessDto) {
        dispatcher.execute(new SaveAccessAction(accessDto), new WaitingAsyncCallback<SaveAccessResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorSavingRole()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(SaveAccessResult result) {
                final AccessDto accessSaved = result.getAccess();
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getMessageList(getMessages().accessSaved()), MessageTypeEnum.SUCCESS);
                dispatcher.execute(new FindAccessByUserAction(accessDto.getUser().getUsername()), new WaitingAsyncCallback<FindAccessByUserResult>() {

                    @Override
                    public void onWaitFailure(Throwable caught) {
                        ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUserAccess()), MessageTypeEnum.ERROR);
                    }
                    @Override
                    public void onWaitSuccess(FindAccessByUserResult result) {
                        getView().onAccessSaved(result.getAccessDtos(), accessSaved);
                    }
                });
            }
        });
    }

    @Override
    public void saveAccess(final List<AccessDto> accessDtos) {
        dispatcher.execute(new SaveAccessListAction(accessDtos), new WaitingAsyncCallback<SaveAccessListResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorSavingRole()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(SaveAccessListResult result) {
                retrieveUserAccess(accessDtos.get(0).getUser().getUsername());
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getMessageList(getMessages().accessSaved()), MessageTypeEnum.SUCCESS);
            }
        });
    }

    @Override
    public void deleteAccess(List<Long> selectedAccess, final String username) {
        dispatcher.execute(new DeleteAccessListAction(selectedAccess), new WaitingAsyncCallback<DeleteAccessListResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUserAccess()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onWaitSuccess(DeleteAccessListResult result) {
                retrieveUserAccess(username);
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getMessageList(getMessages().accessDeleted()), MessageTypeEnum.SUCCESS);
            }
        });
    }

    @ProxyEvent
    @Override
    public void onUpdateOperations(UpdateOperationsEvent event) {
        getView().setOperationList(event.getOperations());
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

}
