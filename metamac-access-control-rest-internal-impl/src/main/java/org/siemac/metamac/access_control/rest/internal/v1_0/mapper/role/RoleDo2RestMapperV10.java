package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.role;

import java.util.List;

import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.rest.access_control.v1_0.domain.Roles;

public interface RoleDo2RestMapperV10 {

    public Roles toRoles(List<Role> sources);
}
