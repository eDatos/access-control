package org.siemac.metamac.access.control.web.client.model.record;

import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.web.client.model.ds.AppDS;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class AppRecord extends ListGridRecord {

    public AppRecord() {
    }

    public AppRecord(String code, String title, String description, AppDto appDto) {
        setCode(code);
        setTitle(title);
        setDescription(description);
        setAppDto(appDto);
    }

    public void setCode(String value) {
        setAttribute(AppDS.CODE, value);
    }

    public String getCode() {
        return getAttributeAsString(AppDS.CODE);
    }

    public void setTitle(String value) {
        setAttribute(AppDS.TITLE, value);
    }

    public String getTitle() {
        return getAttributeAsString(AppDS.TITLE);
    }

    public void setDescription(String value) {
        setAttribute(AppDS.DESCRIPTION, value);
    }

    public String getDescription() {
        return getAttributeAsString(AppDS.DESCRIPTION);
    }

    public void setAppDto(AppDto appDto) {
        setAttribute(AppDS.APP_DTO, appDto);
    }

    public AppDto getAppDto() {
        return (AppDto) getAttributeAsObject(AppDS.APP_DTO);
    }
}
