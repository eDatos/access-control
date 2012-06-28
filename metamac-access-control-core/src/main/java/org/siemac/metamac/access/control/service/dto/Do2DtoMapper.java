package org.siemac.metamac.access.control.service.dto;

import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;

public interface Do2DtoMapper {

    public RoleDto roleDoToDto(Role role);

    public AppDto appDoToDto(App app);

    public UserDto userDoToDto(User user);

    public AccessDto accessDoToDto(Access access);

}
