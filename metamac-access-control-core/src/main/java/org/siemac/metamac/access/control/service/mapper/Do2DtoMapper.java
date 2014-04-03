package org.siemac.metamac.access.control.service.mapper;

import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.mapper.BaseDo2DtoMapper;

public interface Do2DtoMapper extends BaseDo2DtoMapper {

    RoleDto roleDoToDto(Role role);

    AppDto appDoToDto(App app);

    UserDto userDoToDto(User user);

    AccessDto accessDoToDto(Access access) throws MetamacException;

}
