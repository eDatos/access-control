package org.siemac.metamac.access.control.web.client.view;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getConstants;
import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.web.client.model.ds.AccessDS;
import org.siemac.metamac.access.control.web.client.model.ds.UserDS;
import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;
import org.siemac.metamac.access.control.web.client.model.record.UserRecord;
import org.siemac.metamac.access.control.web.client.presenter.UsersListPresenter;
import org.siemac.metamac.access.control.web.client.utils.ClientSecurityUtils;
import org.siemac.metamac.access.control.web.client.utils.CommonUtils;
import org.siemac.metamac.access.control.web.client.utils.RecordUtils;
import org.siemac.metamac.access.control.web.client.view.handlers.UsersListUiHandlers;
import org.siemac.metamac.access.control.web.client.widgets.AppDragAndDropItem;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.web.common.client.utils.ExternalItemUtils;
import org.siemac.metamac.web.common.client.widgets.CustomListGrid;
import org.siemac.metamac.web.common.client.widgets.ListGridToolStrip;
import org.siemac.metamac.web.common.client.widgets.TitleLabel;
import org.siemac.metamac.web.common.client.widgets.actions.PaginatedAction;
import org.siemac.metamac.web.common.client.widgets.actions.SearchPaginatedAction;
import org.siemac.metamac.web.common.client.widgets.form.GroupDynamicForm;
import org.siemac.metamac.web.common.client.widgets.form.MainFormLayout;
import org.siemac.metamac.web.common.client.widgets.form.fields.EmailItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.RequiredSelectItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.RequiredTextItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.SearchExternalPaginatedDragAndDropItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.ViewTextItem;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.VisibilityChangedEvent;
import com.smartgwt.client.widgets.events.VisibilityChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridFieldIfFunction;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;

public class UsersListViewImpl extends ViewWithUiHandlers<UsersListUiHandlers> implements UsersListPresenter.UsersListView {

    private static final String                    USER_USERNAME              = "username";
    private static final String                    USER_NAME                  = "name";
    private static final String                    USER_SURNAME               = "surname";
    private static final String                    USER_MAIL                  = "mail";

    private static final String                    ACCESS_ROLE                = "role";
    private static final String                    ACCESS_APP                 = "app";
    private static final String                    ACCESS_OPERATION           = "operation";

    private static final String                    USER_LAYOUT_ID             = "userlayout";

    private static final int                       OPERATION_LIST_MAX_RESULTS = 17;

    private VLayout                                panel;
    private VLayout                                subPanel;
    private ListGridToolStrip                      userToolStrip;
    private CustomListGrid                         usersListGrid;

    private Label                                  title;

    private VLayout                                userLayout;
    private MainFormLayout                         userMainFormLayout;
    private GroupDynamicForm                       viewUserForm;
    private GroupDynamicForm                       editionUserForm;

    private ListGridToolStrip                      accessToolStrip;
    private CustomListGrid                         accessListGrid;

    private MainFormLayout                         accessMainFormLayout;

    private GroupDynamicForm                       editionAccessForm;

    private SearchExternalPaginatedDragAndDropItem operationItem;
    private AppDragAndDropItem                     appItem;

    private VLayout                                accessLayout;

    private UserDto                                userDto;

    private List<RoleDto>                          roleDtos;

    private UsersListUiHandlers                    uiHandlers;

    public UsersListViewImpl() {
        super();
        panel = new VLayout();

        title = new Label(getConstants().users());
        title.setStyleName("usersSectionTitle");
        title.setHeight(30);

        // Users ListGrid

        userToolStrip = new ListGridToolStrip(getMessages().userDeleteTitle(), getMessages().userDeleteConfirmation());
        userToolStrip.getNewButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                selectUser(new UserDto());
            }
        });
        userToolStrip.getNewButton().setVisibility(ClientSecurityUtils.canCreateUser() ? Visibility.VISIBLE : Visibility.HIDDEN);

        userToolStrip.getDeleteConfirmationWindow().getYesButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                uiHandlers.deleteUsers(getSelectedUsers());
            }
        });

        usersListGrid = new CustomListGrid();
        usersListGrid.setHeight(200);
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
                        // Delete more than one User with one click
                        showUserDeleteButton();
                    }
                }
            }
        });

        VLayout listGridLayout = new VLayout();
        listGridLayout.setAutoHeight();
        listGridLayout.setMargin(15);
        listGridLayout.addMember(title);
        listGridLayout.addMember(userToolStrip);
        listGridLayout.addMember(usersListGrid);

        // User Details

        userMainFormLayout = new MainFormLayout(ClientSecurityUtils.canUpdateUser());
        userMainFormLayout.getSave().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (editionUserForm.validate()) {
                    uiHandlers.saveUser(getUser());
                }
            }
        });
        userMainFormLayout.getCancelToolStripButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                // If it is a new user, hide mainFormLayout
                if (userDto.getId() == null) {
                    userLayout.hide();
                }
            }
        });
        createViewUserForm();
        createEditionUserForm();

        // User Access

        TitleLabel accessTitle = new TitleLabel(getConstants().userAccess());
        accessTitle.setStyleName("accessSectionTitle");
        accessTitle.setHeight(30);

        accessToolStrip = new ListGridToolStrip(getMessages().accessDeleteTitle(), getMessages().accessDeleteConfirmation());
        accessToolStrip.getNewButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                selectAccess(new AccessDto());
            }
        });
        accessToolStrip.getNewButton().setVisibility(ClientSecurityUtils.canCreateAccess() ? Visibility.VISIBLE : Visibility.HIDDEN);

        accessToolStrip.getDeleteConfirmationWindow().getYesButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                uiHandlers.deleteAccess(getSelectedAccess(), userDto.getUsername());
            }
        });

        accessListGrid = new CustomListGrid();
        accessListGrid.setHeight(300);
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

        accessListGrid.setDataSource(new AccessDS());
        accessListGrid.setUseAllDataSourceFields(false);
        accessListGrid.setFields(accessIdField, roleField, appField, opField);
        accessListGrid.setShowGroupSummary(true);
        accessListGrid.setGroupStartOpen(GroupStartOpen.ALL);
        accessListGrid.setGroupByField(AccessRecord.ROLE);
        accessListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {

            @Override
            public void onSelectionChanged(SelectionEvent event) {
                if (accessListGrid.getSelectedRecord() != null && accessListGrid.getSelectedRecords().length == 1) {
                    AccessRecord record = (AccessRecord) accessListGrid.getSelectedRecord();
                    selectAccess(record.getAccessDto());
                } else {
                    // No record selected
                    deselectAccess();
                    if (accessListGrid.getSelectedRecords().length > 1) {
                        // Delete more than one Access with one click
                        showAccessDeleteButton();
                    }
                }
            }
        });

        accessMainFormLayout = new MainFormLayout();
        accessMainFormLayout.setVisibility(Visibility.HIDDEN);
        accessMainFormLayout.getSave().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (editionAccessForm.getItem(ACCESS_ROLE).validate() && appItem.validate()) {
                    uiHandlers.saveAccess(getAccessList());
                }
            }
        });
        accessMainFormLayout.getCancelToolStripButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                // If it is a new user, hide mainFormLayout
                // if (accessDto.getId() == null) {
                accessMainFormLayout.hide();
                // }
            }
        });
        accessMainFormLayout.addVisibilityChangedHandler(new VisibilityChangedHandler() {

            @Override
            public void onVisibilityChanged(VisibilityChangedEvent event) {
                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

                    @Override
                    public void execute() {
                        // Scroll to bottom
                        subPanel.scrollTo(0, subPanel.getMember(USER_LAYOUT_ID).getBottom());
                    }
                });
            }
        });

        createEditionAccessForm();

        VLayout subAccessLayout = new VLayout();
        subAccessLayout.setAutoHeight();
        subAccessLayout.setBorder("1px solid #D9D9D9");
        subAccessLayout.addMember(accessToolStrip);
        subAccessLayout.addMember(accessListGrid);
        subAccessLayout.addMember(accessMainFormLayout);

        userLayout = new VLayout();
        userLayout.setID(USER_LAYOUT_ID);
        userLayout.setVisibility(Visibility.HIDDEN);
        userLayout.addMember(userMainFormLayout);

        accessLayout = new VLayout();
        accessLayout.setMargin(15);
        accessLayout.addMember(accessTitle);
        accessLayout.addMember(subAccessLayout);

        userLayout.addMember(accessLayout);

        subPanel = new VLayout();
        subPanel.setOverflow(Overflow.SCROLL);
        subPanel.addMember(listGridLayout);
        subPanel.addMember(userLayout);

        panel.addMember(subPanel);
    }

    @Override
    public Widget asWidget() {
        return panel;
    }

    @Override
    public void setUiHandlers(UsersListUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        if (slot == UsersListPresenter.TYPE_SetContextAreaContentToolBar) {
            if (content != null) {
                panel.addMember(content, 0);
            }
        } else {
            // To support inheritance in your views it is good practice to call super.setInSlot when you can't handle the call.
            // Who knows, maybe the parent class knows what to do with this slot.
            super.setInSlot(slot, content);
        }
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
        accessLayout.show();
        accessMainFormLayout.hide();
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
        userMainFormLayout.clearTitleLabelContents();
        if (userDto.getId() == null) {
            userToolStrip.getDeleteButton().hide();
            usersListGrid.deselectAllRecords();
            userMainFormLayout.setEditionMode();
        } else {
            showUserDeleteButton();
            userMainFormLayout.setViewMode();
            uiHandlers.retrieveUserAccess(userDto.getUsername());
        }
        userLayout.show();
        if (userDto.getId() == null) {
            // Do not show user access panel
            accessLayout.hide();
        }
        setUser(userDto);
    }

    private void deselectUser() {
        userToolStrip.getDeleteButton().hide();
        userLayout.hide();
    }

    private void createViewUserForm() {
        viewUserForm = new GroupDynamicForm(getConstants().user());
        ViewTextItem username = new ViewTextItem(USER_USERNAME, getConstants().userUsername());
        ViewTextItem name = new ViewTextItem(USER_NAME, getConstants().userName());
        ViewTextItem surname = new ViewTextItem(USER_SURNAME, getConstants().userSurname());
        ViewTextItem mail = new ViewTextItem(USER_MAIL, getConstants().userMail());
        viewUserForm.setFields(username, name, surname, mail);
        userMainFormLayout.addViewCanvas(viewUserForm);
    }

    private void createEditionUserForm() {
        editionUserForm = new GroupDynamicForm(getConstants().user());
        RequiredTextItem username = new RequiredTextItem(USER_USERNAME, getConstants().userUsername());
        RequiredTextItem name = new RequiredTextItem(USER_NAME, getConstants().userName());
        RequiredTextItem surname = new RequiredTextItem(USER_SURNAME, getConstants().userSurname());
        EmailItem mail = new EmailItem(USER_MAIL, getConstants().userMail());
        mail.setRequired(true);
        editionUserForm.setFields(username, name, surname, mail);
        userMainFormLayout.addEditionCanvas(editionUserForm);
    }

    @Override
    public void onUserSaved(List<UserDto> userDtos, UserDto userDto) {
        UserRecord[] records = new UserRecord[userDtos.size()];
        UserRecord savedRecord = null;
        for (int i = 0; i < userDtos.size(); i++) {
            UserRecord userRecord = RecordUtils.getUserRecord(userDtos.get(i));
            records[i] = userRecord;
            if (userDto.getId().compareTo(userRecord.getId()) == 0) {
                savedRecord = userRecord;
            }
        }
        usersListGrid.setData(records);
        if (savedRecord != null) {
            usersListGrid.selectRecord(savedRecord);
        }
    }

    private void setUser(UserDto userDto) {
        userMainFormLayout.setTitleLabelContents(userDto.getUsername());
        setUserViewMode(userDto);
        setUserEditionMode(userDto);
    }

    private void setUserViewMode(UserDto userDto) {
        viewUserForm.setValue(USER_USERNAME, userDto.getUsername());
        viewUserForm.setValue(USER_NAME, userDto.getName());
        viewUserForm.setValue(USER_SURNAME, userDto.getSurname());
        viewUserForm.setValue(USER_MAIL, userDto.getMail());
    }

    private void setUserEditionMode(UserDto userDto) {
        editionUserForm.setValue(USER_USERNAME, userDto.getUsername());
        editionUserForm.setValue(USER_NAME, userDto.getName());
        editionUserForm.setValue(USER_SURNAME, userDto.getSurname());
        editionUserForm.setValue(USER_MAIL, userDto.getMail());
    }

    private UserDto getUser() {
        userDto.setUsername(editionUserForm.getValueAsString(USER_USERNAME));
        userDto.setName(editionUserForm.getValueAsString(USER_NAME));
        userDto.setSurname(editionUserForm.getValueAsString(USER_SURNAME));
        userDto.setMail(editionUserForm.getValueAsString(USER_MAIL));
        return userDto;
    }

    private List<Long> getSelectedAccess() {
        List<Long> selectedAccess = new ArrayList<Long>();
        ListGridRecord[] records = accessListGrid.getSelectedRecords();
        for (int i = 0; i < records.length; i++) {
            AccessRecord record = (AccessRecord) records[i];
            selectedAccess.add(record.getId());
        }
        return selectedAccess;
    }

    private void selectAccess(AccessDto accessDto) {
        if (accessDto.getId() == null) {
            accessToolStrip.getDeleteButton().hide();
            accessListGrid.deselectAllRecords();
            accessMainFormLayout.setEditionMode();
            accessMainFormLayout.show();
            accessMainFormLayout.focus();
            // Reset dragAndDrop items
            appItem.resetValues();
            operationItem.clearValue();

            // Load statistical operations
            uiHandlers.retrievePaginatedOperations(0, OPERATION_LIST_MAX_RESULTS, null);
        } else {
            showAccessDeleteButton();
            accessMainFormLayout.hide();
        }
        setAccess(accessDto);
    }

    private void deselectAccess() {
        accessToolStrip.getDeleteButton().hide();
    }

    private void createEditionAccessForm() {
        editionAccessForm = new GroupDynamicForm(getConstants().role());
        RequiredSelectItem role = new RequiredSelectItem(ACCESS_ROLE, getConstants().role());
        role.setWidth(305);
        appItem = new AppDragAndDropItem(ACCESS_APP, getConstants().app(), ACCESS_APP);
        appItem.setRequired(true);
        appItem.setStartRow(true);
        operationItem = new SearchExternalPaginatedDragAndDropItem(ACCESS_OPERATION, getConstants().statisticalOperation(), ACCESS_OPERATION, OPERATION_LIST_MAX_RESULTS, 300, new PaginatedAction() {

            @Override
            public void retrieveResultSet(int firstResult, int maxResults) {
                uiHandlers.retrievePaginatedOperations(firstResult, maxResults, null);
            }
        });
        operationItem.setSearchAction(new SearchPaginatedAction() {

            @Override
            public void retrieveResultSet(int firstResult, int maxResults, String code) {
                uiHandlers.retrievePaginatedOperations(firstResult, maxResults, code);
            }
        });
        editionAccessForm.setFields(role, appItem, operationItem);
        accessMainFormLayout.addEditionCanvas(editionAccessForm);
    }

    private void setAccess(AccessDto accessDto) {
        setAccessEditionMode(accessDto);
    }

    private void setAccessEditionMode(AccessDto accessDto) {
        editionAccessForm.setValue(ACCESS_ROLE, accessDto.getRole() != null ? accessDto.getRole().getId().toString() : null);
    }

    private List<AccessDto> getAccessList() {
        List<AccessDto> accessList = new ArrayList<AccessDto>();
        List<AppDto> applications = appItem.getSelectedAppDtos();
        List<ExternalItemDto> operations = operationItem.getSelectedExternalItems();
        // REMOVE TITLE: operation title can not be stored because can be modified
        operations = ExternalItemUtils.removeTitle(operations);
        if (operations.isEmpty()) {
            for (AppDto app : applications) {
                AccessDto accessDto = new AccessDto();
                accessDto.setUser(userDto);
                accessDto.setRole(getRoleDtoById(editionAccessForm.getValueAsString(ACCESS_ROLE)));
                accessDto.setApp(app);
                accessList.add(accessDto);
            }
        } else {
            for (AppDto app : applications) {
                for (ExternalItemDto op : operations) {
                    AccessDto accessDto = new AccessDto();
                    accessDto.setUser(userDto);
                    accessDto.setRole(getRoleDtoById(editionAccessForm.getValueAsString(ACCESS_ROLE)));
                    accessDto.setApp(app);
                    accessDto.setOperation(op);
                    accessList.add(accessDto);
                }
            }
        }
        return accessList;
    }

    @Override
    public void setRoleList(List<RoleDto> roles) {
        this.roleDtos = roles;
        editionAccessForm.getItem(ACCESS_ROLE).setValueMap(CommonUtils.getRolesHashMap(roles));
    }

    @Override
    public void setApplicationList(List<AppDto> apps) {
        appItem.setSourceAppDtos(apps);
    }

    @Override
    public void setOperations(List<ExternalItemDto> operations, int firstResult, int totalResults) {
        operationItem.setSourceExternalItems(operations);
        operationItem.refreshSourcePaginationInfo(firstResult, operations.size(), totalResults);
    }

    private RoleDto getRoleDtoById(String id) {
        for (RoleDto role : roleDtos) {
            if (role.getId().compareTo(Long.valueOf(id)) == 0) {
                return role;
            }
        }
        return new RoleDto();
    }

    @Override
    public void onAccessSaved(List<AccessDto> accessDtos, AccessDto accessDto) {
        AccessRecord[] records = new AccessRecord[accessDtos.size()];
        AccessRecord savedRecord = null;
        for (int i = 0; i < accessDtos.size(); i++) {
            AccessRecord accessRecord = RecordUtils.getAccessRecord(accessDtos.get(i));
            records[i] = accessRecord;
            if (accessDto.getId().compareTo(accessRecord.getId()) == 0) {
                savedRecord = accessRecord;
            }
        }
        accessListGrid.setData(records);
        if (savedRecord != null) {
            accessListGrid.selectRecord(savedRecord);
        }
    }

    private void showUserDeleteButton() {
        if (ClientSecurityUtils.canDeleteUser()) {
            userToolStrip.getDeleteButton().show();
        }
    }

    private void showAccessDeleteButton() {
        if (ClientSecurityUtils.canDeleteAccess()) {
            accessToolStrip.getDeleteButton().show();
        }
    }

}
