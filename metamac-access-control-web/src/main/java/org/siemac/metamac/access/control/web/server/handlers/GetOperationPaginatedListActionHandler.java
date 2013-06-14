package org.siemac.metamac.access.control.web.server.handlers;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.web.server.rest.StatisticalOperationsRestInternalFacade;
import org.siemac.metamac.access.control.web.server.rest.utils.ExternalItemUtils;
import org.siemac.metamac.access.control.web.shared.GetOperationPaginatedListAction;
import org.siemac.metamac.access.control.web.shared.GetOperationPaginatedListResult;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.Operations;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.ResourceInternal;
import org.siemac.metamac.web.common.server.ServiceContextHolder;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class GetOperationPaginatedListActionHandler extends SecurityActionHandler<GetOperationPaginatedListAction, GetOperationPaginatedListResult> {

    @Autowired
    private StatisticalOperationsRestInternalFacade statisticalOperationsRestInternalFacade;

    public GetOperationPaginatedListActionHandler() {
        super(GetOperationPaginatedListAction.class);
    }

    @Override
    public GetOperationPaginatedListResult executeSecurityAction(GetOperationPaginatedListAction action) throws ActionException {

        ServiceContext serviceContext = ServiceContextHolder.getCurrentServiceContext();

        int firstResult = 0;
        int totalResults = 0;
        List<ExternalItemDto> externalItemDtos = new ArrayList<ExternalItemDto>();
        Operations result = statisticalOperationsRestInternalFacade.findOperations(serviceContext, action.getFirstResult(), action.getMaxResults(), action.getOperation());
        if (result != null && result.getOperations() != null) {
            firstResult = result.getOffset().intValue();
            totalResults = result.getTotal().intValue();
            for (ResourceInternal resource : result.getOperations()) {
                ExternalItemDto externalItemDto = ExternalItemUtils.getExternalItemDtoFromStatisticalOperationsResourceInternal(resource);
                externalItemDtos.add(externalItemDto);
            }
        }
        return new GetOperationPaginatedListResult(externalItemDtos, firstResult, totalResults);
    }
}
