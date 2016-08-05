package org.siemac.metamac.access.control.web.shared;

import java.util.List;

import org.siemac.metamac.core.common.dto.ExternalItemDto;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;

@GenDispatch(isSecure = false)
public class GetOperationPaginatedList {

    @In(1)
    int                   firstResult;

    @In(2)
    int                   maxResults;

    @In(3)
    String                operation;

    @Out(1)
    List<ExternalItemDto> operations;

    @Out(2)
    int                   firstResultOut;

    @Out(3)
    int                   totalResults;

}
