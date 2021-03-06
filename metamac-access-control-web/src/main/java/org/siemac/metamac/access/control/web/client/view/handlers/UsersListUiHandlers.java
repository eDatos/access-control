package org.siemac.metamac.access.control.web.client.view.handlers;

import java.util.List;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.UserDto;

import com.gwtplatform.mvp.client.UiHandlers;

public interface UsersListUiHandlers extends UiHandlers {

    void saveUser(UserDto userDto);

    void deleteUsers(List<Long> selectedUsers);

    void retrieveUserAccess(String username);

    void saveAccess(List<AccessDto> accessDto);

    void deleteAccess(List<AccessDto> selectedAccess, String username);

    void retrievePaginatedOperations(int firstResult, int maxResults, String operationCode);

}
