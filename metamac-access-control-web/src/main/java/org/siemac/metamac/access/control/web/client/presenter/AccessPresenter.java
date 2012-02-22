package org.siemac.metamac.access.control.web.client.presenter;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.access.control.web.client.NameTokens;
import org.siemac.metamac.access.control.web.client.view.handlers.AccessUiHandlers;
import org.siemac.metamac.access.control.web.shared.DeleteUserListAction;
import org.siemac.metamac.access.control.web.shared.DeleteUserListResult;
import org.siemac.metamac.access.control.web.shared.FindAllUsersAction;
import org.siemac.metamac.access.control.web.shared.FindAllUsersResult;
import org.siemac.metamac.access.control.web.shared.SaveUserAction;
import org.siemac.metamac.access.control.web.shared.SaveUserResult;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.events.SetTitleEvent;
import org.siemac.metamac.web.common.client.events.ShowMessageEvent;
import org.siemac.metamac.web.common.client.utils.ErrorUtils;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;


public class AccessPresenter extends Presenter<AccessPresenter.AccessView, AccessPresenter.AccessProxy> implements AccessUiHandlers {
    
    private final DispatchAsync dispatcher;
    
    @ProxyCodeSplit
    @NameToken(NameTokens.accessPage)
    public interface AccessProxy extends Proxy<AccessPresenter>, Place {
    }
    
    public interface AccessView extends View, HasUiHandlers<AccessUiHandlers> {
        void setUsersList(List<UserDto> userDtos);
        void onUserSaved(UserDto userDto);
    }
    
    @Inject
    public AccessPresenter(EventBus eventBus, AccessView view, AccessProxy proxy, DispatchAsync dispatcher) {
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
        SetTitleEvent.fire(AccessPresenter.this, getMessages().metamacAccessControl());
    }
    
    private void retrieveUsersList() {
        dispatcher.execute(new FindAllUsersAction(), new AsyncCallback<FindAllUsersResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(AccessPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorRetrievingUsers()), MessageTypeEnum.ERROR);
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
                ShowMessageEvent.fire(AccessPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorDeletingUser()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onSuccess(DeleteUserListResult result) {
                ShowMessageEvent.fire(AccessPresenter.this, ErrorUtils.getMessageList(selectedUsers.size() > 1 ? getMessages().usersDeleted() : getMessages().userDeleted()), MessageTypeEnum.SUCCESS);
                retrieveUsersList();
            }
        });
    }

    @Override
    public void saveUser(UserDto userDto) {
        dispatcher.execute(new SaveUserAction(userDto), new AsyncCallback<SaveUserResult>() {
            @Override
            public void onFailure(Throwable caught) {
                ShowMessageEvent.fire(AccessPresenter.this, ErrorUtils.getErrorMessages(caught, getMessages().errorSavingUser()), MessageTypeEnum.ERROR);
            }
            @Override
            public void onSuccess(SaveUserResult result) {
                ShowMessageEvent.fire(AccessPresenter.this, ErrorUtils.getMessageList(getMessages().userSaved()), MessageTypeEnum.SUCCESS);
                getView().onUserSaved(result.getUserDto());
            }
        });
    }
    
}
