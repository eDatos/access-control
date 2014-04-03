package org.siemac.metamac.access.control.core.serviceimpl;

import static org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteriaBuilder.criteriaFor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.fornax.cartridges.sculptor.framework.domain.PagedResult;
import org.fornax.cartridges.sculptor.framework.domain.PagingParameter;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of AccessControlBaseService.
 */
@Service("accessControlBaseService")
public class AccessControlBaseServiceImpl extends AccessControlBaseServiceImplBase {

    private static final Logger LOG = LoggerFactory.getLogger(AccessControlBaseServiceImpl.class);

    @Autowired
    private RoleRepository      roleRepository;
    @Autowired
    private AppRepository       appRepository;
    @Autowired
    private UserRepository      userRepository;
    @Autowired
    private AccessRepository    accessRepository;

    public AccessControlBaseServiceImpl() {
    }

    // ------------------------------------------------------------------------------------
    // ROLES
    // ------------------------------------------------------------------------------------
    @Override
    public Role findRoleById(ServiceContext ctx, Long id) throws MetamacException {
        // Validations
        InvocationValidator.checkFindRoleById(id);

        // Repository Operation
        try {
            return roleRepository.findById(id);
        } catch (RoleNotFoundException e) {
            LOG.error("findRoleById: role not found", e);
            throw new MetamacException(ServiceExceptionType.ROLE_NOT_FOUND, id);
        }
    }

    @Override
    public Role createRole(ServiceContext ctx, Role entity) throws MetamacException {
        // Validations
        InvocationValidator.checkCreateRole(entity);
        validateRoleCodeUnique(ctx, entity.getCode(), null);

        // Repository operation
        return roleRepository.save(entity);
    }

    @Override
    public Role updateRole(ServiceContext ctx, Role entity) throws MetamacException {
        // Validations
        InvocationValidator.checkUpdateRole(entity);
        validateRoleCodeUnique(ctx, entity.getCode(), entity.getId());

        // Repository operation
        return roleRepository.save(entity);

    }

    @Override
    public void deleteRole(ServiceContext ctx, Long roleId) throws MetamacException {
        // Validations
        InvocationValidator.checkDeleteRole(roleId);

        // Repository operation
        Role role = findRoleById(ctx, roleId);
        roleRepository.delete(role);
    }

    @Override
    public List<Role> findAllRoles(ServiceContext ctx) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAllRoles();

        // Repository operation
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findRoleByCondition(ServiceContext ctx, List<ConditionalCriteria> conditions) throws MetamacException {
        // Validations
        InvocationValidator.checkFindRoleByCondition(conditions);

        // Initializations
        initCriteriaConditions(conditions, Role.class);

        // Repository operation
        return roleRepository.findByCondition(conditions);
    }

    // ------------------------------------------------------------------------------------
    // APPS
    // ------------------------------------------------------------------------------------
    @Override
    public App findAppById(ServiceContext ctx, Long id) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAppById(id);

        // Repository Operation
        try {
            return appRepository.findById(id);
        } catch (AppNotFoundException e) {
            LOG.error("findAppById: app not found", e);
            throw new MetamacException(ServiceExceptionType.APP_NOT_FOUND, id);
        }
    }

    @Override
    public App createApp(ServiceContext ctx, App entity) throws MetamacException {
        // Validations
        InvocationValidator.checkCreateApp(entity);
        validateAppCodeUnique(ctx, entity.getCode(), null);

        // Repository operation
        return appRepository.save(entity);
    }

    @Override
    public App updateApp(ServiceContext ctx, App entity) throws MetamacException {
        // Validations
        InvocationValidator.checkUpdateApp(entity);
        validateAppCodeUnique(ctx, entity.getCode(), entity.getId());

        // Repository operation
        return appRepository.save(entity);
    }

    @Override
    public void deleteApp(ServiceContext ctx, Long appId) throws MetamacException {
        // Validations
        InvocationValidator.checkDeleteApp(appId);

        // Repository operation
        App app = findAppById(ctx, appId);
        appRepository.delete(app);
    }

    @Override
    public List<App> findAllApps(ServiceContext ctx) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAllApps();

        // Repository operation
        return appRepository.findAll();
    }

    @Override
    public List<App> findAppByCondition(ServiceContext ctx, List<ConditionalCriteria> conditions) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAppByCondition(conditions);

        // Initializations
        initCriteriaConditions(conditions, App.class);

        // Repository operation
        return appRepository.findByCondition(conditions);

    }

    // ------------------------------------------------------------------------------------
    // USERS
    // ------------------------------------------------------------------------------------

    @Override
    public User findUserById(ServiceContext ctx, Long id) throws MetamacException {
        // Validations
        InvocationValidator.checkFindUserById(id);

        // Repository Operation
        try {
            return userRepository.findById(id);
        } catch (UserNotFoundException e) {
            LOG.error("findUserById: user not found", e);
            throw new MetamacException(ServiceExceptionType.USER_NOT_FOUND, id);
        }
    }

    @Override
    public User createUser(ServiceContext ctx, User entity) throws MetamacException {
        // Validations
        InvocationValidator.checkCreateUser(entity);

        // Set username in lowercase
        entity.setUsername(entity.getUsername().toLowerCase());

        // Validate username
        validateUserUsernameUnique(ctx, entity.getUsername(), null);

        // Repository operation
        return userRepository.save(entity);
    }

    @Override
    public User updateUser(ServiceContext ctx, User entity) throws MetamacException {
        // Validations
        InvocationValidator.checkUpdateUser(entity);

        // Set username in lowercase
        entity.setUsername(entity.getUsername().toLowerCase());

        // Validate username
        validateUserUsernameUnique(ctx, entity.getUsername(), entity.getId());

        // Repository operation
        return userRepository.save(entity);

    }

    @Override
    public void deleteUser(ServiceContext ctx, Long userId) throws MetamacException {
        // Validations
        InvocationValidator.checkDeleteUser(userId);

        // Repository operation
        User user = findUserById(ctx, userId);
        userRepository.delete(user);
    }

    @Override
    public List<User> findAllUsers(ServiceContext ctx) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAllUsers();

        // Repository operation
        return userRepository.findAll();
    }

    @Override
    public List<User> findUserByCondition(ServiceContext ctx, List<ConditionalCriteria> conditions) throws MetamacException {
        // Validations
        InvocationValidator.checkFindUserByCondition(conditions);

        // Initializations
        initCriteriaConditions(conditions, User.class);

        // Repository operation
        return userRepository.findByCondition(conditions);
    }

    @Override
    public PagedResult<User> findUserByCondition(ServiceContext ctx, List<ConditionalCriteria> conditions, PagingParameter pagingParameter) throws MetamacException {
        // Validations
        InvocationValidator.checkFindUserByCondition(conditions);

        // Initializations
        initCriteriaConditions(conditions, User.class);

        // Repository operation
        return userRepository.findByCondition(conditions, pagingParameter);
    }

    // ------------------------------------------------------------------------------------
    // ACCESS
    // ------------------------------------------------------------------------------------
    @Override
    public Access findAccessById(ServiceContext ctx, Long id) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAccessById(id);

        // Repository Operation
        try {
            return accessRepository.findById(id);
        } catch (AccessNotFoundException e) {
            LOG.error("findAccessById: access not found", e);
            throw new MetamacException(ServiceExceptionType.ACCESS_NOT_FOUND, id);
        }
    }

    @Override
    public Access createAccess(ServiceContext ctx, Access entity) throws MetamacException {
        // Validations
        InvocationValidator.checkCreateAccess(entity);
        validateAccessUnique(ctx, entity);

        // Repository operation
        return accessRepository.save(entity);
    }

    @Override
    public Access updateAccess(ServiceContext ctx, Access entity) throws MetamacException {
        // Validations
        InvocationValidator.checkUpdateAccess(entity);
        validateRemovalDate(entity);
        validateAccessUnique(ctx, entity);

        // Repository operation
        return accessRepository.save(entity);
    }

    @Override
    public void deleteAccess(ServiceContext ctx, Long accessId) throws MetamacException {
        // Validations
        InvocationValidator.checkDeleteAccess(accessId);

        // Repository operation
        Access access = findAccessById(ctx, accessId);
        accessRepository.delete(access);
    }

    @Override
    public List<Access> findAllAccess(ServiceContext ctx) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAllAccess();

        // Repository operation
        return accessRepository.findAll();
    }

    @Override
    public List<Access> findAccessByCondition(ServiceContext ctx, List<ConditionalCriteria> conditions) throws MetamacException {
        // Validations
        InvocationValidator.checkFindAccessByCondition(conditions);

        // Initializations
        initCriteriaConditions(conditions, Access.class);

        // Repository operation
        return accessRepository.findByCondition(conditions);
    }

    @Override
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
        InvocationValidator.checkRemoveAccess(accessId);

        // Retrieve entity
        Access access = findAccessById(ctx, accessId);

        // Validate if it's already removed
        validateRemovalDate(access);

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

    private void validateAccessUnique(ServiceContext ctx, Access entity) throws MetamacException {
        List<ConditionalCriteria> conditions = new ArrayList<ConditionalCriteria>();

        String operationUrn = fillOperationUrn(entity);
        populateConditionsForValidateAccessUnique(entity, conditions, operationUrn);
        List<Access> access = findAccessByCondition(ctx, conditions);

        if (access != null) {
            if (entity.getId() == null) {
                if (access.size() == 1) {
                    throwExceptionForDuplicatedAccess(entity, operationUrn);
                } else if (access.size() > 1) {
                    throwMetamacExceptionForDuplicatedAccess(entity, operationUrn);
                }
            } else {
                if (access.size() == 2) {
                    throwExceptionForDuplicatedAccess(entity, operationUrn);
                } else if (access.size() > 2) {
                    throwMetamacExceptionForDuplicatedAccess(entity, operationUrn);
                }
            }
        }

    }

    private String fillOperationUrn(Access entity) {
        if (entity.getOperation() != null) {
            return entity.getOperation().getUrn();
        } else {
            return StringUtils.EMPTY;
        }
    }

    private void populateConditionsForValidateAccessUnique(Access entity, List<ConditionalCriteria> conditions, String operationUrn) {
        // Role condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.role().code(), entity.getRole().getCode()));

        // App condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.app().code(), entity.getApp().getCode()));

        // User condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.user().username(), entity.getUser().getUsername()));

        // Operation condition
        if (StringUtils.isNotBlank(operationUrn)) {
            conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.core.domain.AccessProperties.operation().urn(), operationUrn));
        } else {
            conditions.add(ConditionalCriteria.isNull(org.siemac.metamac.access.control.core.domain.AccessProperties.operation().urn()));
        }

        // Removal date
        conditions.add(ConditionalCriteria.isNull(org.siemac.metamac.access.control.core.domain.AccessProperties.removalDate()));
    }

    private void throwMetamacExceptionForDuplicatedAccess(Access entity, String operationUrn) throws MetamacException {
        throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one access with values. Role: " + entity.getRole().getCode() + " App: " + entity.getApp().getCode() + " User: "
                + entity.getUser().getUsername() + " Operation: " + operationUrn);
    }

    private void throwExceptionForDuplicatedAccess(Access entity, String operationUrn) throws MetamacException {
        if (StringUtils.isEmpty(operationUrn)) {
            throw new MetamacException(ServiceExceptionType.ACCESS_ALREADY_EXIST_WITHOUT_OPERATION, entity.getRole().getCode(), entity.getApp().getCode(), entity.getUser().getUsername());
        } else {
            throw new MetamacException(ServiceExceptionType.ACCESS_ALREADY_EXIST_WITH_OPERATION, entity.getRole().getCode(), entity.getApp().getCode(), entity.getUser().getUsername(), operationUrn);
        }
    }

    private void validateRemovalDate(Access entity) throws MetamacException {
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
