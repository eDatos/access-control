package org.siemac.metamac.access.control.web.shared;

import org.siemac.metamac.access.control.dto.serviceapi.AppDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class SaveApp {

    @In(1)
    AppDto appToSave;

    @Out(1)
    AppDto appDto;

}
