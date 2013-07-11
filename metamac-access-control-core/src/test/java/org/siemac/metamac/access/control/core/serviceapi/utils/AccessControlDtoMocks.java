package org.siemac.metamac.access.control.core.serviceapi.utils;

import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.common.test.utils.MetamacMocks;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;

/**
 * Mocks
 */
public class AccessControlDtoMocks extends MetamacMocks {

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

    public static ExternalItemDto mockExternalItemDto(String code, String codeNested, String uri, String urn, String urnInternal, TypeExternalArtefactsEnum type, String managementAppUrl) {
        ExternalItemDto target = new ExternalItemDto();
        target.setCode(code);
        target.setCodeNested(codeNested);
        target.setUri(uri);
        target.setUrn(urn);
        target.setUrnInternal(urnInternal);
        target.setType(type);
        target.setManagementAppUrl(managementAppUrl);
        return target;
    }

}
