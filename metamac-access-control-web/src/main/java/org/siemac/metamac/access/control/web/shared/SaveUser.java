package org.siemac.metamac.access.control.web.shared;

import org.siemac.metamac.domain.access.control.dto.UserDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class SaveUser {

    @In(1)
    UserDto userToSave;

    @Out(1)
    UserDto userDto;

}
