package org.siemac.metamac.access.control.base.serviceimpl;

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
            throw new MetamacException(ServiceExceptionType.SERVICE_ROLE_NOT_FOUND);
        }
    }

    public Role saveRole(ServiceContext ctx, Role entity) throws MetamacException {
           return roleRepository.save(entity);
    }

    public void deleteRole(ServiceContext ctx, Role entity) throws MetamacException {
        roleRepository.delete(entity);
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
            throw new MetamacException(ServiceExceptionType.SERVICE_APP_NOT_FOUND);
        }
    }

    public App saveApp(ServiceContext ctx, App entity) throws MetamacException {
        return appRepository.save(entity);
    }

    public void deleteApp(ServiceContext ctx, App entity) throws MetamacException {
        appRepository.delete(entity);
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
            throw new MetamacException(ServiceExceptionType.SERVICE_USER_NOT_FOUND);
        }
    }

    public User saveUser(ServiceContext ctx, User entity) throws MetamacException {
        return userRepository.save(entity);
    }

    public void deleteUser(ServiceContext ctx, User entity) throws MetamacException {
        userRepository.delete(entity);
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
            throw new MetamacException(ServiceExceptionType.SERVICE_ACCESS_NOT_FOUND);
        }
    }

    public Access saveAccess(ServiceContext ctx, Access entity) throws MetamacException {
        return accessRepository.save(entity);
    }

    public void deleteAccess(ServiceContext ctx, Access entity) throws MetamacException {
        accessRepository.delete(entity);
    }

    public List<Access> findAllAccess(ServiceContext ctx) throws MetamacException {
        return accessRepository.findAll();
    }

    public List<Access> findAccessByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) throws MetamacException {
        return accessRepository.findByCondition(condition);
    }

}
