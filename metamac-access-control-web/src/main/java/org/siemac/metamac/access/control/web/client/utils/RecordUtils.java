package org.siemac.metamac.access.control.web.client.utils;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;
import org.siemac.metamac.access.control.web.client.model.record.AppRecord;
import org.siemac.metamac.access.control.web.client.model.record.UserRecord;
import org.siemac.metamac.web.common.client.utils.DateUtils;

public class RecordUtils {

    /**
     * Returns a {@link AccessRecord} from {@link AccessDto}
     * 
     * @param accessDto
     * @return
     */
    public static AccessRecord getAccessRecord(AccessDto accessDto) {
        AccessRecord record = new AccessRecord(accessDto.getId(), accessDto.getUser() != null ? accessDto.getUser().getUsername() : null, accessDto.getRole() != null
                ? accessDto.getRole().getTitle()
                : null, accessDto.getApp() != null ? accessDto.getApp().getTitle() : null, accessDto.getOperation() != null ? accessDto.getOperation().getCode() : null,
                DateUtils.getFormattedDate(accessDto.getRemovalDate()), accessDto);
        return record;
    }
    /**
     * Returns a {@link UserRecord} from {@link UserDto}
     * 
     * @param userDto
     * @return
     */
    public static UserRecord getUserRecord(UserDto userDto) {
        UserRecord record = new UserRecord(userDto.getId(), userDto.getUsername(), userDto.getName(), userDto.getSurname(), userDto.getMail(), userDto);
        return record;
    }

    /**
     * Returns a {@link AppRecord} from a {@link AppDto}
     * 
     * @param appDto
     * @return
     */
    public static AppRecord getAppRecord(AppDto appDto) {
        AppRecord record = new AppRecord(appDto.getCode(), appDto.getTitle(), appDto.getDescription(), appDto);
        return record;
    }

}
