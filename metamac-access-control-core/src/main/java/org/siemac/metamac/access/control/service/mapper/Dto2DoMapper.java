package org.siemac.metamac.access.control.service.mapper;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.mapper.BaseDto2DoMapper;

public interface Dto2DoMapper extends BaseDto2DoMapper {

    Role roleDtoToDo(ServiceContext ctx, RoleDto source) throws MetamacException;

    App appDtoToDo(ServiceContext ctx, AppDto source) throws MetamacException;

    User userDtoToDo(ServiceContext ctx, UserDto source) throws MetamacException;

    Access accessDtoToDo(ServiceContext ctx, AccessDto source) throws MetamacException;
}
