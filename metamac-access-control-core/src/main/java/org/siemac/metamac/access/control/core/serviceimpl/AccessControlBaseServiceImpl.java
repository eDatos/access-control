package org.siemac.metamac.access.control.core.serviceimpl;

import static org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteriaBuilder.criteriaFor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.joda.time.DateTime;
import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.AccessRepository;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.AppRepository;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.RoleRepository;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.domain.UserRepository;
import org.siemac.metamac.access.control.core.exception.AccessNotFoundException;
import org.siemac.metamac.access.control.core.exception.AppNotFoundException;
import org.siemac.metamac.access.control.core.exception.RoleNotFoundException;
import org.siemac.metamac.access.control.core.exception.UserNotFoundException;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
import org.siemac.metamac.access.control.service.utils.InvocationValidator;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of AccessControlBaseService.
 */
@Service("accessControlBaseService")
public class AccessControlBaseServiceImpl extends AccessControlBaseServiceImplBase {

    @Autowired
    private RoleRepository   roleRepository;
    @Autowired
    private AppRepository    appRepository;
    @Autowired
    private UserRepository   userRepository;
    @Autowired
    private AccessRepository accessRepository;

    public AccessControlBaseServiceImpl() {
    }

    // ------------------------------------------------------------------------------------
    // ROLES
    // ------------------------------------------------------------------------------------
    public Role findRoleById(ServiceContext ctx, Long id) throws MetamacException {
        // Validations
        InvocationValidator.checkFindRoleById(id, null);

        // Repository Operation
        try {
            return roleRepository.findById(id);
        } catch (RoleNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.ROLE_NOT_FOUND, id);
        }
    }

    public Role createRole(ServiceContext ctx, Role entity) throws MetamacException {
        // Validations
        InvocationValidator.checkCreateRole(entity, null);
        validateRoleCodeUnique(ctx, entity.getCode(), null);

        // Repository operation
        return roleRepository.save(entity);
    }

    public Role updateRole(ServiceContext ctx, Role entity) throws MetamacException {
        // Validations
        InvocationValidator.checkUpdateRole(entity, null);
        validateRoleCodeUnique(ctx, entity.getCode(), entity.getId());

        // Repository operation
        return roleRepository.save(entity);

    }

    public void deleteRole(ServiceContext ctx, Long roleId) throws MetamacException {
        // Validations
        InvocationValidator.checkDeleteRole(roleId, null);

        // Repository operation
        Role role = findRoleById(ctx, roleId);
        roleRepository.delete(role);
    }

    public List<Role> findAllRoles(ServiceContext ctx) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAllRoles(null);

        // Repository operation
        return roleRepository.findAll();
    }

    public List<Role> findRoleByCondition(ServiceContext ctx, List<ConditionalCriteria> conditions) throws MetamacException {
        // Validations
        InvocationValidator.checkFindRoleByCondition(conditions, null);

        // Initializations
        initCriteriaConditions(conditions, Role.class);

        // Repository operation
        return roleRepository.findByCondition(conditions);
    }

    // ------------------------------------------------------------------------------------
    // APPS
    // ------------------------------------------------------------------------------------
    public App findAppById(ServiceContext ctx, Long id) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAppById(id, null);

        // Repository Operation
        try {
            return appRepository.findById(id);
        } catch (AppNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.APP_NOT_FOUND, id);
        }
    }

    public App createApp(ServiceContext ctx, App entity) throws MetamacException {
        // Validations
        InvocationValidator.checkCreateApp(entity, null);
        validateAppCodeUnique(ctx, entity.getCode(), null);

        // Repository operation
        return appRepository.save(entity);
    }

    public App updateApp(ServiceContext ctx, App entity) throws MetamacException {
        // Validations
        InvocationValidator.checkUpdateApp(entity, null);
        validateAppCodeUnique(ctx, entity.getCode(), entity.getId());

        // Repository operation
        return appRepository.save(entity);
    }

    public void deleteApp(ServiceContext ctx, Long appId) throws MetamacException {
        // Validations
        InvocationValidator.checkDeleteApp(appId, null);

        // Repository operation
        App app = findAppById(ctx, appId);
        appRepository.delete(app);
    }

    public List<App> findAllApps(ServiceContext ctx) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAllApps(null);

        // Repository operation
        return appRepository.findAll();
    }

    public List<App> findAppByCondition(ServiceContext ctx, List<ConditionalCriteria> conditions) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAppByCondition(conditions, null);

        // Initializations
        initCriteriaConditions(conditions, App.class);

        // Repository operation
        return appRepository.findByCondition(conditions);

    }

    // ------------------------------------------------------------------------------------
    // USERS
    // ------------------------------------------------------------------------------------

    public User findUserById(ServiceContext ctx, Long id) throws MetamacException {
        // Validations
        InvocationValidator.checkFindUserById(id, null);

        // Repository Operation
        try {
            return userRepository.findById(id);
        } catch (UserNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.USER_NOT_FOUND, id);
        }
    }

    public User createUser(ServiceContext ctx, User entity) throws MetamacException {
        // Validations
        InvocationValidator.checkCreateUser(entity, null);
        validateUserUsernameUnique(ctx, entity.getUsername(), null);

        // Repository operation
        return userRepository.save(entity);
    }

    public User updateUser(ServiceContext ctx, User entity) throws MetamacException {
        // Validations
        InvocationValidator.checkUpdateUser(entity, null);
        validateUserUsernameUnique(ctx, entity.getUsername(), entity.getId());

        // Repository operation
        return userRepository.save(entity);

    }

    public void deleteUser(ServiceContext ctx, Long userId) throws MetamacException {
        // Validations
        InvocationValidator.checkDeleteUser(userId, null);

        // Repository operation
        User user = findUserById(ctx, userId);
        userRepository.delete(user);
    }

    public List<User> findAllUsers(ServiceContext ctx) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAllUsers(null);

        // Repository operation
        return userRepository.findAll();
    }

    public List<User> findUserByCondition(ServiceContext ctx, List<ConditionalCriteria> conditions) throws MetamacException {
        // Validations
        InvocationValidator.checkFindUserByCondition(conditions, null);

        // Initializations
        initCriteriaConditions(conditions, User.class);

        // Repository operation
        return userRepository.findByCondition(conditions);
    }

    // ------------------------------------------------------------------------------------
    // ACCESS
    // ------------------------------------------------------------------------------------
    public Access findAccessById(ServiceContext ctx, Long id) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAccessById(id, null);

        // Repository Operation
        try {
            return accessRepository.findById(id);
        } catch (AccessNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.ACCESS_NOT_FOUND, id);
        }
    }

    public Access createAccess(ServiceContext ctx, Access entity) throws MetamacException {
        // Validations
        InvocationValidator.checkCreateAccess(entity, null);
        validateAccessUnique(ctx, entity, null);

        // Repository operation
        return accessRepository.save(entity);
    }

    public Access updateAccess(ServiceContext ctx, Access entity) throws MetamacException {
        // Validations
        InvocationValidator.checkUpdateAccess(entity, null);
        validateRemovalDate(ctx, entity, null);
        validateAccessUnique(ctx, entity, null);

        // Repository operation
        return accessRepository.save(entity);
    }

    public void deleteAccess(ServiceContext ctx, Long accessId) throws MetamacException {
        // Validations
        InvocationValidator.checkDeleteAccess(accessId, null);

        // Repository operation
        Access access = findAccessById(ctx, accessId);
        accessRepository.delete(access);
    }

    public List<Access> findAllAccess(ServiceContext ctx) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAllAccess(null);

        // Repository operation
        return accessRepository.findAll();
    }

    public List<Access> findAccessByCondition(ServiceContext ctx, List<ConditionalCriteria> conditions) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAccessByCondition(conditions, null);

        // Initializations
        initCriteriaConditions(conditions, Access.class);

        // Repository operation
        return accessRepository.findByCondition(conditions);
    }

    public List<Access> findAccessByCondition(ServiceContext ctx, String roleCode, String appCode, String username, String operationUrn, Boolean removalAccess) throws MetamacException {
        List<ConditionalCriteria> conditions = new ArrayList<ConditionalCriteria>();

        // Role condition
        if (!StringUtils.isEmpty(roleCode)) {
            conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.role().code(), roleCode));
        }

        // App condition
        if (!StringUtils.isEmpty(appCode)) {
            conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.app().code(), appCode));
        }

        // User condition
        if (!StringUtils.isEmpty(username)) {
            conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.user().username(), username));
        }

        // Operation condition
        if (!StringUtils.isEmpty(operationUrn)) {
            conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.operation().urn(), operationUrn));
        }

        // Removal date conditions
        if (Boolean.FALSE.equals(removalAccess)) {
            conditions.add(ConditionalCriteria.isNull(org.siemac.metamac.access.control.core.domain.AccessProperties.removalDate()));
        } else if (Boolean.TRUE.equals(removalAccess)) {
            conditions.add(ConditionalCriteria.isNotNull(org.siemac.metamac.access.control.core.domain.AccessProperties.removalDate()));
        }

        List<Access> access = findAccessByCondition(ctx, conditions);

        return access;
    }

    @Override
    public void removeAccess(ServiceContext ctx, Long accessId) throws MetamacException {
        // Validations
        InvocationValidator.checkRemoveAccess(accessId, null);

        // Retrieve entity
        Access access = findAccessById(ctx, accessId);

        // Validate if it's already removed
        validateRemovalDate(ctx, access, null);

        // Set attributes
        access.setRemovalDate(new DateTime());

        // Repository operation
        accessRepository.save(access);

    }

    // ----------------------------------------------------------------------
    // VALIDATORS
    // ----------------------------------------------------------------------

    private void validateRoleCodeUnique(ServiceContext ctx, String code, Long actualId) throws MetamacException {
        List<ConditionalCriteria> conditions = criteriaFor(Role.class).withProperty(org.siemac.metamac.access.control.core.domain.RoleProperties.code()).ignoreCaseEq(code).build();

        if (actualId != null) {
            conditions.add(ConditionalCriteria.not(ConditionalCriteria.equal(org.siemac.metamac.access.control.core.domain.RoleProperties.id(), actualId)));
        }

        List<Role> roles = findRoleByCondition(ctx, conditions);

        if (roles != null) {
            if (roles.size() == 1) {
                throw new MetamacException(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED, code);
            } else if (roles.size() > 1) {
                throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one role with code " + code);
            }
        }
    }

    private void validateAppCodeUnique(ServiceContext ctx, String code, Long actualId) throws MetamacException {
        List<ConditionalCriteria> conditions = criteriaFor(App.class).withProperty(org.siemac.metamac.access.control.core.domain.AppProperties.code()).ignoreCaseEq(code).build();

        if (actualId != null) {
            conditions.add(ConditionalCriteria.not(ConditionalCriteria.equal(org.siemac.metamac.access.control.core.domain.AppProperties.id(), actualId)));
        }

        List<App> apps = findAppByCondition(ctx, conditions);

        if (apps != null) {
            if (apps.size() == 1) {
                throw new MetamacException(ServiceExceptionType.APP_ALREADY_EXIST_CODE_DUPLICATED, code);
            } else if (apps.size() > 1) {
                throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one app with code code " + code);
            }
        }

    }

    private void validateUserUsernameUnique(ServiceContext ctx, String username, Long actualId) throws MetamacException {
        List<ConditionalCriteria> conditions = criteriaFor(User.class).withProperty(org.siemac.metamac.access.control.core.domain.UserProperties.username()).ignoreCaseEq(username).build();

        if (actualId != null) {
            conditions.add(ConditionalCriteria.not(ConditionalCriteria.equal(org.siemac.metamac.access.control.core.domain.UserProperties.id(), actualId)));
        }

        List<User> users = findUserByCondition(ctx, conditions);

        if (users != null) {
            if (users.size() == 1) {
                throw new MetamacException(ServiceExceptionType.USER_ALREADY_EXIST_CODE_DUPLICATED, username);
            } else if (users.size() > 1) {
                throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one user with username code " + username);
            }
        }
    }

    private void validateAccessUnique(ServiceContext ctx, Access entity, Object object) throws MetamacException {
        List<ConditionalCriteria> conditions = new ArrayList<ConditionalCriteria>();

        String operationUrn = null;
        if (entity.getOperation() != null) {
            operationUrn = entity.getOperation().getUrn();
        }

        // Role condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.role().code(), entity.getRole().getCode()));

        // App condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.app().code(), entity.getApp().getCode()));

        // User condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.user().username(), entity.getUser().getUsername()));

        // Operation condition
        if (operationUrn != null) {
            conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.operation().urn(), operationUrn));
        } else {
            conditions.add(ConditionalCriteria.isNull(org.siemac.metamac.access.control.core.domain.AccessProperties.operation().urn()));
        }

        // Removal date
        conditions.add(ConditionalCriteria.isNull(org.siemac.metamac.access.control.core.domain.AccessProperties.removalDate()));

        List<Access> access = findAccessByCondition(ctx, conditions);

        if (access != null) {
            if (entity.getId() == null) {
                if (access.size() == 1) {
                    throw new MetamacException(ServiceExceptionType.ACCESS_ALREADY_EXIST_CODE_DUPLICATED, entity.getRole().getCode(), entity.getApp().getCode(), entity.getUser().getUsername(),
                            operationUrn);
                } else if (access.size() > 1) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one access with values. Role: " + entity.getRole().getCode() + " App: " + entity.getApp().getCode() + " User: "
                            + entity.getUser().getUsername() + " Operation: " + operationUrn);
                }
            } else {
                if (access.size() == 2) {
                    throw new MetamacException(ServiceExceptionType.ACCESS_ALREADY_EXIST_CODE_DUPLICATED, entity.getRole().getCode(), entity.getApp().getCode(), entity.getUser().getUsername(),
                            operationUrn);
                } else if (access.size() > 2) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one access with values. Role: " + entity.getRole().getCode() + " App: " + entity.getApp().getCode() + " User: "
                            + entity.getUser().getUsername() + " Operation: " + operationUrn);
                }
            }
        }

    }

    private void validateRemovalDate(ServiceContext ctx, Access entity, Object object) throws MetamacException {
        if (entity.getRemovalDate() != null) {
            throw new MetamacException(ServiceExceptionType.ACCESS_REMOVED, entity.getId());
        }
    }

    // ----------------------------------------------------------------------
    // UTILS
    // ----------------------------------------------------------------------
    @SuppressWarnings({"rawtypes", "unchecked"})
    private List<ConditionalCriteria> initCriteriaConditions(List<ConditionalCriteria> conditions, Class entityClass) {
        List<ConditionalCriteria> conditionsEntity = ConditionalCriteriaBuilder.criteriaFor(entityClass).build();
        if (conditions != null) {
            conditionsEntity.addAll(conditions);
        }
        return conditionsEntity;
    }

}
