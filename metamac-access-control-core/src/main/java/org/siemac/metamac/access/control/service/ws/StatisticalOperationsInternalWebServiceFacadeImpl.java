package org.siemac.metamac.access.control.service.ws;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.service.ws.utils.MetamacExceptionUtils;
import org.siemac.metamac.core.common.dto.ExternalItemBtDto;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.exception.MetamacExceptionItem;
import org.siemac.metamac.schema.common.v1_0.domain.MetamacCriteria;
import org.siemac.metamac.schema.common.v1_0.domain.MetamacCriteriaConjunctionRestriction;
import org.siemac.metamac.schema.common.v1_0.domain.MetamacCriteriaRestrictionList;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.MetamacExceptionFault;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.domain.FindOperationsResult;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.domain.OperationBase;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.domain.OperationBaseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticalOperationsInternalWebServiceFacadeImpl implements StatisticalOperationsInternalWebServiceFacade {

    @Autowired
    private WebServicesLocator webservicesLocator;

    @Override
    public List<ExternalItemBtDto> findOperations() throws MetamacException {
        OperationBaseList operationBaseList = findOperationsBaseList();
        
        List<ExternalItemBtDto> operations = new ArrayList<ExternalItemBtDto>();
        for (OperationBase item : operationBaseList.getOperation()) {
            operations.add(new ExternalItemBtDto("http://statisticalOperations/" + item.getCode(), item.getCode(), TypeExternalArtefactsEnum.STATISTICAL_OPERATION));
        }
        
        return operations;
    }
    
    private OperationBase retrieveOperation(String operationCode) throws MetamacException {
        try {
            return webservicesLocator.getMetamacStatisticalOperationsInternalInterface().retrieveOperation(operationCode);
        } catch (MetamacExceptionFault e) {
            List<MetamacExceptionItem> metamacExceptionItems = MetamacExceptionUtils.getMetamacExceptionItems(e.getFaultInfo().getExceptionItems());
            throw new MetamacException(metamacExceptionItems);
        }
    }

    private OperationBaseList findOperationsBaseList() throws MetamacException {
        try {
            MetamacCriteria metamacCriteria = new MetamacCriteria();
            metamacCriteria.setRestriction(new MetamacCriteriaConjunctionRestriction());
            ((MetamacCriteriaConjunctionRestriction) metamacCriteria.getRestriction()).setRestrictions(new MetamacCriteriaRestrictionList());

            metamacCriteria.setMaximumResultSize(BigInteger.valueOf(1000));
            
            FindOperationsResult findOperationsResult = webservicesLocator.getMetamacStatisticalOperationsInternalInterface().findOperations(metamacCriteria);
            return findOperationsResult.getOperations();
        } catch (MetamacExceptionFault e) {
            List<MetamacExceptionItem> metamacExceptionItems = MetamacExceptionUtils.getMetamacExceptionItems(e.getFaultInfo().getExceptionItems());
            throw new MetamacException(metamacExceptionItems);
        }
    }
}