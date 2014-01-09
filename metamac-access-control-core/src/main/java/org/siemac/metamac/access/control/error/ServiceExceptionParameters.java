package org.siemac.metamac.access.control.error;

import org.siemac.metamac.core.common.exception.CommonServiceExceptionParameters;

public class ServiceExceptionParameters extends CommonServiceExceptionParameters {

    public static final String ROLE                 = "parameter.acl.role";
    public static final String USER                 = "parameter.acl.user";
    public static final String APP                  = "parameter.acl.app";
    public static final String ACCESS               = "parameter.acl.access";

    public static final String ROLE_ID              = "parameter.acl.role.id";
    public static final String ROLE_UUID            = "parameter.acl.role.uuid";
    public static final String ROLE_CODE            = "parameter.acl.role.code";
    public static final String ROLE_TITLE           = "parameter.acl.role.title";

    public static final String USER_ID              = "parameter.acl.user.id";
    public static final String USER_UUID            = "parameter.acl.user.uuid";
    public static final String USER_USERNAME        = "parameter.acl.user.username";
    public static final String USER_NAME            = "parameter.acl.user.name";
    public static final String USER_SURNAME         = "parameter.acl.user.surname";
    public static final String USER_MAIL            = "parameter.acl.user.mail";

    public static final String APP_ID               = "parameter.acl.app.id";
    public static final String APP_UUID             = "parameter.acl.app.uuid";
    public static final String APP_CODE             = "parameter.acl.app.code";
    public static final String APP_TITLE            = "parameter.acl.app.title";

    public static final String ACCESS_ID            = "parameter.acl.access.id";
    public static final String ACCESS_UUID          = "parameter.acl.access.uuid";
    public static final String ACCESS_USER          = "parameter.acl.access.user";
    public static final String ACCESS_ROLE          = "parameter.acl.access.role";
    public static final String ACCESS_APP           = "parameter.acl.access.app";
    public static final String ACCESS_OPERATION     = "parameter.acl.access.operation";
    public static final String ACCESS_OPERATION_URN = "parameter.acl.access.operation.urn";
    public static final String ACCESS_SEND_EMAIL    = "parameter.acl.access.sendEmail";

}
