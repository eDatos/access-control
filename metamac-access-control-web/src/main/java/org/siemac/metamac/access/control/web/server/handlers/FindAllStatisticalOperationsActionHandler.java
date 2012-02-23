package org.siemac.metamac.access.control.web.server.handlers;

import java.util.List;

import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.shared.FindAllStatisticalOperationsAction;
import org.siemac.metamac.access.control.web.shared.FindAllStatisticalOperationsResult;
import org.siemac.metamac.core.common.dto.serviceapi.ExternalItemBtDto;
import org.siemac.metamac.core.common.serviceapi.MetamacCoreCommonService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;


public class FindAllStatisticalOperationsActionHandler extends AbstractActionHandler< FindAllStatisticalOperationsAction,  FindAllStatisticalOperationsResult> {

    @Autowired
    private MetamacCoreCommonService metamacCoreCommonService;
    
    
    public FindAllStatisticalOperationsActionHandler() {
        super(FindAllStatisticalOperationsAction.class);
    }

    @Override
    public FindAllStatisticalOperationsResult execute(FindAllStatisticalOperationsAction action, ExecutionContext context) throws ActionException {
        List<ExternalItemBtDto> operations = metamacCoreCommonService.findAllStatisticalOperations(ServiceContextHelper.getServiceContext());
        return new FindAllStatisticalOperationsResult(operations);
    }

    @Override
    public void undo(FindAllStatisticalOperationsAction action, FindAllStatisticalOperationsResult result, ExecutionContext context) throws ActionException {
        
    }
    
}
