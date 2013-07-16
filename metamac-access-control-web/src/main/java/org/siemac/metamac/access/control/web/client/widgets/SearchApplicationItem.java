package org.siemac.metamac.access.control.web.client.widgets;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.web.client.AccessControlWeb;
import org.siemac.metamac.access.control.web.client.model.ds.AppDS;
import org.siemac.metamac.access.control.web.client.model.record.AppRecord;
import org.siemac.metamac.access.control.web.client.utils.RecordUtils;
import org.siemac.metamac.core.common.util.shared.BooleanUtils;
import org.siemac.metamac.core.common.util.shared.StringUtils;
import org.siemac.metamac.web.common.client.MetamacWebCommon;
import org.siemac.metamac.web.common.client.widgets.BaseSearchWindow;
import org.siemac.metamac.web.common.client.widgets.CustomListGridField;
import org.siemac.metamac.web.common.client.widgets.form.fields.DragAndDropItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.external.ExternalItemListItem;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class SearchApplicationItem extends ExternalItemListItem {

    protected static final int        FORM_ITEM_CUSTOM_WIDTH = 500;

    protected SearchApplicationWindow searchApplicationWindow;
    protected List<AppDto>            appDtos;

    public SearchApplicationItem(final String name, String title) {
        super(name, title, true);

        CustomListGridField titleField = new CustomListGridField(AppDS.TITLE, AccessControlWeb.getConstants().app());
        listGrid.setFields(titleField);

        getSearchIcon().addFormItemClickHandler(new FormItemClickHandler() {

            @Override
            public void onFormItemClick(FormItemIconClickEvent event) {
                searchApplicationWindow = new SearchApplicationWindow(name);
                searchApplicationWindow.resetValues();
                searchApplicationWindow.setSourceAppDtos(getSourceAppDtosWihtoutSelectedApps());
                searchApplicationWindow.setTargetAppDtos(getSelectedAppDtos());
                searchApplicationWindow.getSaveButton().addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent event) {
                        List<AppDto> selectedAppDtos = searchApplicationWindow.getSelectedAppDtos();
                        searchApplicationWindow.markForDestroy();

                        List<AppRecord> selectedAppRecords = new ArrayList<AppRecord>();
                        for (AppDto appDto : selectedAppDtos) {
                            AppRecord appRecord = RecordUtils.getAppRecord(appDto);
                            selectedAppRecords.add(appRecord);
                        }
                        SearchApplicationItem.this.getListGrid().setData(selectedAppRecords.toArray(new ListGridRecord[selectedAppRecords.size()]));
                    }
                });
            }
        });
    }

    private List<AppDto> getSourceAppDtosWihtoutSelectedApps() {
        List<AppDto> sourceAppDtosWihtoutSelectedApps = new ArrayList<AppDto>();
        List<AppDto> selectedAppDtos = getSelectedAppDtos();
        for (AppDto appDto : appDtos) {
            if (!isAppDtoInList(appDto, selectedAppDtos)) {
                sourceAppDtosWihtoutSelectedApps.add(appDto);
            }
        }
        return sourceAppDtosWihtoutSelectedApps;
    }

    private boolean isAppDtoInList(AppDto appDto, List<AppDto> appDtos) {
        for (AppDto a : appDtos) {
            if (StringUtils.equals(appDto.getCode(), a.getCode())) {
                return true;
            }
        }
        return false;
    }

    public List<AppDto> getSelectedAppDtos() {
        List<AppDto> appDtos = new ArrayList<AppDto>();
        ListGridRecord[] records = getListGrid().getRecords();
        for (ListGridRecord record : records) {
            appDtos.add(((AppRecord) record).getAppDto());
        }
        return appDtos;
    }

    public void setSourceAppDtos(List<AppDto> appDtos) {
        this.appDtos = appDtos;
    }

    @Override
    public Boolean validate() {
        if (BooleanUtils.isTrue(getRequired())) {
            return getListGrid().getRecords() != null && getListGrid().getRecords().length > 0;
        } else {
            return true;
        }
    }

    private class SearchApplicationWindow extends BaseSearchWindow {

        protected AppDragAndDropItem appDragAndDropItem;
        protected ButtonItem         saveItem;

        public SearchApplicationWindow(String name) {
            super(AccessControlWeb.getConstants().appSelection());
            setWidth(FORM_ITEM_CUSTOM_WIDTH);

            appDragAndDropItem = new AppDragAndDropItem(name, "", name);
            appDragAndDropItem.setShowTitle(false);

            saveItem = new ButtonItem("save-button", MetamacWebCommon.getConstants().actionSave());
            saveItem.setAlign(Alignment.CENTER);

            getForm().setFields(appDragAndDropItem, saveItem);
        }

        public void setSourceAppDtos(List<AppDto> appDtos) {
            appDragAndDropItem.setSourceAppDtos(appDtos);
        }

        public void setTargetAppDtos(List<AppDto> appDtos) {
            appDragAndDropItem.setTargetAppDtos(appDtos);
        }

        public void resetValues() {
            appDragAndDropItem.resetValues();
        }

        public ButtonItem getSaveButton() {
            return saveItem;
        }

        public List<AppDto> getSelectedAppDtos() {
            return appDragAndDropItem.getSelectedAppDtos();
        }
    }

    private class AppDragAndDropItem extends DragAndDropItem {

        public AppDragAndDropItem(String name, String title, String dragDropType) {
            super(name, title);

            CustomListGridField titleField = new CustomListGridField(AppDS.TITLE, AccessControlWeb.getConstants().appTitle());

            sourceList.setHeight(250);
            sourceList.setWidth(FORM_ITEM_CUSTOM_WIDTH / 2);
            sourceList.setDataSource(new AppDS());
            sourceList.setUseAllDataSourceFields(false);
            sourceList.setFields(titleField);

            targetList.setHeight(250);
            targetList.setWidth(FORM_ITEM_CUSTOM_WIDTH / 2);
            targetList.setDataSource(new AppDS());
            targetList.setUseAllDataSourceFields(false);
            targetList.setFields(titleField);
        }

        public void setSourceAppDtos(List<AppDto> appDtos) {
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
        }

        @Override
        public Boolean validate() {
            return super.validate();
        }
    }
}
