package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.role;

import java.util.List;

import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access_control.rest.internal.AccessControlRestInternalConstants;
import org.siemac.metamac.access_control.rest.internal.v1_0.mapper.base.CommonDo2RestMapperV10;
import org.siemac.metamac.rest.access_control.v1_0.domain.Roles;
import org.siemac.metamac.rest.common.v1_0.domain.ResourceLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDo2RestMapperv10Impl implements RoleDo2RestMapperV10 {

    @Autowired
    private CommonDo2RestMapperV10 commonDo2RestMapperV10;

    @Override
    public Roles toRoles(List<Role> sources) {
        Roles targets = new Roles();
        targets.setKind(AccessControlRestInternalConstants.KIND_ROLES);

        // Values
        for (Role source : sources) {
            org.siemac.metamac.rest.access_control.v1_0.domain.Role target = toRole(source);
            targets.getRoles().add(target);
        }

        return targets;
    }

    public org.siemac.metamac.rest.access_control.v1_0.domain.Role toRole(Role source) {
        if (source == null) {
            return null;
        }

        org.siemac.metamac.rest.access_control.v1_0.domain.Role target = new org.siemac.metamac.rest.access_control.v1_0.domain.Role();
        target.setKind(AccessControlRestInternalConstants.KIND_ROLE);
        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
        target.setTitle(source.getTitle());
        target.setParentLink(toRoleParentLink());

        return target;
    }

    private String toRolesLink() {
        String resourceSubpath = AccessControlRestInternalConstants.LINK_SUBPATH_ROLES;
        return commonDo2RestMapperV10.toResourceLink(resourceSubpath);
    }

    private ResourceLink toRoleParentLink() {
        return commonDo2RestMapperV10.toResourceLink(AccessControlRestInternalConstants.KIND_ROLES, toRolesLink());
    }
}
