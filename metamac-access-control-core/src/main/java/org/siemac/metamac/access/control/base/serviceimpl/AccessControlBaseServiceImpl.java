package org.siemac.metamac.access.control.base.serviceimpl;

import static org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteriaBuilder.criteriaFor;

import java.util.List;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
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

    public Role findRoleById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return roleRepository.findById(id);
        } catch (RoleNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.ROLE_NOT_FOUND);
        }
    }

    public Role createRole(ServiceContext ctx, Role entity) throws MetamacException {
//        // Validation of parameters
//        InvocationValidator.checkCreateRole(roleDto, exceptions);
//        validateRoleCodeUnique(roleDto.getCode(), exceptions);
        
           return roleRepository.save(entity);
    }
    
    public Role updateRole(ServiceContext ctx, Role entity) throws MetamacException {
//      // Validation of parameters
//      InvocationValidator.checkCreateRole(roleDto, exceptions);
//      validateRoleCodeUnique(roleDto.getCode(), exceptions);
      
         return roleRepository.save(entity);
  }

    public void deleteRole(ServiceContext ctx, Long roleId) throws MetamacException {
        Role role = findRoleById(ctx, roleId);
        roleRepository.delete(role);
    }

    public List<Role> findAllRoles(ServiceContext ctx) throws MetamacException {
        return roleRepository.findAll();
    }

    public List<Role> findRoleByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) throws MetamacException {
        return roleRepository.findByCondition(condition);
    }

    public App findAppById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return appRepository.findById(id);
        } catch (AppNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.APP_NOT_FOUND);
        }
    }

    public App createApp(ServiceContext ctx, App entity) throws MetamacException {
        return appRepository.save(entity);
    }
    
    public App updateApp(ServiceContext ctx, App entity) throws MetamacException {
        return appRepository.save(entity);
    }

    public void deleteApp(ServiceContext ctx, Long appId) throws MetamacException {
        App app = findAppById(ctx, appId);
        appRepository.delete(app);
    }

    public List<App> findAllApps(ServiceContext ctx) throws MetamacException {
        return appRepository.findAll();
    }

    public List<App> findAppByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) throws MetamacException {
        return appRepository.findByCondition(condition);

    }

    public User findUserById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return userRepository.findById(id);
        } catch (UserNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.USER_NOT_FOUND);
        }
    }

    public User createUser(ServiceContext ctx, User entity) throws MetamacException {
        return userRepository.save(entity);
    }
    
    public User updateUser(ServiceContext ctx, User entity) throws MetamacException {
        return userRepository.save(entity);
    }

    public void deleteUser(ServiceContext ctx, Long userId) throws MetamacException {
        User user = findUserById(ctx, userId);
        userRepository.delete(user);
    }

    public List<User> findAllUsers(ServiceContext ctx) throws MetamacException {
        return userRepository.findAll();
    }

    public List<User> findUserByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) throws MetamacException {
        return userRepository.findByCondition(condition);
    }

    public Access findAccessById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return accessRepository.findById(id);
        } catch (AccessNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.ACCESS_NOT_FOUND);
        }
    }

    public Access createAccess(ServiceContext ctx, Access entity) throws MetamacException {
        return accessRepository.save(entity);
    }
    
    public Access updateAccess(ServiceContext ctx, Access entity) throws MetamacException {
        return accessRepository.save(entity);
    }

    public void deleteAccess(ServiceContext ctx, Long accessId) throws MetamacException {
        Access access = findAccessById(ctx, accessId);
        accessRepository.delete(access);
    }

    public List<Access> findAllAccess(ServiceContext ctx) throws MetamacException {
        return accessRepository.findAll();
    }

    public List<Access> findAccessByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) throws MetamacException {
        return accessRepository.findByCondition(condition);
    }
    
    
    
    // ----------------------------------------------------------------------
    //                              VALIDATORS
    // ----------------------------------------------------------------------
    
    
    private void validateRoleCodeUnique(ServiceContext ctx, String code, String actualId) throws MetamacException {
        List<ConditionalCriteria> condition = criteriaFor(Role.class).withProperty(org.siemac.metamac.access.control.base.domain.RoleProperties.code()).eq(code).build();
        List<Role> roles = findRoleByCondition(ctx, condition);
        
        if (roles != null && roles.size() != 0 && !roles.get(0).getUuid().equals(actualId)) {
            throw new MetamacException(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED, code);
        }
    }

}
