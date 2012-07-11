package org.siemac.metamac.access.control.web.server.rest;

import org.siemac.metamac.rest.common.v1_0.domain.ResourcesPagedResult;
import org.siemac.metamac.statistical.operations.rest.internal.v1_0.domain.Operation;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;

public interface StatisticalOperationsRestInternalFacade {

    /**
     * Retrieves operation
     */
    public Operation retrieveOperation(String operationCode) throws MetamacWebException;

    /**
     * Finds operations
     */
    public ResourcesPagedResult findOperations(int firstResult, int maxResult, String operation) throws MetamacWebException;

}
