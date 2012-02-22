package org.siemac.metamac.access.control.web.client.view;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getConstants;
import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.access.control.web.client.model.ds.UserDS;
import org.siemac.metamac.access.control.web.client.model.record.UserRecord;
import org.siemac.metamac.access.control.web.client.presenter.AccessPresenter;
import org.siemac.metamac.access.control.web.client.utils.RecordUtils;
import org.siemac.metamac.access.control.web.client.view.handlers.AccessUiHandlers;
import org.siemac.metamac.web.common.client.widgets.CustomListGrid;
import org.siemac.metamac.web.common.client.widgets.ListGridToolStrip;
import org.siemac.metamac.web.common.client.widgets.TitleLabel;
import org.siemac.metamac.web.common.client.widgets.form.GroupDynamicForm;
import org.siemac.metamac.web.common.client.widgets.form.MainFormLayout;
import org.siemac.metamac.web.common.client.widgets.form.fields.EmailItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.RequiredTextItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.ViewTextItem;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.Visibility;
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

    private static final String USER_USERNAME = "username";
    private static final String USER_NAME = "name";
    private static final String USER_SURNAME = "surname";
    private static final String USER_MAIL = "mail";
    
    private VLayout panel;
    private ListGridToolStrip toolStrip;
    private CustomListGrid listGrid;
    
    private TitleLabel title;
    private MainFormLayout mainFormLayout;
    private GroupDynamicForm viewForm;
    private GroupDynamicForm editionForm;
    
    private UserDto userDto;
    
    
    public AccessViewImpl() {
        super();
        panel = new VLayout();
        
        // ListGrid
        
        toolStrip = new ListGridToolStrip(getMessages().userDeleteTitle(), getMessages().userDeleteConfirmation());
        toolStrip.getNewButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                selectUser(new UserDto());
            }
        });
        toolStrip.getDeleteConfirmationWindow().getYesButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getUiHandlers().deleteUsers(getSelectedUsers());
            }
        });
        
        listGrid = new CustomListGrid();
        listGrid.setHeight(250);
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
        listGrid.setShowGroupSummary(true);
        listGrid.setGroupStartOpen(GroupStartOpen.ALL);
        listGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
            @Override
            public void onSelectionChanged(SelectionEvent event) {
                if (listGrid.getSelectedRecords() != null && listGrid.getSelectedRecords().length == 1) {
                    UserRecord record = (UserRecord) listGrid.getSelectedRecord();
                    selectUser(record.getUserDto());
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
        
       
        
        VLayout layout = new VLayout();
        layout.setMargin(15);
        layout.setAutoHeight();
        layout.addMember(toolStrip);
        layout.addMember(listGrid);
        
        // MainFormLayout
        
        title = new TitleLabel();
        title.setStyleName("subsectionTitle");
        mainFormLayout = new MainFormLayout();
        mainFormLayout.setVisibility(Visibility.HIDDEN);
        mainFormLayout.getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (editionForm.validate()) {
                    getUiHandlers().saveUser(getUser());
                }
            }
        });
        createViewForm();
        createEditionForm();
        
        panel.addMember(layout);
        panel.addMember(title);
        panel.addMember(mainFormLayout);
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
    
    private void selectUser(UserDto userDto) {
        this.userDto = userDto;
        if (userDto.getId() == null) {
            toolStrip.getDeleteButton().hide();
            listGrid.deselectAllRecords();
            mainFormLayout.setEditionMode();
        } else {
            toolStrip.getDeleteButton().show();
            mainFormLayout.setViewMode();
        }
        setUser(userDto);
        mainFormLayout.show();
    }

    private void deselectUser() {
        toolStrip.getDeleteButton().hide();
        mainFormLayout.hide();
    }
    
    private void createViewForm() {
        viewForm = new GroupDynamicForm(getConstants().user());
        ViewTextItem username = new ViewTextItem(USER_USERNAME, getConstants().userUsername());
        ViewTextItem name = new ViewTextItem(USER_NAME, getConstants().userName());
        ViewTextItem surname = new ViewTextItem(USER_SURNAME, getConstants().userSurname());
        ViewTextItem mail = new ViewTextItem(USER_MAIL, getConstants().userMail());
        viewForm.setFields(username, name, surname, mail);
        mainFormLayout.addViewCanvas(viewForm);
    }
    
    private void createEditionForm() {
        editionForm = new GroupDynamicForm(getConstants().user());
        RequiredTextItem username = new RequiredTextItem(USER_USERNAME, getConstants().userUsername());
        RequiredTextItem name = new RequiredTextItem(USER_NAME, getConstants().userName());
        RequiredTextItem surname = new RequiredTextItem(USER_SURNAME, getConstants().userSurname());
        EmailItem mail = new EmailItem(USER_MAIL, getConstants().userMail());
        mail.setRequired(true);
        editionForm.setFields(username, name, surname, mail);
        mainFormLayout.addEditionCanvas(editionForm);
    }

    @Override
    public void onUserSaved(UserDto userDto) {
        UserRecord record = RecordUtils.getUserRecord(userDto);
        ListGridRecord[] records = new ListGridRecord[listGrid.getRecords().length + 1];
        for (int i = 0; i < listGrid.getRecords().length; i++) {
            records[i] = listGrid.getRecords()[i];
        }
        records[listGrid.getRecords().length] = record;
        listGrid.setData(records);
        listGrid.selectRecord(record);
    }
    
    private void setUser(UserDto userDto) {
        title.setContents(userDto.getUsername());
        setUserViewMode(userDto);
        setUserEditionMode(userDto);
    }
    
    private void setUserViewMode(UserDto userDto) {
        viewForm.setValue(USER_USERNAME, userDto.getUsername());
        viewForm.setValue(USER_NAME, userDto.getName());
        viewForm.setValue(USER_SURNAME, userDto.getSurname());
        viewForm.setValue(USER_MAIL, userDto.getMail());
    }
    
    private void setUserEditionMode(UserDto userDto) {
        editionForm.setValue(USER_USERNAME, userDto.getUsername());
        editionForm.setValue(USER_NAME, userDto.getName());
        editionForm.setValue(USER_SURNAME, userDto.getSurname());
        editionForm.setValue(USER_MAIL, userDto.getMail());
    }
    
    private UserDto getUser() {
        userDto.setUsername(editionForm.getValueAsString(USER_USERNAME));
        userDto.setName(editionForm.getValueAsString(USER_NAME));
        userDto.setSurname(editionForm.getValueAsString(USER_SURNAME));
        userDto.setMail(editionForm.getValueAsString(USER_MAIL));
        return userDto;
    }
    
}
