package org.siemac.metamac.access.control.error;

import org.siemac.metamac.core.common.error.CommonServiceExceptionType;

public class ServiceExceptionType extends CommonServiceExceptionType {

    // Extended Error Codes
    public static final CommonServiceExceptionType SERVICE_ROLE_NOT_OK      = create("0501", "exception.service.role.not_ok");
    public static final CommonServiceExceptionType SERVICE_ROLE_NOT_FOUND   = create("0502", "exception.service.role.not_found");

    public static final CommonServiceExceptionType SERVICE_APP_NOT_OK       = create("0601", "exception.service.app.not_ok");
    public static final CommonServiceExceptionType SERVICE_APP_NOT_FOUND    = create("0602", "exception.service.app.not_found");

    public static final CommonServiceExceptionType SERVICE_USER_NOT_OK      = create("0701", "exception.service.user.not_ok");
    public static final CommonServiceExceptionType SERVICE_USER_NOT_FOUND   = create("0702", "exception.service.user.not_found");

    public static final CommonServiceExceptionType SERVICE_ACCESS_NOT_OK    = create("0801", "exception.service.access.not_ok");
    public static final CommonServiceExceptionType SERVICE_ACCESS_NOT_FOUND = create("0802", "exception.service.access.not_found");
}
