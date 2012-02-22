package org.siemac.metamac.access.control.web.client.view.handlers;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.UserDto;

import com.gwtplatform.mvp.client.UiHandlers;


public interface AccessUiHandlers extends UiHandlers {

    void saveUser(UserDto userDto);
    void deleteUsers(List<Long> selectedUsers);
    
}
