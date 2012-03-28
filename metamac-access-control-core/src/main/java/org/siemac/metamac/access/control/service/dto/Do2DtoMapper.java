package org.siemac.metamac.access.control.service.dto;

import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;

public interface Do2DtoMapper {

    public RoleDto roleDoToDto(Role role);

    public AppDto appDoToDto(App app);

    public UserDto userDoToDto(User user);

    public AccessDto accessDoToDto(Access access);

}
