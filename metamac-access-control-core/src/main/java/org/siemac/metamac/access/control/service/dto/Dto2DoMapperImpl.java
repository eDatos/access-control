package org.siemac.metamac.access.control.service.dto;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.joda.time.DateTime;
import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseService;
import org.siemac.metamac.access.control.error.ServiceExceptionParameters;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
import org.siemac.metamac.core.common.bt.domain.ExternalItemBt;
import org.siemac.metamac.core.common.dto.ExternalItemBtDto;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.util.OptimisticLockingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dto2DoMapperImpl implements Dto2DoMapper {

    @Autowired
    private AccessControlBaseService accessControlBaseService;

    @Override
    public Role roleDtoToDo(ServiceContext ctx, RoleDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        // If exists, retrieves existing entity. Otherwise, creates new entity
        Role target = new Role();
        if (source.getId() != null) {
            target = accessControlBaseService.findRoleById(ctx, source.getId());
            OptimisticLockingUtils.checkVersion(target.getVersion(), source.getOptimisticLockingVersion());
        }

        roleDtoToDo(source, target);
        return target;
    }

    @Override
    public App appDtoToDo(ServiceContext ctx, AppDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        // If exists, retrieves existing entity. Otherwise, creates new entity
        App target = new App();
        if (source.getId() != null) {
            target = accessControlBaseService.findAppById(ctx, source.getId());
            OptimisticLockingUtils.checkVersion(target.getVersion(), source.getOptimisticLockingVersion());
        }

        appDtoToDo(source, target);
        return target;
    }

    @Override
    public User userDtoToDo(ServiceContext ctx, UserDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        // If exists, retrieves existing entity. Otherwise, creates new entity
        User target = new User();
        if (source.getId() != null) {
            target = accessControlBaseService.findUserById(ctx, source.getId());
            OptimisticLockingUtils.checkVersion(target.getVersion(), source.getOptimisticLockingVersion());
        }
        userDtoToDo(source, target);
        return target;
    }

    @Override
    public Access accessDtoToDo(ServiceContext ctx, AccessDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        // If exists, retrieves existing entity. Otherwise, creates new entity
        Access target = new Access();
        if (source.getId() != null) {
            target = accessControlBaseService.findAccessById(ctx, source.getId());
            OptimisticLockingUtils.checkVersion(target.getVersion(), source.getOptimisticLockingVersion());
        }
        accessDtoToDo(ctx, source, target);
        return target;
    }

    // -------------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // -------------------------------------------------------------------------------------------------

    private Role roleDtoToDo(RoleDto source, Role target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.ROLE);
        }

        // Non modifiables after creation

        // Attributes modifiables
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        // Optimistic locking: Update "update date" attribute to force update of the root entity in order to increase attribute "version"
        target.setUpdateDate(new DateTime());

        return target;
    }

    private App appDtoToDo(AppDto source, App target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.APP);
        }

        // Non modifiables after creation

        // Attributes modifiables
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        
        // Optimistic locking: Update "update date" attribute to force update of the root entity in order to increase attribute "version"
        target.setUpdateDate(new DateTime());

        return target;
    }

    private User userDtoToDo(UserDto source, User target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.USER);
        }

        // Attributes modifiables
        target.setUsername(source.getUsername());
        target.setName(source.getName());
        target.setSurname(source.getSurname());
        target.setMail(source.getMail());
        
        // Optimistic locking: Update "update date" attribute to force update of the root entity in order to increase attribute "version"
        target.setUpdateDate(new DateTime());

        return target;
    }

    private Access accessDtoToDo(ServiceContext ctx, AccessDto source, Access target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.ACCESS);
        }

        target.setRole(roleDtoToDo(ctx, source.getRole()));
        target.setApp(appDtoToDo(ctx, source.getApp()));
        target.setUser(userDtoToDo(ctx, source.getUser()));

        target.setOperation(externalItemBtDtoToDo(source.getOperation()));
        
        // Optimistic locking: Update "update date" attribute to force update of the root entity in order to increase attribute "version"
        target.setUpdateDate(new DateTime());

        return target;
    }

    private ExternalItemBt externalItemBtDtoToDo(ExternalItemBtDto source) {
        if (source != null) {
            return new ExternalItemBt(source.getUriInt(), source.getCodeId(), source.getType());
        } else {
            return null;
        }
    }

}
