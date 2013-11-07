package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.user;

import org.fornax.cartridges.sculptor.framework.domain.PagedResult;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access_control.rest.internal.AccessControlRestInternalConstants;
import org.siemac.metamac.access_control.rest.internal.v1_0.mapper.base.CommonDo2RestMapperV10;
import org.siemac.metamac.rest.access_control.v1_0.domain.Users;
import org.siemac.metamac.rest.common.v1_0.domain.ResourceLink;
import org.siemac.metamac.rest.search.criteria.mapper.SculptorCriteria2RestCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDo2RestMapperv10Impl implements UserDo2RestMapperV10 {

    @Autowired
    private CommonDo2RestMapperV10 commonDo2RestMapperV10;

    @Override
    public Users toUsers(PagedResult<User> sources, String query, String orderBy, Integer limit) {
        Users targets = new Users();

        if (sources == null || sources.getValues().isEmpty()) {
            return targets;
        }

        targets.setKind(AccessControlRestInternalConstants.KIND_USERS);

        // Pagination
        String baseLink = toUsersLink();
        SculptorCriteria2RestCriteria.toPagedResult(sources, targets, query, orderBy, limit, baseLink);

        // Values
        for (User source : sources.getValues()) {
            org.siemac.metamac.rest.access_control.v1_0.domain.User target = toUser(source);
            targets.getUsers().add(target);
        }

        return targets;
    }

    public org.siemac.metamac.rest.access_control.v1_0.domain.User toUser(User source) {
        if (source == null) {
            return null;
        }

        org.siemac.metamac.rest.access_control.v1_0.domain.User target = new org.siemac.metamac.rest.access_control.v1_0.domain.User();
        target.setKind(AccessControlRestInternalConstants.KIND_USER);
        target.setUsername(source.getUsername());
        target.setName(source.getName());
        target.setUsername(source.getSurname());
        target.setMail(source.getMail());
        // target.setSelfLink(value); // TODO quitarlo si no hago el servicio
        target.setParentLink(toUserParentLink());

        return target;
    }

    private String toUsersLink() {
        String resourceSubpath = AccessControlRestInternalConstants.LINK_SUBPATH_USERS;
        return commonDo2RestMapperV10.toResourceLink(resourceSubpath);
    }

    private ResourceLink toUserParentLink() {
        return commonDo2RestMapperV10.toResourceLink(AccessControlRestInternalConstants.KIND_USERS, toUsersLink());
    }

}
