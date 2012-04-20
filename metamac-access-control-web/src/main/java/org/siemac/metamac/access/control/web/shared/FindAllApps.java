package org.siemac.metamac.access.control.web.shared;

import java.util.List;

import org.siemac.metamac.domain.access.control.dto.AppDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class FindAllApps {

    @Out(1)
    List<AppDto> apps;

}
