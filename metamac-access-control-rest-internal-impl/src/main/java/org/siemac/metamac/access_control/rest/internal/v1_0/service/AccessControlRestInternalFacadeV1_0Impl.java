package org.siemac.metamac.access_control.rest.internal.v1_0.service;

import java.util.List;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.fornax.cartridges.sculptor.framework.domain.PagedResult;
import org.fornax.cartridges.sculptor.framework.domain.PagingParameter;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseService;
import org.siemac.metamac.access_control.rest.internal.AccessControlRestInternalConstants;
import org.siemac.metamac.access_control.rest.internal.service.utils.AccessControlRestInternalUtils;
import org.siemac.metamac.access_control.rest.internal.v1_0.mapper.user.UserDo2RestMapperV10;
import org.siemac.metamac.access_control.rest.internal.v1_0.mapper.user.UserRest2DoMapperV10;
import org.siemac.metamac.rest.access_control.v1_0.domain.Users;
import org.siemac.metamac.rest.search.criteria.SculptorCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accessControlRestInternalFacadeV1_0")
public class AccessControlRestInternalFacadeV1_0Impl implements AccessControlRestInternalFacadeV1_0 {

    @Autowired
    private UserRest2DoMapperV10     userRest2DoMapperV10;

    @Autowired
    private UserDo2RestMapperV10     userDo2RestMapperV10;

    @Autowired
    private AccessControlBaseService accessControlBaseService;

    @Override
    public Users findUsers(String query, String limit, String offset) {
        try {
            // Transform query
            SculptorCriteria sculptorCriteria = userRest2DoMapperV10.getUserCriteriaMapper().restCriteriaToSculptorCriteria(query, null, limit, offset);

            // Find
            PagedResult<User> entitiesPagedResult = findUsers(sculptorCriteria.getConditions(), sculptorCriteria.getPagingParameter());

            // Transform
            Users users = userDo2RestMapperV10.toUsers(entitiesPagedResult, query, null, sculptorCriteria.getLimit());

            return users;
        } catch (Exception e) {
            throw AccessControlRestInternalUtils.manageException(e);
        }
    }

    private PagedResult<User> findUsers(List<ConditionalCriteria> conditionalCriteriaQuery, PagingParameter pagingParameter) {
        try {
            return accessControlBaseService.findUserByCondition(AccessControlRestInternalConstants.SERVICE_CONTEXT, conditionalCriteriaQuery, pagingParameter);
        } catch (Exception e) {
            throw AccessControlRestInternalUtils.manageException(e);
        }
    }
}
