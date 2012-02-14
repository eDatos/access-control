package org.siemac.metamac.access.control.error;

import org.siemac.metamac.core.common.error.CommonServiceExceptionType;

public class ServiceExceptionType extends CommonServiceExceptionType {

    // Extended Error Codes
    public static final CommonServiceExceptionType SERVICE_ROLE_NOT_OK      = create("1001", "exception.service.role.not_ok");
    public static final CommonServiceExceptionType SERVICE_ROLE_NOT_FOUND   = create("1002", "exception.service.role.not_found");

    public static final CommonServiceExceptionType SERVICE_APP_NOT_OK       = create("1101", "exception.service.app.not_ok");
    public static final CommonServiceExceptionType SERVICE_APP_NOT_FOUND    = create("1102", "exception.service.app.not_found");

    public static final CommonServiceExceptionType SERVICE_USER_NOT_OK      = create("1201", "exception.service.user.not_ok");
    public static final CommonServiceExceptionType SERVICE_USER_NOT_FOUND   = create("1202", "exception.service.user.not_found");

    public static final CommonServiceExceptionType SERVICE_ACCESS_NOT_OK    = create("1301", "exception.service.access.not_ok");
    public static final CommonServiceExceptionType SERVICE_ACCESS_NOT_FOUND = create("1302", "exception.service.access.not_found");
}
