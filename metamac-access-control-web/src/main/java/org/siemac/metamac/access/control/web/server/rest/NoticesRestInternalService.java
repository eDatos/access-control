package org.siemac.metamac.access.control.web.server.rest;

import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;

public interface NoticesRestInternalService {

    public static final String BEAN_ID = "noticesRestInternalService";

    void createNotificationForSaveAccessList(ServiceContext ctx, List<AccessDto> accessList) throws MetamacWebException;

    void createNotificationForDeleteAccessList(ServiceContext serviceContext, List<AccessDto> accessList) throws MetamacWebException;

}
