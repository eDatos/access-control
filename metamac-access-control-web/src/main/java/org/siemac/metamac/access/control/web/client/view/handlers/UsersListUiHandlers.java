package org.siemac.metamac.access.control.web.client.view.handlers;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;

import com.gwtplatform.mvp.client.UiHandlers;

public interface UsersListUiHandlers extends UiHandlers {

    void saveUser(UserDto userDto);
    void deleteUsers(List<Long> selectedUsers);

    void retrieveUserAccess(String username);
    void saveAccess(AccessDto accessDto);
    void saveAccess(List<AccessDto> accessDto);
    void deleteAccess(List<Long> selectedAccess, String username);

}
