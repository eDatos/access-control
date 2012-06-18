package org.siemac.metamac.access.control.service.ws;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.siemac.metamac.access.control.service.ws.utils.MetamacExceptionUtils;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.exception.MetamacExceptionItem;
import org.siemac.metamac.schema.common.v1_0.domain.MetamacCriteria;
import org.siemac.metamac.schema.common.v1_0.domain.MetamacCriteriaConjunctionRestriction;
import org.siemac.metamac.schema.common.v1_0.domain.MetamacCriteriaPropertyRestriction;
import org.siemac.metamac.schema.common.v1_0.domain.MetamacCriteriaRestrictionList;
import org.siemac.metamac.schema.common.v1_0.domain.OperationType;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.MetamacExceptionFault;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.domain.FindOperationsResult;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.domain.OperationCriteriaPropertyRestriction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticalOperationsInternalWebServiceFacadeImpl implements StatisticalOperationsInternalWebServiceFacade {

    @Autowired
    private WebServicesLocator webservicesLocator;

    @Override
    public FindOperationsResult findOperations(int firstResult, int maxResult, String operationCode) throws MetamacException {
        try {
            MetamacCriteria metamacCriteria = new MetamacCriteria();
            metamacCriteria.setRestriction(new MetamacCriteriaConjunctionRestriction());
            ((MetamacCriteriaConjunctionRestriction) metamacCriteria.getRestriction()).setRestrictions(new MetamacCriteriaRestrictionList());
            
            // Pagination
            metamacCriteria.setFirstResult(BigInteger.valueOf(firstResult));
            metamacCriteria.setMaximumResultSize(BigInteger.valueOf(maxResult));
            metamacCriteria.setCountTotalResults(true);

            if (!StringUtils.isBlank(operationCode)) {
                MetamacCriteriaPropertyRestriction restriction = new MetamacCriteriaPropertyRestriction();
                restriction.setPropertyName(OperationCriteriaPropertyRestriction.CODE.name());
                restriction.setStringValue(operationCode);
                restriction.setOperation(OperationType.ILIKE);
                ((MetamacCriteriaConjunctionRestriction) metamacCriteria.getRestriction()).getRestrictions().getRestriction().add(restriction);
            }

            return webservicesLocator.getMetamacStatisticalOperationsInternalInterface().findOperations(metamacCriteria);
        } catch (MetamacExceptionFault e) {
            List<MetamacExceptionItem> metamacExceptionItems = MetamacExceptionUtils.getMetamacExceptionItems(e.getFaultInfo().getExceptionItems());
            throw new MetamacException(metamacExceptionItems);
        }
    }

}