package org.siemac.metamac.access.control.web.shared;

import java.util.List;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;

@GenDispatch(isSecure = false)
public class DeleteRoleList {

    @In(1)
    List<Long> roleIds;

}
