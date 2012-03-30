package org.siemac.metamac.access.control.web.server.handlers;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.base.serviceapi.AccessControlBaseServiceFacade;
import org.siemac.metamac.access.control.web.server.ServiceContextHelper;
import org.siemac.metamac.access.control.web.shared.SaveAccessListAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessListResult;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class SaveAccessListActionHandler extends AbstractActionHandler<SaveAccessListAction, SaveAccessListResult> {

    @Autowired
    private AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    public SaveAccessListActionHandler() {
        super(SaveAccessListAction.class);
    }

    @Override
    public SaveAccessListResult execute(SaveAccessListAction action, ExecutionContext context) throws ActionException {
        List<AccessDto> accessSaved = new ArrayList<AccessDto>();
        List<AccessDto> accessDtos = action.getAccessToSave();
        for (AccessDto accessToSave : accessDtos) {
            try {
                AccessDto accessDto = null;
                if (accessToSave.getId() == null) {
                    accessDto = accessControlBaseServiceFacade.createAccess(ServiceContextHelper.getServiceContext(), accessToSave);
                    accessSaved.add(accessDto);
                } else {
                    // DO NOT EDIT ACCESS: delete access and create a new one instead
                    // accessDto = accessControlBaseServiceFacade.updateAccess(ServiceContextHelper.getServiceContext(), accessToSave);
                }
            } catch (MetamacException e) {
                throw new MetamacWebException(WebExceptionUtils.getMetamacWebExceptionItem(e.getExceptionItems()));
            }
        }
        return new SaveAccessListResult(accessSaved);
    }

    @Override
    public void undo(SaveAccessListAction action, SaveAccessListResult result, ExecutionContext context) throws ActionException {

    }

}
