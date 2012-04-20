package org.siemac.metamac.access.control.service.ws;

import java.util.List;

import org.siemac.metamac.core.common.dto.ExternalItemBtDto;
import org.siemac.metamac.core.common.exception.MetamacException;

public interface StatisticalOperationsInternalWebServiceFacade {

    /**
     * Find operations and return ExternalItemBtDto 
     */
    
    public List<ExternalItemBtDto> findOperations() throws MetamacException;
}