package org.siemac.metamac.access.control.web.client.model.record;

import org.siemac.metamac.access.control.core.dto.AppDto;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class AppRecord extends ListGridRecord {

    public static final String CODE        = "code";
    public static final String TITLE       = "title";
    public static final String DESCRIPTION = "desc";
    public static final String APP_DTO     = "dto";

    public AppRecord() {
    }

    public AppRecord(String code, String title, String description, AppDto appDto) {
        setCode(code);
        setTitle(title);
        setDescription(description);
        setAppDto(appDto);
    }

    public void setCode(String value) {
        setAttribute(CODE, value);
    }

    public String getCode() {
        return getAttributeAsString(CODE);
    }

    public void setTitle(String value) {
        setAttribute(TITLE, value);
    }

    public String getTitle() {
        return getAttributeAsString(TITLE);
    }

    public void setDescription(String value) {
        setAttribute(DESCRIPTION, value);
    }

    public String getDescription() {
        return getAttributeAsString(DESCRIPTION);
    }

    public void setAppDto(AppDto appDto) {
        setAttribute(APP_DTO, appDto);
    }

    public AppDto getAppDto() {
        return (AppDto) getAttributeAsObject(APP_DTO);
    }

}
