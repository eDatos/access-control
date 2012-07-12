package org.siemac.metamac.access.control.web.server.rest;

import org.apache.commons.lang.StringUtils;
import org.siemac.metamac.core.common.criteria.MetamacCriteriaPropertyRestriction.OperationType;
import org.siemac.metamac.rest.common.v1_0.domain.ResourcesPagedResult;
import org.siemac.metamac.statistical.operations.rest.internal.v1_0.domain.Operation;
import org.siemac.metamac.statistical.operations.rest.internal.v1_0.domain.OperationCriteriaPropertyRestriction;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticalOperationsRestInternalFacadeImpl implements StatisticalOperationsRestInternalFacade {

    @Autowired
    private RestApiLocator restApiLocator;

    @Override
    public Operation retrieveOperation(String operationCode) throws MetamacWebException {
        try {
            return restApiLocator.getStatisticalOperationsRestFacadeV10().retrieveOperationById(operationCode); // OPERATION ID in the rest API is what we call CODE
        } catch (Exception e) {
            // TODO throw exception
            return null;
        }
    }

    @Override
    public ResourcesPagedResult findOperations(int firstResult, int maxResult, String operation) throws MetamacWebException {
        try {

            String query = null;
            if (!StringUtils.isBlank(operation)) {
                // TODO Build query to find operations by URN and title
                query = OperationCriteriaPropertyRestriction.TITLE + " " + OperationType.ILIKE.name() + " \"" + operation + "\"";
            }

            // Pagination
            String limit = String.valueOf(maxResult);
            String offset = String.valueOf(firstResult);

            ResourcesPagedResult findOperationsResult = restApiLocator.getStatisticalOperationsRestFacadeV10().findOperations(query, null, limit, offset);
            return findOperationsResult;
        } catch (Exception e) {
            // TODO throw exception
            return null;
        }
    }

}
