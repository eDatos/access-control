package org.siemac.metamac.access.control.web.client.view;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getConstants;
import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.access.control.web.client.model.ds.AccessDS;
import org.siemac.metamac.access.control.web.client.model.ds.UserDS;
import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;
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
    private CustomListGrid usersListGrid;
    
    private VLayout userLayout;
    private TitleLabel title;
    private MainFormLayout mainFormLayout;
    private GroupDynamicForm viewForm;
    private GroupDynamicForm editionForm;
    
    private ListGrid accessListGrid;
    
    private UserDto userDto;
    
    
    public AccessViewImpl() {
        super();
        panel = new VLayout();
        
        // Users ListGrid
        
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
        
        usersListGrid = new CustomListGrid();
        usersListGrid.setHeight(250);
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
        usersListGrid.setDataSource(userDS);
        usersListGrid.setUseAllDataSourceFields(false);
        usersListGrid.setFields(idField, usernameField, nameField, surnameField, mailField);
        usersListGrid.setShowGroupSummary(true);
        usersListGrid.setGroupStartOpen(GroupStartOpen.ALL);
        usersListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
            @Override
            public void onSelectionChanged(SelectionEvent event) {
                if (usersListGrid.getSelectedRecords() != null && usersListGrid.getSelectedRecords().length == 1) {
                    UserRecord record = (UserRecord) usersListGrid.getSelectedRecord();
                    selectUser(record.getUserDto());
                } else {
                    // No record selected
                    deselectUser();
                    if (usersListGrid.getSelectedRecords().length > 1) {
                        // Delete more than one Family with one click
                        toolStrip.getDeleteButton().show();
                    }
                }
            }
        });
        
       
        
        VLayout listGridLayout = new VLayout();
        listGridLayout.setMargin(15);
        listGridLayout.setAutoHeight();
        listGridLayout.addMember(toolStrip);
        listGridLayout.addMember(usersListGrid);
        
        // User Details
        
        title = new TitleLabel();
        title.setStyleName("subsectionTitle");
        mainFormLayout = new MainFormLayout();
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
        
        // User Access
        
        TitleLabel userAccessTitle = new TitleLabel(getConstants().userAccess());
        userAccessTitle.setStyleName("subsectionTitle");
        
        accessListGrid = new ListGrid();
        accessListGrid.setMargin(15);
        ListGridField accessIdField = new ListGridField(AccessRecord.ID, getConstants().identifier());
        accessIdField.setShowIfCondition(new ListGridFieldIfFunction() {
            @Override
            public boolean execute(ListGrid grid, ListGridField field, int fieldNum) {
                return false;
            }
        });
        ListGridField roleField = new ListGridField(AccessRecord.ROLE, getConstants().role());
        ListGridField appField = new ListGridField(AccessRecord.APP, getConstants().app());
        ListGridField opField = new ListGridField(AccessRecord.OPERATION, getConstants().statisticalOperation());
        accessListGrid.setFields(accessIdField, roleField, appField, opField);
        accessListGrid.setDataSource(new AccessDS());
        
        userLayout = new VLayout();
        userLayout.addMember(title);
        userLayout.addMember(mainFormLayout);
        userLayout.addMember(userAccessTitle);
        userLayout.addMember(accessListGrid);
        
        panel.addMember(listGridLayout);
        panel.addMember(userLayout);
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
        usersListGrid.setData(records);
    }
    
    @Override
    public void setUserAccess(List<AccessDto> accessDtos) {
        AccessRecord[] records = new AccessRecord[accessDtos.size()];
        for (int i = 0; i < accessDtos.size(); i++) {
            records[i] = RecordUtils.getAccessRecord(accessDtos.get(i));
        }
        accessListGrid.setData(records);
    }
    
    private List<Long> getSelectedUsers() {
        List<Long> selectedUsers = new ArrayList<Long>();
        ListGridRecord[] records = usersListGrid.getSelectedRecords();
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
            usersListGrid.deselectAllRecords();
            mainFormLayout.setEditionMode();
        } else {
            toolStrip.getDeleteButton().show();
            mainFormLayout.setViewMode();
            getUiHandlers().retrieveUserAccess(userDto.getUsername());
        }
        userLayout.show();
        setUser(userDto);
    }

    private void deselectUser() {
        toolStrip.getDeleteButton().hide();
        userLayout.hide();
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
        ListGridRecord[] records = new ListGridRecord[usersListGrid.getRecords().length + 1];
        for (int i = 0; i < usersListGrid.getRecords().length; i++) {
            records[i] = usersListGrid.getRecords()[i];
        }
        records[usersListGrid.getRecords().length] = record;
        usersListGrid.setData(records);
        usersListGrid.selectRecord(record);
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
