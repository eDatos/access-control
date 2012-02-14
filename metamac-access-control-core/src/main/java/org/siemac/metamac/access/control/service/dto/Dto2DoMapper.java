package org.siemac.metamac.access.control.service.dto;

import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.core.common.exception.MetamacException;

public interface Dto2DoMapper {

    public Role roleDtoToDo(RoleDto source)  throws MetamacException ;
    public Role roleDtoToDo(RoleDto source, Role target)  throws MetamacException ;

    public App appDtoToDo(AppDto source) throws MetamacException ;
    public App appDtoToDo(AppDto source, App target) throws MetamacException ;

    public User userDtoToDo(UserDto source) throws MetamacException ;
    public User userDtoToDo(UserDto source, User target) throws MetamacException ;

    public Access accessDtoToDo(AccessDto source) throws MetamacException ;
    public Access accessDtoToDo(AccessDto source, Access target) throws MetamacException ;
}
