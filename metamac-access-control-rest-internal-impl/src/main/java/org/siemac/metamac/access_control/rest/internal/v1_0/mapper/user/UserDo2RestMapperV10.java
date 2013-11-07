package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.user;

import org.fornax.cartridges.sculptor.framework.domain.PagedResult;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.rest.access_control.v1_0.domain.Users;

public interface UserDo2RestMapperV10 {

    public Users toUsers(PagedResult<User> sources, String query, String orderBy, Integer limit);
}
