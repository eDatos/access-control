package org.siemac.metamac.access.control.web.server.handlers;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.web.server.rest.StatisticalOperationsRestInternalFacade;
import org.siemac.metamac.access.control.web.shared.GetOperationPaginatedListAction;
import org.siemac.metamac.access.control.web.shared.GetOperationPaginatedListResult;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;
import org.siemac.metamac.rest.common.v1_0.domain.Resource;
import org.siemac.metamac.rest.common.v1_0.domain.ResourcesPagedResult;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.DtoUtils;
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
        int firstResult = 0;
        int totalResults = 0;
        List<ExternalItemDto> externalItemDtos = new ArrayList<ExternalItemDto>();
        ResourcesPagedResult result = statisticalOperationsRestInternalFacade.findOperations(action.getFirstResult(), action.getMaxResults(), action.getOperation());
        if (result != null && result.getItems() != null) {
            firstResult = result.getOffset().intValue();
            totalResults = result.getTotal().intValue();
            for (Resource resource : result.getItems()) {
                ExternalItemDto externalItemDto = new ExternalItemDto(resource.getId(), resource.getSelfLink(), resource.getUrn(), TypeExternalArtefactsEnum.STATISTICAL_OPERATION,
                        DtoUtils.getInternationalStringDtoFromInternationalString(resource.getTitle()));
                externalItemDtos.add(externalItemDto);

            }
        }
        return new GetOperationPaginatedListResult(externalItemDtos, firstResult, totalResults);
    }

}
