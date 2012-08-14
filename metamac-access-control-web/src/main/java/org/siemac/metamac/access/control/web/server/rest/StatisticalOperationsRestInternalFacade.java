package org.siemac.metamac.access.control.web.server.rest;

import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.Operation;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.Operations;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;

public interface StatisticalOperationsRestInternalFacade {

    /**
     * Retrieves operation
     */
    public Operation retrieveOperation(String operationCode) throws MetamacWebException;

    /**
     * Finds operations
     */
    public Operations findOperations(int firstResult, int maxResult, String operation) throws MetamacWebException;

}
