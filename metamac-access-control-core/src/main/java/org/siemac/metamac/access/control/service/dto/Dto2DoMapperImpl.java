package org.siemac.metamac.access.control.service.dto;

import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.core.common.bt.domain.ExternalItemBt;
import org.siemac.metamac.core.common.dto.serviceapi.ExternalItemBtDto;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.stereotype.Component;

import org.siemac.metamac.access.control.error.ServiceExceptionType;

@Component
public class Dto2DoMapperImpl implements Dto2DoMapper {

    @Override
    public Role roleDtoToDo(RoleDto source) throws MetamacException {
        Role target = new Role();
        roleDtoToDo(source, target);
        return target;
    }

    @Override
    public Role roleDtoToDo(RoleDto source, Role target) throws MetamacException {
        if (source == null) {
            return null;
        }
        
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PARAMETER_REQUIRED.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PARAMETER_REQUIRED.getMessageForReasonType());
        }
        
        // Non modifiables after creation
        
        // Attributes modifiables
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        
        return target;
    }

    @Override
    public App appDtoToDo(AppDto source) throws MetamacException  {
        App target = new App();
        appDtoToDo(source, target);
        return target;
    }

    @Override
    public App appDtoToDo(AppDto source, App target) throws MetamacException  {
        if (source == null) {
            return null;
        }
        
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PARAMETER_REQUIRED.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PARAMETER_REQUIRED.getMessageForReasonType());
        }
        
        // Non modifiables after creation
        
        // Attributes modifiables
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        
        return target;
    }

    @Override
    public User userDtoToDo(UserDto source) throws MetamacException  {
        User target = new User();
        userDtoToDo(source, target);
        return target;
    }

    @Override
    public User userDtoToDo(UserDto source, User target) throws MetamacException  {
        if (source == null) {
            return null;
        }
        
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PARAMETER_REQUIRED.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PARAMETER_REQUIRED.getMessageForReasonType());
        }
        
        // Non modifiables after creation
        
        // Attributes modifiables
        target.setSurname(source.getUsername());
        target.setName(source.getName());
        target.setSurname(source.getSurname());
        target.setMail(source.getMail());
        
        return target;
    }

    @Override
    public Access accessDtoToDo(AccessDto source) throws MetamacException  {
        Access target = new Access();
        accessDtoToDo(source, target);
        return target;
    }

    @Override
    public Access accessDtoToDo(AccessDto source, Access target) throws MetamacException  {
        if (source == null) {
            return null;
        }
        
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PARAMETER_REQUIRED.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PARAMETER_REQUIRED.getMessageForReasonType());
        }
        
        // Non modifiable after creation
        
        // Attributes that we modified at service
        // ROLE
        // APP
        // USER
        
        // Modifiable attributes
        target.setOperation(externalItemBtDtoToDo(source.getOperation()));
        
        return target;
    }
    
    
    private ExternalItemBt externalItemBtDtoToDo(ExternalItemBtDto source) {
        return new ExternalItemBt(source.getUriInt(), source.getCodeId(), source.getType());
    }


}
