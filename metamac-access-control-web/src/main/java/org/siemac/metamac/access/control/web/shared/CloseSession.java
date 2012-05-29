package org.siemac.metamac.access.control.web.shared;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class CloseSession {

    @Out(1)
    String logoutPageUrl;

}
