package org.siemac.metamac.access.control.web.client.widgets;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.core.common.dto.ExternalItemBtDto;
import org.siemac.metamac.web.common.client.model.ds.ExternalItemDS;
import org.siemac.metamac.web.common.client.model.record.ExternalItemRecord;
import org.siemac.metamac.web.common.client.utils.RecordUtils;
import org.siemac.metamac.web.common.client.widgets.PaginatedAction;
import org.siemac.metamac.web.common.client.widgets.PaginatedBaseCustomListGrid;
import org.siemac.metamac.web.common.client.widgets.form.fields.CustomCanvasItem;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.widgets.TransferImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;

public class ExternalPaginatedDragAndDropItem extends CustomCanvasItem {

    protected PaginatedBaseCustomListGrid sourceList;
    protected ListGrid                    targetList;

    protected boolean                     required;

    private String                        name;

    public ExternalPaginatedDragAndDropItem(String name, String title, String dragDropType, int maxResults, PaginatedAction action) {
        super(name, title);
        this.name = name;

        setCellStyle("dragAndDropCellStyle");

        ListGridField idField = new ListGridField(ExternalItemRecord.CODE);
        idField.setShowHover(true);
        idField.setHoverCustomizer(new HoverCustomizer() {

            @Override
            public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) {
                ExternalItemRecord externalItemRecord = (ExternalItemRecord) record;
                return externalItemRecord.getCode();
            }
        });

        sourceList = new PaginatedBaseCustomListGrid(maxResults, action);
        sourceList.getListGrid().setShowHeader(false);
        sourceList.getListGrid().setLeaveScrollbarGap(false);
        sourceList.getListGrid().setAlternateRecordStyles(false);
        sourceList.setWidth(300);
        sourceList.setHeight(400);
        sourceList.getListGrid().setCanDragRecordsOut(true);
        sourceList.getListGrid().setCanAcceptDroppedRecords(true);
        sourceList.getListGrid().setCanReorderFields(true);
        sourceList.getListGrid().setDragDataAction(DragDataAction.MOVE);
        // sourceList.getListGrid().setSaveLocally(true);
        sourceList.getListGrid().setDragType(dragDropType);
        sourceList.getListGrid().setDropTypes(dragDropType);
        sourceList.getListGrid().setFields(idField);
        sourceList.getListGrid().setDataSource(new ExternalItemDS());
        sourceList.getListGrid().setUseAllDataSourceFields(false);

        targetList = new ListGrid();
        targetList.setLeaveScrollbarGap(false);
        targetList.setShowHeader(false);
        targetList.setAlternateRecordStyles(false);
        targetList.setWidth(300);
        targetList.setHeight(400);
        targetList.setCanDragRecordsOut(true);
        targetList.setCanAcceptDroppedRecords(true);
        targetList.setCanReorderRecords(true);
        // targetList.setSaveLocally(true);
        targetList.setDragType(dragDropType);
        targetList.setDropTypes(dragDropType);

        targetList.setFields(idField);
        targetList.setDataSource(new ExternalItemDS());
        targetList.setUseAllDataSourceFields(false);

        VStack buttonStack = new VStack(10);
        buttonStack.setWidth(32);
        buttonStack.setHeight(74);
        buttonStack.setLayoutAlign(Alignment.CENTER);

        TransferImgButton rightImg = new TransferImgButton(TransferImgButton.RIGHT);
        rightImg.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                // targetList.transferSelectedData(sourceList.getListGrid());
                ListGridRecord[] records = sourceList.getListGrid().getSelectedRecords();
                sourceList.removeListGridSelectedData();
                for (ListGridRecord record : records) {
                    targetList.addData(record);
                }
            }
        });

        TransferImgButton leftImg = new TransferImgButton(TransferImgButton.LEFT);
        leftImg.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                sourceList.getListGrid().transferSelectedData(targetList);
            }
        });

        TransferImgButton rightAll = new TransferImgButton(TransferImgButton.RIGHT_ALL);
        rightAll.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                Record[] records = sourceList.getListGrid().getRecords();
                sourceList.getListGrid().setData(new Record[]{});
                for (Record record : records) {
                    targetList.addData(record);
                }
            }
        });

        TransferImgButton leftAll = new TransferImgButton(TransferImgButton.LEFT_ALL);
        leftAll.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                Record[] records = targetList.getRecords();
                targetList.setData(new Record[]{});
                for (Record record : records) {
                    sourceList.getListGrid().addData(record);
                }
            }
        });

        buttonStack.addMember(rightImg);
        buttonStack.addMember(leftImg);
        buttonStack.addMember(rightAll);
        buttonStack.addMember(leftAll);

        HLayout hLayout = new HLayout(1);
        hLayout.addMember(sourceList);
        hLayout.addMember(buttonStack);
        hLayout.addMember(targetList);

        VLayout vLayout = new VLayout();
        vLayout.addMember(hLayout);

        setCanvas(vLayout);
    }

    public void setRequired(boolean required) {
        this.required = required;
        super.setRequired(required);
        setTitleStyle("requiredFormLabel");
    }

    public void clearValue() {
        sourceList.getListGrid().selectAllRecords();
        sourceList.getListGrid().removeSelectedData();
        targetList.selectAllRecords();
        targetList.removeSelectedData();
    }

    @Override
    public Boolean validate() {
        if (required && targetList.getRecords().length == 0) {
            return false;
        }
        getForm().clearFieldErrors(name, true);
        return true;
    }

    public void setSourceExternalItems(List<ExternalItemBtDto> externalItems) {
        ExternalItemRecord[] records = new ExternalItemRecord[externalItems.size()];
        for (int i = 0; i < externalItems.size(); i++) {
            records[i] = RecordUtils.getExternalItemRecord(externalItems.get(i));
        }
        sourceList.getListGrid().setData(records);

    }

    public void setTargetExternalItems(List<ExternalItemBtDto> externalItems) {
        ExternalItemRecord[] records = new ExternalItemRecord[externalItems.size()];
        for (int i = 0; i < externalItems.size(); i++) {
            records[i] = RecordUtils.getExternalItemRecord(externalItems.get(i));
        }
        targetList.setData(records);
    }

    public List<ExternalItemBtDto> getSelectedExternalItems() {
        List<ExternalItemBtDto> selectedItems = new ArrayList<ExternalItemBtDto>();
        ListGridRecord[] records = targetList.getRecords();
        for (int i = 0; i < records.length; i++) {
            ExternalItemRecord record = (ExternalItemRecord) records[i];
            selectedItems.add(record.getExternalItemBtDto());
        }
        return selectedItems;
    }

    public void resetValues() {
        clearValue();
    }

    public void refreshSorucePaginationInfo(int firstResult, int elementsInPage, int totalResults) {
        sourceList.refreshPaginationInfo(firstResult, elementsInPage, totalResults);
    }

}
