package org.siemac.metamac.access.control.service.dto;

import java.util.Date;

import org.joda.time.DateTime;
import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.core.common.bt.domain.ExternalItemBt;
import org.siemac.metamac.core.common.dto.serviceapi.ExternalItemBtDto;
import org.siemac.metamac.domain.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.domain.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.domain.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.domain.access.control.dto.serviceapi.UserDto;
import org.springframework.stereotype.Component;

@Component
public class Do2DtoMapperImpl implements Do2DtoMapper {

    @Override
    public RoleDto roleDoToDto(Role source) {
        RoleDto target = new RoleDto();

        target.setId(source.getId());
        target.setUuid(source.getUuid());
        target.setVersion(source.getVersion());

        target.setCreatedDate(dateDoToDto(source.getCreatedDate()));
        target.setCreatedBy(source.getCreatedBy());
        target.setLastUpdated(dateDoToDto(source.getLastUpdated()));
        target.setLastUpdatedBy(source.getLastUpdatedBy());

        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        return target;
    }

    @Override
    public AppDto appDoToDto(App source) {
        AppDto target = new AppDto();

        target.setId(source.getId());
        target.setUuid(source.getUuid());
        target.setVersion(source.getVersion());

        target.setCreatedDate(dateDoToDto(source.getCreatedDate()));
        target.setCreatedBy(source.getCreatedBy());
        target.setLastUpdated(dateDoToDto(source.getLastUpdated()));
        target.setLastUpdatedBy(source.getLastUpdatedBy());

        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        return target;
    }

    @Override
    public UserDto userDoToDto(User source) {
        UserDto target = new UserDto();

        target.setId(source.getId());
        target.setUuid(source.getUuid());
        target.setVersion(source.getVersion());

        target.setCreatedDate(dateDoToDto(source.getCreatedDate()));
        target.setCreatedBy(source.getCreatedBy());
        target.setLastUpdated(dateDoToDto(source.getLastUpdated()));
        target.setLastUpdatedBy(source.getLastUpdatedBy());

        target.setUsername(source.getUsername());
        target.setName(source.getName());
        target.setSurname(source.getSurname());
        target.setMail(source.getMail());

        return target;
    }

    @Override
    public AccessDto accessDoToDto(Access source) {
        AccessDto target = new AccessDto();

        target.setId(source.getId());
        target.setUuid(source.getUuid());
        target.setVersion(source.getVersion());

        target.setCreatedDate(dateDoToDto(source.getCreatedDate()));
        target.setCreatedBy(source.getCreatedBy());
        target.setLastUpdated(dateDoToDto(source.getLastUpdated()));
        target.setLastUpdatedBy(source.getLastUpdatedBy());

        target.setRemovalDate(dateDoToDto(source.getRemovalDate()));

        target.setRole(roleDoToDto(source.getRole()));
        target.setApp(appDoToDto(source.getApp()));
        target.setUser(userDoToDto(source.getUser()));
        target.setOperation(externalItemBtToDto(source.getOperation()));

        return target;
    }

    private ExternalItemBtDto externalItemBtToDto(ExternalItemBt source) {
        if (source == null) {
            return null;
        }

        ExternalItemBtDto target = new ExternalItemBtDto(source.getUriInt(), source.getCodeId(), source.getType());

        return target;
    }

    private Date dateDoToDto(DateTime source) {
        if (source == null) {
            return null;
        }
        return source.toDate();
    }
}
