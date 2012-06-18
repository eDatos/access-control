package org.siemac.metamac.access.control.service.ws;

import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.domain.FindOperationsResult;

public interface StatisticalOperationsInternalWebServiceFacade {

    public FindOperationsResult findOperations(int firstResult, int maxResult, String operationCode) throws MetamacException;

}