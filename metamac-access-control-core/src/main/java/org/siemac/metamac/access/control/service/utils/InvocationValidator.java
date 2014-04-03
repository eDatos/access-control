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

public final class InvocationValidator {

    private InvocationValidator() {
    }

    // ------------------------------------------------------------------------------------
    // ROLES
    // ------------------------------------------------------------------------------------
    public static void checkFindRoleById(Long id) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();
        AccessControlValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);
        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkFindAllRoles() throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();
        // nothing to validate
        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateRole(Role role) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();
        checkRole(role, exceptions);
        AccessControlValidationUtils.checkMetadataEmpty(role.getId(), ServiceExceptionParameters.ROLE_ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkUpdateRole(Role role) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        checkRole(role, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(role.getId(), ServiceExceptionParameters.ROLE_ID, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(role.getUuid(), ServiceExceptionParameters.ROLE_UUID, exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkDeleteRole(Long id) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        AccessControlValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkFindRoleByCondition(List<ConditionalCriteria> conditions) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    // ------------------------------------------------------------------------------------
    // APPS
    // ------------------------------------------------------------------------------------

    public static void checkFindAppById(Long id) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        AccessControlValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateApp(App app) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        checkApp(app, exceptions);
        AccessControlValidationUtils.checkMetadataEmpty(app.getId(), ServiceExceptionParameters.APP_ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkUpdateApp(App app) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        checkApp(app, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(app.getId(), ServiceExceptionParameters.APP_ID, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(app.getUuid(), ServiceExceptionParameters.APP_UUID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkDeleteApp(Long appId) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        AccessControlValidationUtils.checkParameterRequired(appId, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindAllApps() throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindAppByCondition(List<ConditionalCriteria> conditions) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    // ------------------------------------------------------------------------------------
    // USERS
    // ------------------------------------------------------------------------------------

    public static void checkFindUserById(Long id) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        AccessControlValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateUser(User user) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        checkUser(user, exceptions);
        AccessControlValidationUtils.checkMetadataEmpty(user.getId(), ServiceExceptionParameters.USER_ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkUpdateUser(User user) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        checkUser(user, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(user.getId(), ServiceExceptionParameters.ID, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(user.getUuid(), ServiceExceptionParameters.USER_UUID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkDeleteUser(Long userId) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        AccessControlValidationUtils.checkParameterRequired(userId, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindAllUsers() throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindUserByCondition(List<ConditionalCriteria> conditions) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    // ------------------------------------------------------------------------------------
    // ACCESS
    // ------------------------------------------------------------------------------------

    public static void checkFindAccessById(Long id) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        AccessControlValidationUtils.checkParameterRequired(id, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkCreateAccess(Access access) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        checkAccess(access, exceptions);
        AccessControlValidationUtils.checkMetadataEmpty(access.getId(), ServiceExceptionParameters.ACCESS_ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkUpdateAccess(Access access) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        checkAccess(access, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(access.getId(), ServiceExceptionParameters.ACCESS_ID, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(access.getUuid(), ServiceExceptionParameters.ACCESS_UUID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkDeleteAccess(Long accessId) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        AccessControlValidationUtils.checkParameterRequired(accessId, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkRemoveAccess(Long accessId) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        AccessControlValidationUtils.checkParameterRequired(accessId, ServiceExceptionParameters.ID, exceptions);

        ExceptionUtils.throwIfException(exceptions);
    }

    public static void checkFindAllAccess() throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    public static void checkFindAccessByCondition(List<ConditionalCriteria> conditions) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        // nothing to validate

        ExceptionUtils.throwIfException(exceptions);

    }

    // ------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ------------------------------------------------------------------------------------
    private static void checkRole(Role role, List<MetamacExceptionItem> exceptions) {
        AccessControlValidationUtils.checkParameterRequired(role, ServiceExceptionParameters.ROLE, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(role.getCode(), ServiceExceptionParameters.ROLE_CODE, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(role.getTitle(), ServiceExceptionParameters.ROLE_TITLE, exceptions);
    }

    private static void checkApp(App app, List<MetamacExceptionItem> exceptions) {
        AccessControlValidationUtils.checkParameterRequired(app, ServiceExceptionParameters.APP, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(app.getCode(), ServiceExceptionParameters.APP_CODE, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(app.getTitle(), ServiceExceptionParameters.APP_TITLE, exceptions);
    }

    private static void checkUser(User user, List<MetamacExceptionItem> exceptions) {
        AccessControlValidationUtils.checkParameterRequired(user, ServiceExceptionParameters.USER, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(user.getUsername(), ServiceExceptionParameters.USER_USERNAME, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(user.getName(), ServiceExceptionParameters.USER_NAME, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(user.getSurname(), ServiceExceptionParameters.USER_SURNAME, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(user.getMail(), ServiceExceptionParameters.USER_MAIL, exceptions);
    }

    private static void checkAccess(Access access, List<MetamacExceptionItem> exceptions) {
        AccessControlValidationUtils.checkParameterRequired(access, ServiceExceptionParameters.ACCESS, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(access.getUser(), ServiceExceptionParameters.ACCESS_USER, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(access.getRole(), ServiceExceptionParameters.ACCESS_ROLE, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(access.getApp(), ServiceExceptionParameters.ACCESS_APP, exceptions);
        AccessControlValidationUtils.checkMetadataOptionalIsValid(access.getOperation(), ServiceExceptionParameters.ACCESS_OPERATION, exceptions);
        AccessControlValidationUtils.checkMetadataRequired(access.getSendEmail(), ServiceExceptionParameters.ACCESS_SEND_EMAIL, exceptions);
    }

}
