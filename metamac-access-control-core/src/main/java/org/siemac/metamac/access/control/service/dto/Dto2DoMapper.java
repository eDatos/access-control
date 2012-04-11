package org.siemac.metamac.access.control.service.dto;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.AccessDto;
import org.siemac.metamac.domain.access.control.dto.AppDto;
import org.siemac.metamac.domain.access.control.dto.RoleDto;
import org.siemac.metamac.domain.access.control.dto.UserDto;

public interface Dto2DoMapper {

    public Role roleDtoToDo(ServiceContext ctx, RoleDto source) throws MetamacException;

    public App appDtoToDo(ServiceContext ctx, AppDto source) throws MetamacException;

    public User userDtoToDo(ServiceContext ctx, UserDto source) throws MetamacException;

    public Access accessDtoToDo(ServiceContext ctx, AccessDto source) throws MetamacException;
}
