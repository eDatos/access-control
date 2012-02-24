package org.siemac.metamac.access.control.web.shared;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class SaveAccessList {

    @In(1)
    List<AccessDto> accessToSave;
 
    @Out(1)
    List<AccessDto> accessDtos;
    
}
