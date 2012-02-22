package org.siemac.metamac.access.control.web.client.view;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getConstants;
import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.access.control.web.client.model.ds.UserDS;
import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;
import org.siemac.metamac.access.control.web.client.model.record.UserRecord;
import org.siemac.metamac.access.control.web.client.presenter.AccessPresenter;
import org.siemac.metamac.access.control.web.client.utils.RecordUtils;
import org.siemac.metamac.access.control.web.client.view.handlers.AccessUiHandlers;
import org.siemac.metamac.web.common.client.widgets.CustomListGrid;
import org.siemac.metamac.web.common.client.widgets.ListGridToolStrip;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridFieldIfFunction;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;


public class AccessViewImpl extends ViewWithUiHandlers<AccessUiHandlers> implements AccessPresenter.AccessView {

    private VLayout panel;
    private ListGridToolStrip toolStrip;
    private CustomListGrid listGrid;
    
    
    public AccessViewImpl() {
        super();
        panel = new VLayout();
        
        toolStrip = new ListGridToolStrip(getMessages().userDeleteTitle(), getMessages().userDeleteConfirmation());
        toolStrip.getDeleteConfirmationWindow().getYesButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getUiHandlers().deleteUsers(getSelectedUsers());
            }
        });
        
        
        listGrid = new CustomListGrid();
        ListGridField idField = new ListGridField(UserRecord.ID, getConstants().identifier());
        idField.setShowIfCondition(new ListGridFieldIfFunction() {
            @Override
            public boolean execute(ListGrid grid, ListGridField field, int fieldNum) {
                return false;
            }
        });
        ListGridField usernameField = new ListGridField(UserRecord.USERNAME, getConstants().userUsername());
        ListGridField nameField = new ListGridField(UserRecord.NAME, getConstants().userName());
        ListGridField surnameField = new ListGridField(UserRecord.SURNAME, getConstants().userSurname());
        ListGridField mailField = new ListGridField(UserRecord.MAIL, getConstants().userMail());
        
        UserDS userDS = new UserDS();
        listGrid.setDataSource(userDS);
        listGrid.setUseAllDataSourceFields(false);
        listGrid.setFields(idField, usernameField, nameField, surnameField, mailField);
        listGrid.setGroupByField(AccessRecord.USER);
        listGrid.setShowGroupSummary(true);
        listGrid.setGroupStartOpen(GroupStartOpen.ALL);
        listGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
            @Override
            public void onSelectionChanged(SelectionEvent event) {
                if (listGrid.getSelectedRecords() != null && listGrid.getSelectedRecords().length == 1) {
                    UserRecord record = (UserRecord) listGrid.getSelectedRecord();
                    selectUser(record.getId());
                } else {
                    // No record selected
                    deselectUser();
                    if (listGrid.getSelectedRecords().length > 1) {
                        // Delete more than one Family with one click
                        toolStrip.getDeleteButton().show();
                    }
                }
            }
        });
        
        panel.addMember(toolStrip);
        panel.addMember(listGrid);
    }
    
    @Override
    public Widget asWidget() {
        return panel;
    }

    @Override
    public void setUsersList(List<UserDto> usersDtos) {
        UserRecord[] records = new UserRecord[usersDtos.size()];
        for (int i = 0; i < usersDtos.size(); i++) {
            records[i] = RecordUtils.getUserRecord(usersDtos.get(i));
        }
        listGrid.setData(records);
    }
    
    private List<Long> getSelectedUsers() {
        List<Long> selectedUsers = new ArrayList<Long>();
        ListGridRecord[] records = listGrid.getSelectedRecords();
        for (int i = 0; i < records.length; i++) {
            UserRecord record = (UserRecord) records[i];
            selectedUsers.add(record.getId());
        }
        return selectedUsers;
    }
    
    private void selectUser(Long id) {
        if (id == null) {
            toolStrip.getDeleteButton().hide();
            listGrid.deselectAllRecords();
        } else {
            toolStrip.getDeleteButton().show();
        }
    }
    
    private void deselectUser() {
        toolStrip.getDeleteButton().hide();
    }
    
}
