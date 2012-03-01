package org.siemac.metamac.access.control.web.client.presenter;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
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
import org.siemac.metamac.core.common.dto.serviceapi.ExternalItemBtDto;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.events.SetTitleEvent;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;


public class UsersListPresenter extends Presenter<UsersListPresenter.UsersListView, UsersListPresenter.UsersListProxy> implements UsersListUiHandlers, UpdateRolesHandler, UpdateApplicationsHandler, UpdateOperationsHandler {
    
    // private static Logger logger = Logger.getLogger(UsersListPresenter.class.getName());
    
    
    private final DispatchAsync dispatcher;
    
    @ProxyCodeSplit
    @NameToken(NameTokens.usersListPage)
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
    public UsersListPresenter(EventBus eventBus, UsersListView view, UsersListProxy proxy, DispatchAsync dispatcher) {
        super(eventBus, view, proxy);
        this.dispatcher = dispatcher;
        getView().setUiHandlers(this);
    }

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
    
    private void retrieveUsersList() {
        dispatcher.execute(new FindAllUsersAction(), new AsyncCallback<FindAllUsersResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUsers()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onSuccess(FindAllUsersResult result) {
                getView().setUsersList(result.getUsers());
            }
        });
    }

    @Override
    public void deleteUsers(final List<Long> selectedUsers) {
        dispatcher.execute(new DeleteUserListAction(selectedUsers), new AsyncCallback<DeleteUserListResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorDeletingUser()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onSuccess(DeleteUserListResult result) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getMessageList(selectedUsers.size() > 1 ? getMessages().usersDeleted() : getMessages().userDeleted()), MessageTypeEnum.SUCCESS);
                retrieveUsersList();
            }
        });
    }

    @Override
    public void saveUser(UserDto userDto) {
        dispatcher.execute(new SaveUserAction(userDto), new AsyncCallback<SaveUserResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorSavingUser()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onSuccess(SaveUserResult result) {
                final UserDto userSaved = result.getUserDto();
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getMessageList(getMessages().userSaved()), MessageTypeEnum.SUCCESS);
                dispatcher.execute(new FindAllUsersAction(), new AsyncCallback<FindAllUsersResult>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUsers()), MessageTypeEnum.ERROR);
                    }
                    @Override
                    public void onSuccess(FindAllUsersResult result) {
                        getView().onUserSaved(result.getUsers(), userSaved);
                    }
                });
            }
        });
    }
    
    @Override
    public void retrieveUserAccess(String username) {
        dispatcher.execute(new FindAccessByUserAction(username), new AsyncCallback<FindAccessByUserResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUserAccess()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onSuccess(FindAccessByUserResult result) {
                getView().setUserAccess(result.getAccessDtos());
            }
        });
    }

    @Override
    public void saveAccess(final AccessDto accessDto) {
        dispatcher.execute(new SaveAccessAction(accessDto), new AsyncCallback<SaveAccessResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorSavingRole()), MessageTypeEnum.ERROR); 
            }
            @Override
            public void onSuccess(SaveAccessResult result) {
                final AccessDto accessSaved = result.getAccess(); 
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getMessageList(getMessages().accessSaved()), MessageTypeEnum.SUCCESS);
                dispatcher.execute(new FindAccessByUserAction(accessDto.getUser().getUsername()), new AsyncCallback<FindAccessByUserResult>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUserAccess()), MessageTypeEnum.ERROR);
                    }
                    @Override
                    public void onSuccess(FindAccessByUserResult result) {
                        getView().onAccessSaved(result.getAccessDtos(), accessSaved);
                    }
                });
            }}
        );
    }
    
    @Override
    public void saveAccess(final List<AccessDto> accessDtos) {
        dispatcher.execute(new SaveAccessListAction(accessDtos), new AsyncCallback<SaveAccessListResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorSavingRole()), MessageTypeEnum.ERROR); 
            }
            @Override
            public void onSuccess(SaveAccessListResult result) {
                retrieveUserAccess(accessDtos.get(0).getUser().getUsername());
            }
        });
    }

    @Override
    public void deleteAccess(List<Long> selectedAccess, final String username) {
        dispatcher.execute(new DeleteAccessListAction(selectedAccess), new AsyncCallback<DeleteAccessListResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUserAccess()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onSuccess(DeleteAccessListResult result) {
                retrieveUserAccess(username);
                ShowMessageEvent.fire(UsersListPresenter.this, ErrorUtils.getMessageList(getMessages().accessDeleted()), MessageTypeEnum.SUCCESS);
            }}
        );
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
