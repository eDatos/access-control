package org.siemac.metamac.access_control.rest.internal;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.rest.api.constants.RestApiConstants;

public class AccessControlRestInternalConstants extends RestApiConstants {

    public static final ServiceContext SERVICE_CONTEXT    = new ServiceContext("restAccessControl", "restAccessControl", "restAccessControl");

    public static String               API_NAME           = "access-control";
    public static String               API_VERSION_1_0    = "v1.0";

    public static String               KIND_USERS         = API_NAME + KIND_SEPARATOR + "users";
    public static String               KIND_USER          = API_NAME + KIND_SEPARATOR + "user";

    public static String               LINK_SUBPATH_USERS = "users";
}
