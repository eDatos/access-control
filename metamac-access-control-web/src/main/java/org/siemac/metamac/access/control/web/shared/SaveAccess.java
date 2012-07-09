package org.siemac.metamac.access.control.web.shared;

import org.siemac.metamac.access.control.core.dto.AccessDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class SaveAccess {

    @In(1)
    AccessDto accessToSave;

    @Out(1)
    AccessDto access;

}
