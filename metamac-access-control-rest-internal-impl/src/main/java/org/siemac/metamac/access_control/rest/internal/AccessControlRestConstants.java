package org.siemac.metamac.access_control.rest.internal;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.rest.api.constants.RestApiConstants;

public class AccessControlRestConstants extends RestApiConstants {

    public static final ServiceContext SERVICE_CONTEXT = new ServiceContext("restAccessControl", "restAccessControl", "restAccessControl");

    public static String               API_NAME        = "access-control";
    public static String               API_VERSION_1_0 = "v1.0";

}
