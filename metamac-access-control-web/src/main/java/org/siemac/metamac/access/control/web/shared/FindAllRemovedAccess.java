package org.siemac.metamac.access.control.web.shared;

import java.util.List;

import org.siemac.metamac.access.control.core.dto.AccessDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class FindAllRemovedAccess {

    @Out(1)
    List<AccessDto> accessDtos;

}
