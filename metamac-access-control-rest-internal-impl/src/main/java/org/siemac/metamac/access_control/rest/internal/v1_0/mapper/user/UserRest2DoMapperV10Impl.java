package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.user;

import org.fornax.cartridges.sculptor.framework.domain.Property;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.domain.UserProperties;
import org.siemac.metamac.access_control.rest.internal.v1_0.mapper.base.BaseRest2DoMapperV10Impl;
import org.siemac.metamac.rest.access_control.v1_0.domain.UserCriteriaPropertyOrder;
import org.siemac.metamac.rest.access_control.v1_0.domain.UserCriteriaPropertyRestriction;
import org.siemac.metamac.rest.common.query.domain.MetamacRestOrder;
import org.siemac.metamac.rest.common.query.domain.MetamacRestQueryPropertyRestriction;
import org.siemac.metamac.rest.exception.RestException;
import org.siemac.metamac.rest.search.criteria.SculptorPropertyCriteriaBase;
import org.siemac.metamac.rest.search.criteria.mapper.RestCriteria2SculptorCriteria;
import org.siemac.metamac.rest.search.criteria.mapper.RestCriteria2SculptorCriteria.CriteriaCallback;
import org.springframework.stereotype.Component;

@Component
public class UserRest2DoMapperV10Impl extends BaseRest2DoMapperV10Impl implements UserRest2DoMapperV10 {

    RestCriteria2SculptorCriteria<User> userCriteriaMapper = null;

    public UserRest2DoMapperV10Impl() {
        userCriteriaMapper = new RestCriteria2SculptorCriteria<User>(User.class, null, UserCriteriaPropertyRestriction.class, new UserCriteriaCallback());
    }

    @Override
    public RestCriteria2SculptorCriteria<User> getUserCriteriaMapper() {
        return userCriteriaMapper;
    }

    private class UserCriteriaCallback implements CriteriaCallback {

        @Override
        public SculptorPropertyCriteriaBase retrieveProperty(MetamacRestQueryPropertyRestriction propertyRestriction) throws RestException {
            UserCriteriaPropertyRestriction propertyNameCriteria = UserCriteriaPropertyRestriction.fromValue(propertyRestriction.getPropertyName());
            switch (propertyNameCriteria) {
                case USERNAME:
                    return buildSculptorPropertyCriteria(UserProperties.username(), PropertyTypeEnum.STRING, propertyRestriction);
                case APPICATION_CODE:
                    return buildSculptorPropertyCriteria(UserProperties.access().app().code(), PropertyTypeEnum.STRING, propertyRestriction);
                case ROLE_CODE:
                    return buildSculptorPropertyCriteria(UserProperties.access().role().code(), PropertyTypeEnum.STRING, propertyRestriction);
                case STATISTICAL_OPERATION_URN:
                    return buildSculptorPropertyCriteriaDisjunctionForUrnProperty(propertyRestriction, UserProperties.access().operation());
                default:
                    throw toRestExceptionParameterIncorrect(propertyNameCriteria.name());
            }
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Property retrievePropertyOrder(MetamacRestOrder order) throws RestException {
            UserCriteriaPropertyOrder propertyNameCriteria = UserCriteriaPropertyOrder.fromValue(order.getPropertyName());
            switch (propertyNameCriteria) {
                case USERNAME:
                    return UserProperties.username();
                default:
                    throw toRestExceptionParameterIncorrect(propertyNameCriteria.name());
            }
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Property retrievePropertyOrderDefault() throws RestException {
            return UserProperties.surname();
        }

    }

}
