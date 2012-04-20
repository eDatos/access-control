package org.siemac.metamac.access.control.web.shared;

import org.siemac.metamac.domain.access.control.dto.RoleDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class SaveRole {

    @In(1)
    RoleDto roleToSave;

    @Out(1)
    RoleDto roleDto;

}
