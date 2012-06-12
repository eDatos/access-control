package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.service.ws.StatisticalOperationsInternalWebServiceFacade;
import org.siemac.metamac.access.control.web.shared.FindAllStatisticalOperationsAction;
import org.siemac.metamac.access.control.web.shared.FindAllStatisticalOperationsResult;
import org.siemac.metamac.core.common.dto.ExternalItemBtDto;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.web.common.server.handlers.SecurityActionHandler;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class FindAllStatisticalOperationsActionHandler extends SecurityActionHandler<FindAllStatisticalOperationsAction, FindAllStatisticalOperationsResult> {

    @Autowired
    private StatisticalOperationsInternalWebServiceFacade statisticalOperationsInternalWebServiceFacade;

    public FindAllStatisticalOperationsActionHandler() {
        super(FindAllStatisticalOperationsAction.class);
    }

    @Override
    public FindAllStatisticalOperationsResult executeSecurityAction(FindAllStatisticalOperationsAction action) throws ActionException {
        try {
            List<ExternalItemBtDto> operations = statisticalOperationsInternalWebServiceFacade.findOperations();
            return new FindAllStatisticalOperationsResult(operations);
        } catch (MetamacException e) {
            throw WebExceptionUtils.createMetamacWebException(e);
        }
    }

    @Override
    public void undo(FindAllStatisticalOperationsAction action, FindAllStatisticalOperationsResult result, ExecutionContext context) throws ActionException {

    }

}
