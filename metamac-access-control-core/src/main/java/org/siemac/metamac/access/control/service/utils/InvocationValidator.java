package org.siemac.metamac.access.control.service.utils;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.error.ServiceExceptionParameters;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.exception.MetamacExceptionItem;
import org.siemac.metamac.core.common.exception.utils.ExceptionUtils;
import org.siemac.metamac.core.common.serviceimpl.utils.ValidationUtils;

public class InvocationValidator {

    // ------------------------------------------------------------------------------------
    // ROLES
    // ------------------------------------------------------------------------------------
    public static void checkFindRoleById(Long id, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkFindAllRoles(List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateRole(Role role, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkRole(role, exceptions);
        ValidationUtils.checkMetadataEmpty(role.getId(), ServiceExceptionParameters.ROLE_ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkUpdateRole(Role role, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkRole(role, exceptions);
        ValidationUtils.checkMetadataRequired(role.getId(), ServiceExceptionParameters.ROLE_ID, exceptions);
        ValidationUtils.checkMetadataRequired(role.getUuid(), ServiceExceptionParameters.ROLE_UUID, exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkDeleteRole(Long id, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkFindRoleByCondition(List<ConditionalCriteria> conditions, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    // ------------------------------------------------------------------------------------
    // APPS
    // ------------------------------------------------------------------------------------

    public static void checkFindAppById(Long id, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateApp(App app, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkApp(app, exceptions);
        ValidationUtils.checkMetadataEmpty(app.getId(), ServiceExceptionParameters.APP_ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkUpdateApp(App app, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkApp(app, exceptions);
        ValidationUtils.checkMetadataRequired(app.getId(), ServiceExceptionParameters.APP_ID, exceptions);
        ValidationUtils.checkMetadataRequired(app.getUuid(), ServiceExceptionParameters.APP_UUID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkDeleteApp(Long appId, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(appId, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindAllApps(List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindAppByCondition(List<ConditionalCriteria> conditions, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    // ------------------------------------------------------------------------------------
    // USERS
    // ------------------------------------------------------------------------------------

    public static void checkFindUserById(Long id, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateUser(User user, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkUser(user, exceptions);
        ValidationUtils.checkMetadataEmpty(user.getId(), ServiceExceptionParameters.USER_ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkUpdateUser(User user, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkUser(user, exceptions);
        ValidationUtils.checkMetadataRequired(user.getId(), ServiceExceptionParameters.ID, exceptions);
        ValidationUtils.checkMetadataRequired(user.getUuid(), ServiceExceptionParameters.USER_UUID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkDeleteUser(Long userId, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(userId, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindAllUsers(List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindUserByCondition(List<ConditionalCriteria> conditions, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    // ------------------------------------------------------------------------------------
    // ACCESS
    // ------------------------------------------------------------------------------------

    public static void checkFindAccessById(Long id, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateAccess(Access access, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkAccess(access, exceptions);
        ValidationUtils.checkMetadataEmpty(access.getId(), ServiceExceptionParameters.ACCESS_ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkUpdateAccess(Access access, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkAccess(access, exceptions);
        ValidationUtils.checkMetadataRequired(access.getId(), ServiceExceptionParameters.ACCESS_ID, exceptions);
        ValidationUtils.checkMetadataRequired(access.getUuid(), ServiceExceptionParameters.ACCESS_UUID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkDeleteAccess(Long accessId, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(accessId, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkRemoveAccess(Long accessId, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(accessId, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkFindAllAccess(List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindAccessByCondition(List<ConditionalCriteria> conditions, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    // ------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ------------------------------------------------------------------------------------
    private static void checkRole(Role role, List<MetamacExceptionItem> exceptions) {
        ValidationUtils.checkParameterRequired(role, ServiceExceptionParameters.ROLE, exceptions);
        ValidationUtils.checkMetadataRequired(role.getCode(), ServiceExceptionParameters.ROLE_CODE, exceptions);
        ValidationUtils.checkMetadataRequired(role.getTitle(), ServiceExceptionParameters.ROLE_TITLE, exceptions);
    }

    private static void checkApp(App app, List<MetamacExceptionItem> exceptions) {
        ValidationUtils.checkParameterRequired(app, ServiceExceptionParameters.APP, exceptions);
        ValidationUtils.checkMetadataRequired(app.getCode(), ServiceExceptionParameters.APP_CODE, exceptions);
        ValidationUtils.checkMetadataRequired(app.getTitle(), ServiceExceptionParameters.APP_TITLE, exceptions);
    }

    private static void checkUser(User user, List<MetamacExceptionItem> exceptions) {
        ValidationUtils.checkParameterRequired(user, ServiceExceptionParameters.USER, exceptions);
        ValidationUtils.checkMetadataRequired(user.getUsername(), ServiceExceptionParameters.USER_USERNAME, exceptions);
        ValidationUtils.checkMetadataRequired(user.getName(), ServiceExceptionParameters.USER_NAME, exceptions);
        ValidationUtils.checkMetadataRequired(user.getSurname(), ServiceExceptionParameters.USER_SURNAME, exceptions);
        ValidationUtils.checkMetadataRequired(user.getMail(), ServiceExceptionParameters.USER_MAIL, exceptions);
    }

    private static void checkAccess(Access access, List<MetamacExceptionItem> exceptions) {
        ValidationUtils.checkParameterRequired(access, ServiceExceptionParameters.ACCESS, exceptions);
        ValidationUtils.checkMetadataRequired(access.getUser(), ServiceExceptionParameters.ACCESS_USER, exceptions);
        ValidationUtils.checkMetadataRequired(access.getRole(), ServiceExceptionParameters.ACCESS_ROLE, exceptions);
        ValidationUtils.checkMetadataRequired(access.getApp(), ServiceExceptionParameters.ACCESS_APP, exceptions);
        if (access.getOperation() != null) {
            ValidationUtils.checkMetadataRequired(access.getOperation().getUrn(), ServiceExceptionParameters.ACCESS_OPERATION_URN, exceptions);
        }
    }

}
