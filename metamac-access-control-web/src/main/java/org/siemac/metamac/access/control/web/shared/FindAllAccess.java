package org.siemac.metamac.access.control.web.shared;

import java.util.List;

import org.siemac.metamac.domain.access.control.dto.AccessDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class FindAllAccess {

    @Out(1)
    List<AccessDto> access;

}
