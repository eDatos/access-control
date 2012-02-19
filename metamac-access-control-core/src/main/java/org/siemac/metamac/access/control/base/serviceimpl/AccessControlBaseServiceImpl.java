package org.siemac.metamac.access.control.base.serviceimpl;

import static org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteriaBuilder.criteriaFor;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.AccessRepository;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.AppRepository;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.RoleRepository;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.access.control.base.domain.UserRepository;
import org.siemac.metamac.access.control.base.exception.AccessNotFoundException;
import org.siemac.metamac.access.control.base.exception.AppNotFoundException;
import org.siemac.metamac.access.control.base.exception.RoleNotFoundException;
import org.siemac.metamac.access.control.base.exception.UserNotFoundException;
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
        // TODO: Comprobar que si está asociado a un acceso no puede borrarse

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
        // TODO: Comprobar que si está asociado a un acceso no puede borrarse

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
        // TODO: Comprobar que si está asociado a un acceso no puede borrarse

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

        // Repository operation
        return accessRepository.save(entity);
    }

    public void deleteAccess(ServiceContext ctx, Long accessId) throws MetamacException {
        // Validations
        InvocationValidator.checkDeleteAccess(accessId, null);
        // TODO: Comprobar que si está asociado a un acceso no puede borrarse

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

    // ----------------------------------------------------------------------
    // VALIDATORS
    // ----------------------------------------------------------------------

    private void validateRoleCodeUnique(ServiceContext ctx, String code, Long actualId) throws MetamacException {
        List<ConditionalCriteria> condition = criteriaFor(Role.class).withProperty(org.siemac.metamac.access.control.base.domain.RoleProperties.code()).ignoreCaseEq(code).build();

        List<Role> roles = findRoleByCondition(ctx, condition);

        if (roles != null) {
            if (actualId == null) {
                if (roles.size() == 1) {
                    throw new MetamacException(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED, code);
                } else if (roles.size() > 1) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one role with code code " + code);
                }
            } else {
                if (roles.size() == 2) {
                    throw new MetamacException(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED, code);
                } else if (roles.size() > 2) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one role with code code " + code);
                }
            }
        }
    }

    private void validateAppCodeUnique(ServiceContext ctx, String code, Long actualId) throws MetamacException {
        List<ConditionalCriteria> condition = criteriaFor(App.class).withProperty(org.siemac.metamac.access.control.base.domain.AppProperties.code()).ignoreCaseEq(code).build();
        List<App> apps = findAppByCondition(ctx, condition);

        if (apps != null) {
            if (actualId == null) {
                if (apps.size() == 1) {
                    throw new MetamacException(ServiceExceptionType.APP_ALREADY_EXIST_CODE_DUPLICATED, code);
                } else if (apps.size() > 1) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one app with code code " + code);
                }
            } else {
                if (apps.size() == 2) {
                    throw new MetamacException(ServiceExceptionType.APP_ALREADY_EXIST_CODE_DUPLICATED, code);
                } else if (apps.size() > 2) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one app with code code " + code);
                }
            }
        }

    }

    private void validateUserUsernameUnique(ServiceContext ctx, String username, Long actualId) throws MetamacException {
        List<ConditionalCriteria> condition = criteriaFor(User.class).withProperty(org.siemac.metamac.access.control.base.domain.UserProperties.username()).ignoreCaseEq(username).build();
        List<User> users = findUserByCondition(ctx, condition);

        if (users != null) {
            if (actualId == null) {
                if (users.size() == 1) {
                    throw new MetamacException(ServiceExceptionType.USER_ALREADY_EXIST_CODE_DUPLICATED, username);
                } else if (users.size() > 1) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one user with username code " + username);
                }
            } else {
                if (users.size() == 2) {
                    throw new MetamacException(ServiceExceptionType.USER_ALREADY_EXIST_CODE_DUPLICATED, username);
                } else if (users.size() > 2) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one user with username code " + username);
                }
            }
        }
    }
    
    
    private void validateAccessUnique(ServiceContext ctx, Access entity, Object object) throws MetamacException {
        List<ConditionalCriteria> conditions = new ArrayList<ConditionalCriteria>();
        
        // Role condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.base.domain.AccessProperties.role().code(), entity.getRole().getCode()));
        
        // App condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.base.domain.AccessProperties.app().code(), entity.getApp().getCode()));
        
        // User condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.base.domain.AccessProperties.user().username(), entity.getUser().getUsername()));
        
        // Operation condition
        conditions.add(ConditionalCriteria.ignoreCaseEqual(org.siemac.metamac.access.control.base.domain.AccessProperties.operation().codeId(), entity.getOperation().getCodeId()));
        
        List<Access> access = findAccessByCondition(ctx, conditions);
        
        if (access != null) {
            if (entity.getId() == null) {
                if (access.size() == 1) {
                    throw new MetamacException(ServiceExceptionType.ACCESS_ALREADY_EXIST_CODE_DUPLICATED, entity.getRole().getCode(), entity.getApp().getCode(), entity.getUser().getUsername(), entity.getOperation().getCodeId());
                } else if (access.size() > 1) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one access with values. Role: " + entity.getRole().getCode() + " App: " + entity.getApp().getCode() + " User: " +  entity.getUser().getUsername() + " Operation: " +  entity.getOperation().getCodeId());
                }
            } else {
                if (access.size() == 2) {
                    throw new MetamacException(ServiceExceptionType.ACCESS_ALREADY_EXIST_CODE_DUPLICATED, entity.getRole().getCode(), entity.getApp().getCode(), entity.getUser().getUsername(), entity.getOperation().getCodeId());
                } else if (access.size() > 2) {
                    throw new MetamacException(ServiceExceptionType.UNKNOWN, "More than one access with values. Role: " + entity.getRole().getCode() + " App: " + entity.getApp().getCode() + " User: " +  entity.getUser().getUsername() + " Operation: " +  entity.getOperation().getCodeId());
                }
            }
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
