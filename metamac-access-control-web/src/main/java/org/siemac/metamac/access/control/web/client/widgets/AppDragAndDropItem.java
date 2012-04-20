package org.siemac.metamac.access.control.web.client.widgets;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.web.client.model.ds.AppDS;
import org.siemac.metamac.access.control.web.client.model.record.AppRecord;
import org.siemac.metamac.domain.access.control.dto.AppDto;
import org.siemac.metamac.web.common.client.widgets.form.fields.DragAndDropItem;

import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class AppDragAndDropItem extends DragAndDropItem {

    private List<AppDto> appDtos;

    public AppDragAndDropItem(String name, String title, String dragDropType) {
        super(name, title, dragDropType);

        // ListGridField codeField = new ListGridField(AppRecord.CODE);
        // codeField.setWidth("20%");
        // codeField.setShowHover(true);
        // codeField.setHoverCustomizer(new HoverCustomizer() {
        // @Override
        // public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) {
        // AppRecord appRecord = (AppRecord) record;
        // return appRecord.getCode();
        // }
        // });

        ListGridField titleField = new ListGridField(AppRecord.TITLE);
        titleField.setShowHover(true);
        titleField.setHoverCustomizer(new HoverCustomizer() {

            @Override
            public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) {
                AppRecord appRecord = (AppRecord) record;
                return appRecord.getTitle();
            }
        });

        sourceList.setDataSource(new AppDS());
        sourceList.setUseAllDataSourceFields(false);
        sourceList.setFields(titleField);

        targetList.setDataSource(new AppDS());
        targetList.setUseAllDataSourceFields(false);
        targetList.setFields(titleField);
    }

    public void setSourceAppDtos(List<AppDto> appDtos) {
        this.appDtos = appDtos;
        AppRecord[] records = new AppRecord[appDtos.size()];
        for (int i = 0; i < appDtos.size(); i++) {
            records[i] = org.siemac.metamac.access.control.web.client.utils.RecordUtils.getAppRecord(appDtos.get(i));
        }
        sourceList.setData(records);
    }

    public void setTargetAppDtos(List<AppDto> appDtos) {
        AppRecord[] records = new AppRecord[appDtos.size()];
        for (int i = 0; i < appDtos.size(); i++) {
            records[i] = org.siemac.metamac.access.control.web.client.utils.RecordUtils.getAppRecord(appDtos.get(i));
        }
        targetList.setData(records);
    }

    public List<AppDto> getSelectedAppDtos() {
        List<AppDto> selectedItems = new ArrayList<AppDto>();
        ListGridRecord[] records = targetList.getRecords();
        for (int i = 0; i < records.length; i++) {
            AppRecord record = (AppRecord) records[i];
            selectedItems.add(record.getAppDto());
        }
        return selectedItems;
    }

    public void resetValues() {
        clearValue();
        setSourceAppDtos(appDtos);
    }

}
