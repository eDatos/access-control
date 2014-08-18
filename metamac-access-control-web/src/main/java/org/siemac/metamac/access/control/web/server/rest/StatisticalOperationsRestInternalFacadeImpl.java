package org.siemac.metamac.access.control.web.server.rest;

import org.apache.commons.lang.StringUtils;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.error.ServiceExceptionParameters;
import org.siemac.metamac.rest.common.v1_0.domain.ComparisonOperator;
import org.siemac.metamac.rest.common.v1_0.domain.LogicalOperator;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.Operation;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.OperationCriteriaPropertyRestriction;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.Operations;
import org.siemac.metamac.web.common.server.rest.utils.RestExceptionUtils;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticalOperationsRestInternalFacadeImpl implements StatisticalOperationsRestInternalFacade {

    @Autowired
    private RestApiLocator     restApiLocator;

    @Autowired
    private RestExceptionUtils restExceptionUtils;

    //
    // OPERATION
    //
    @Override
    public Operation retrieveOperation(ServiceContext serviceContext, String operationCode) throws MetamacWebException {
        try {
            // OPERATION ID in the rest API is what we call CODE
            return restApiLocator.getStatisticalOperationsRestFacadeV10().retrieveOperationById(operationCode);
        } catch (Exception e) {
            throw manageStatisticalOperationsRestException(serviceContext, e);
        }
    }

    //
    // OPERATIONS
    //
    @Override
    public Operations findOperations(ServiceContext serviceContext, int firstResult, int maxResult, String operation) throws MetamacWebException {
        try {
            String query = null;
            if (!StringUtils.isBlank(operation)) {
                query = OperationCriteriaPropertyRestriction.TITLE + " " + ComparisonOperator.ILIKE.name() + " \"" + operation + "\"";
                query += " " + LogicalOperator.OR.name() + " " + OperationCriteriaPropertyRestriction.ID + " " + ComparisonOperator.ILIKE.name() + " \"" + operation + "\"";
            }

            // Pagination
            String limit = String.valueOf(maxResult);
            String offset = String.valueOf(firstResult);

            Operations findOperationsResult = restApiLocator.getStatisticalOperationsRestFacadeV10().findOperations(query, null, limit, offset);
            return findOperationsResult;
        } catch (Exception e) {
            throw manageStatisticalOperationsRestException(serviceContext, e);
        }
    }

    private MetamacWebException manageStatisticalOperationsRestException(ServiceContext ctx, Exception e) throws MetamacWebException {
        return restExceptionUtils.manageMetamacRestException(ctx, e, ServiceExceptionParameters.API_STATISTICAL_OPERATIONS_INTERNAL, restApiLocator.getStatisticalOperationsRestFacadeV10());
    }
}
