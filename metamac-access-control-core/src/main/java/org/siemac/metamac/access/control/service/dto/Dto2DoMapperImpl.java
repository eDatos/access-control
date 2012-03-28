package org.siemac.metamac.access.control.service.dto;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseService;
import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.core.common.bt.domain.ExternalItemBt;
import org.siemac.metamac.core.common.dto.serviceapi.ExternalItemBtDto;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.siemac.metamac.access.control.error.ServiceExceptionType;

@Component
public class Dto2DoMapperImpl implements Dto2DoMapper {

    @Autowired
    private AccessControlBaseService accessControlBaseService;

    @Override
    public Role roleDtoToDo(ServiceContext ctx, RoleDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        Role target = new Role();
        if (source.getId() != null) {
            target = accessControlBaseService.findRoleById(ctx, source.getId());
        }

        roleDtoToDo(source, target);
        return target;
    }

    @Override
    public App appDtoToDo(ServiceContext ctx, AppDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        App target = new App();
        if (source.getId() != null) {
            target = accessControlBaseService.findAppById(ctx, source.getId());
        }

        appDtoToDo(source, target);
        return target;
    }

    @Override
    public User userDtoToDo(ServiceContext ctx, UserDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        User target = new User();
        if (source.getId() != null) {
            target = accessControlBaseService.findUserById(ctx, source.getId());
        }
        userDtoToDo(source, target);
        return target;
    }

    @Override
    public Access accessDtoToDo(ServiceContext ctx, AccessDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        Access target = new Access();
        if (source.getId() != null) {
            target = accessControlBaseService.findAccessById(ctx, source.getId());
        }
        accessDtoToDo(ctx, source, target);
        return target;
    }

    // -------------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // -------------------------------------------------------------------------------------------------

    private Role roleDtoToDo(RoleDto source, Role target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, "ROLE");
        }

        // Non modifiables after creation

        // Attributes modifiables
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        return target;
    }

    private App appDtoToDo(AppDto source, App target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, "APP");
        }

        // Non modifiables after creation

        // Attributes modifiables
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        return target;
    }

    private User userDtoToDo(UserDto source, User target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, "USER");
        }

        // Attributes modifiables
        target.setUsername(source.getUsername());
        target.setName(source.getName());
        target.setSurname(source.getSurname());
        target.setMail(source.getMail());

        return target;
    }

    private Access accessDtoToDo(ServiceContext ctx, AccessDto source, Access target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, "ACCESS");
        }

        target.setRole(roleDtoToDo(ctx, source.getRole()));
        target.setApp(appDtoToDo(ctx, source.getApp()));
        target.setUser(userDtoToDo(ctx, source.getUser()));

        target.setOperation(externalItemBtDtoToDo(source.getOperation()));

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
