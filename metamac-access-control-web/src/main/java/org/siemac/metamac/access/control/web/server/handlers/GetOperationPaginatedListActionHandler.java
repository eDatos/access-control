package org.siemac.metamac.access.control.web.server.handlers;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.service.ws.StatisticalOperationsInternalWebServiceFacade;
import org.siemac.metamac.access.control.web.shared.GetOperationPaginatedListAction;
import org.siemac.metamac.access.control.web.shared.GetOperationPaginatedListResult;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.domain.FindOperationsResult;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.domain.OperationBase;
import org.siemac.metamac.statistical.operations.internal.ws.v1_0.domain.OperationBaseList;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class GetOperationPaginatedListActionHandler extends SecurityActionHandler<GetOperationPaginatedListAction, GetOperationPaginatedListResult> {

    @Autowired
    private StatisticalOperationsInternalWebServiceFacade statisticalOperationsInternalWebServiceFacade;

    public GetOperationPaginatedListActionHandler() {
        super(GetOperationPaginatedListAction.class);
    }

    @Override
    public GetOperationPaginatedListResult executeSecurityAction(GetOperationPaginatedListAction action) throws ActionException {
        try {
            int firstResult = 0;
            int totalResults = 0;
            List<ExternalItemDto> ExternalItemDtos = new ArrayList<ExternalItemDto>();
            FindOperationsResult findOperationsResult = statisticalOperationsInternalWebServiceFacade.findOperations(action.getFirstResult(), action.getMaxResults(), action.getOperationCode());
            OperationBaseList operationBaseList = findOperationsResult.getOperations();
            if (operationBaseList != null && operationBaseList.getOperation() != null) {
                firstResult = findOperationsResult.getFirstResult().intValue();
                totalResults = findOperationsResult.getTotalResults().intValue();
                for (OperationBase operationBase : operationBaseList.getOperation()) {
                    ExternalItemDtos.add(new ExternalItemDto("http://statisticalOperations/" + operationBase.getCode(), operationBase.getCode(), TypeExternalArtefactsEnum.STATISTICAL_OPERATION));
                }
            }
            return new GetOperationPaginatedListResult(ExternalItemDtos, firstResult, totalResults);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

}
