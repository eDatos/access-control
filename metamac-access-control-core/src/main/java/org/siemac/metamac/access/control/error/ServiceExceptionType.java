package org.siemac.metamac.access.control.error;

import org.siemac.metamac.core.common.exception.CommonServiceExceptionType;

public class ServiceExceptionType extends CommonServiceExceptionType {

    // Extended Error Codes
    public static final CommonServiceExceptionType ROLE_NOT_FOUND                       = create("exception.acl.role.not_found");
    public static final CommonServiceExceptionType ROLE_ALREADY_EXIST_CODE_DUPLICATED   = create("exception.acl.role.already_exists.code_duplicated");

    public static final CommonServiceExceptionType APP_NOT_FOUND                        = create("exception.acl.app.not_found");
    public static final CommonServiceExceptionType APP_ALREADY_EXIST_CODE_DUPLICATED    = create("exception.acl.app.already_exists.code_duplicated");

    public static final CommonServiceExceptionType USER_NOT_FOUND                       = create("exception.acl.user.not_found");
    public static final CommonServiceExceptionType USER_ALREADY_EXIST_CODE_DUPLICATED   = create("exception.acl.user.already_exists.code_duplicated");

    public static final CommonServiceExceptionType ACCESS_NOT_FOUND                     = create("exception.acl.access.not_found");
    public static final CommonServiceExceptionType ACCESS_ALREADY_EXIST_CODE_DUPLICATED = create("exception.acl.access.already_exists.code_duplicated");
}
