package org.siemac.metamac.access.control.web.client.utils;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;


public class RecordUtils {

    /**
     * Returns a {@link AccessRecord} from {@link AccessDto}
     * 
     * @param accessDto
     * @return
     */
    public static AccessRecord getAccessRecord(AccessDto accessDto) {
        AccessRecord record = new AccessRecord(accessDto.getId().toString(), 
                accessDto.getUser() != null ? accessDto.getUser().getUsername() : null, 
                accessDto.getRole() != null ? accessDto.getRole().getTitle() : null, 
                accessDto.getApp() != null ? accessDto.getApp().getTitle() :  null, 
                accessDto.getOperation() != null ? accessDto.getOperation().getCodeId() : null, 
                accessDto);
        return record;
    }
    
}
