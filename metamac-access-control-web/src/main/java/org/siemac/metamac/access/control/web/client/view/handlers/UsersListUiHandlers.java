package org.siemac.metamac.access.control.web.client.view.handlers;

import java.util.List;

import org.siemac.metamac.domain.access.control.dto.AccessDto;
import org.siemac.metamac.domain.access.control.dto.UserDto;

import com.gwtplatform.mvp.client.UiHandlers;

public interface UsersListUiHandlers extends UiHandlers {

    void saveUser(UserDto userDto);
    void deleteUsers(List<Long> selectedUsers);

    void retrieveUserAccess(String username);
    void saveAccess(AccessDto accessDto);
    void saveAccess(List<AccessDto> accessDto);
    void deleteAccess(List<Long> selectedAccess, String username);

    void retrievePaginatedOperations(int firstResult, int maxResults);

}
