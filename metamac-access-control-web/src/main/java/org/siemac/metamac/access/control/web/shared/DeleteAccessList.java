package org.siemac.metamac.access.control.web.shared;

import java.util.List;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class DeleteAccessList {

    @In(1)
    List<AccessDto>     access;

    @Out(1)
    MetamacWebException notificationException;
}
