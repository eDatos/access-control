package org.siemac.metamac.access.control.web.shared;

import java.util.List;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class SaveAccessList {

    @In(1)
    List<AccessDto>     accessToSave;

    @Out(1)
    UserDto             userDto;

    @Out(2)
    List<AccessDto>     accessDtos;

    @Out(3)
    MetamacWebException notificationException;

}
