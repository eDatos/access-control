package org.siemac.metamac.access.control.service.utils;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
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

        ValidationUtils.checkParameterRequired(id, "ID", exceptions);

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
        ValidationUtils.checkMetadataEmpty(role.getId(), "ROLE.ID", exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkUpdateRole(Role role, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkRole(role, exceptions);
        ValidationUtils.checkMetadataRequired(role.getId(), "ROLE.ID", exceptions);
        ValidationUtils.checkMetadataRequired(role.getUuid(), "ROLE.UUID", exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkDeleteRole(Long id, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(id, "ID", exceptions);

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

        ValidationUtils.checkParameterRequired(id, "ID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateApp(App app, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkApp(app, exceptions);
        ValidationUtils.checkMetadataEmpty(app.getId(), "APP.ID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkUpdateApp(App app, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkApp(app, exceptions);
        ValidationUtils.checkMetadataRequired(app.getId(), "APP.ID", exceptions);
        ValidationUtils.checkMetadataRequired(app.getUuid(), "APP.UUID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkDeleteApp(Long appId, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(appId, "ID", exceptions);

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

        ValidationUtils.checkParameterRequired(id, "ID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateUser(User user, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkUser(user, exceptions);
        ValidationUtils.checkMetadataEmpty(user.getId(), "USER.ID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkUpdateUser(User user, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkUser(user, exceptions);
        ValidationUtils.checkMetadataRequired(user.getId(), "USER.ID", exceptions);
        ValidationUtils.checkMetadataRequired(user.getUuid(), "USER.UUID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkDeleteUser(Long userId, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(userId, "ID", exceptions);

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

        ValidationUtils.checkParameterRequired(id, "ID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateAccess(Access access, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkAccess(access, exceptions);
        ValidationUtils.checkMetadataEmpty(access.getId(), "ACCESS.ID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkUpdateAccess(Access access, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        checkAccess(access, exceptions);
        ValidationUtils.checkMetadataRequired(access.getId(), "ACCESS.ID", exceptions);
        ValidationUtils.checkMetadataRequired(access.getUuid(), "ACCESS.UUID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkDeleteAccess(Long accessId, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(accessId, "ID", exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkRemoveAccess(Long accessId, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        ValidationUtils.checkParameterRequired(accessId, "ID", exceptions);

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
        ValidationUtils.checkParameterRequired(role, "ROLE", exceptions);
        ValidationUtils.checkMetadataRequired(role.getCode(), "ROLE.CODE", exceptions);
        ValidationUtils.checkMetadataRequired(role.getTitle(), "ROLE.TITLE", exceptions);
    }

    private static void checkApp(App app, List<MetamacExceptionItem> exceptions) {
        ValidationUtils.checkParameterRequired(app, "APP", exceptions);
        ValidationUtils.checkMetadataRequired(app.getCode(), "APP.CODE", exceptions);
        ValidationUtils.checkMetadataRequired(app.getTitle(), "APP.TITLE", exceptions);
    }

    private static void checkUser(User user, List<MetamacExceptionItem> exceptions) {
        ValidationUtils.checkParameterRequired(user, "USER", exceptions);
        ValidationUtils.checkMetadataRequired(user.getUsername(), "USER.USERNAME", exceptions);
        ValidationUtils.checkMetadataRequired(user.getName(), "USER.NAME", exceptions);
        ValidationUtils.checkMetadataRequired(user.getSurname(), "USER.SURNAME", exceptions);
        ValidationUtils.checkMetadataRequired(user.getMail(), "USER.MAIL", exceptions);
    }

    private static void checkAccess(Access access, List<MetamacExceptionItem> exceptions) {
        ValidationUtils.checkParameterRequired(access, "ACCESS", exceptions);
        ValidationUtils.checkMetadataRequired(access.getUser(), "ACCESS.USER", exceptions);
        ValidationUtils.checkMetadataRequired(access.getRole(), "ACCESS.ROLE", exceptions);
        ValidationUtils.checkMetadataRequired(access.getApp(), "ACCESS.APP", exceptions);
    }

}
