package org.siemac.metamac.access.control.base.serviceapi.utils;

import org.siemac.metamac.domain.access.control.dto.AppDto;
import org.siemac.metamac.domain.access.control.dto.RoleDto;
import org.siemac.metamac.domain.access.control.dto.UserDto;

/**
 * Mocks
 */
public class AccessControlDtoMocks {

    public static RoleDto mockRoleDto() {
        RoleDto roleDto = new RoleDto();
        roleDto.setCode("TECNICO_PLANIFICACION");
        roleDto.setTitle("Técnico de Planifiación");
        roleDto.setDescription("Técnicos encargados de la planificación estadística del ISTAC");
        return roleDto;
    }

    public static AppDto mockAppDto() {
        AppDto appDto = new AppDto();
        appDto.setCode("GESTOR_CONCEPTOS");
        appDto.setTitle("Gestor de conceptos");
        appDto.setDescription("Gestor de conceptos estadísticos usados por otros aplicativos");
        return appDto;
    }

    public static UserDto mockUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("rdiaada");
        userDto.setName("Rita");
        userDto.setSurname("Díaz Adán");
        userDto.setMail("rdiaada@arte-consultores.com");
        return userDto;
    }

}
