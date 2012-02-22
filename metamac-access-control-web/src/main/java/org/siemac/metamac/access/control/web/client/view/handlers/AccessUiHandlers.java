package org.siemac.metamac.access.control.web.client.view.handlers;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;


public interface AccessUiHandlers extends UiHandlers {

    void deleteUsers(List<Long> selectedUsers);
    
}
