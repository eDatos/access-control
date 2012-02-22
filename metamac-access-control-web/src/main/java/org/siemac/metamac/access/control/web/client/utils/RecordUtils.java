package org.siemac.metamac.access.control.web.client.utils;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;
import org.siemac.metamac.access.control.web.client.model.record.UserRecord;


public class RecordUtils {

    /**
     * Returns a {@link AccessRecord} from {@link AccessDto}
     * 
     * @param accessDto
     * @return
     */
    public static AccessRecord getAccessRecord(AccessDto accessDto) {
        AccessRecord record = new AccessRecord(accessDto.getId(), 
                accessDto.getUser() != null ? accessDto.getUser().getUsername() : null, 
                accessDto.getRole() != null ? accessDto.getRole().getTitle() : null, 
                accessDto.getApp() != null ? accessDto.getApp().getTitle() :  null, 
                accessDto.getOperation() != null ? accessDto.getOperation().getCodeId() : null, 
                accessDto);
        return record;
    }
    
    /**
     * Returns a {@link UserRecord} from {@link UserDto}
     * 
     * @param userDto
     * @return
     */
    public static UserRecord getUserRecord(UserDto userDto) {
        UserRecord record = new UserRecord(userDto.getId(), 
                userDto.getUsername(), 
                userDto.getName(), 
                userDto.getSurname(), 
                userDto.getMail(), 
                userDto);
        return record;
    }
    
}
