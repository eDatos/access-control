package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.user;

import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.rest.search.criteria.mapper.RestCriteria2SculptorCriteria;

public interface UserRest2DoMapperV10 {

    public RestCriteria2SculptorCriteria<User> getUserCriteriaMapper();
}
