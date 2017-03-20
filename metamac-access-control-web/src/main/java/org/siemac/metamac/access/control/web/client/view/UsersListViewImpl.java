package org.siemac.metamac.access.control.web.client.view;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getConstants;
import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getMessages;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.access.control.web.client.model.ds.AccessDS;
import org.siemac.metamac.access.control.web.client.model.ds.UserDS;
import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;
import org.siemac.metamac.access.control.web.client.model.record.UserRecord;
import org.siemac.metamac.access.control.web.client.presenter.UsersListPresenter;
import org.siemac.metamac.access.control.web.client.utils.ClientSecurityUtils;
import org.siemac.metamac.access.control.web.client.utils.CommonUtils;
import org.siemac.metamac.access.control.web.client.utils.RecordUtils;
import org.siemac.metamac.access.control.web.client.view.handlers.UsersListUiHandlers;
import org.siemac.metamac.access.control.web.client.widgets.NavigableListGrid;
import org.siemac.metamac.access.control.web.client.widgets.SearchApplicationItem;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.core.common.util.shared.StringUtils;
import org.siemac.metamac.web.common.client.MetamacWebCommon;
import org.siemac.metamac.web.common.client.constants.CommonWebConstants;
import org.siemac.metamac.web.common.client.utils.BooleanWebUtils;
import org.siemac.metamac.web.common.client.utils.ExternalItemUtils;
import org.siemac.metamac.web.common.client.utils.ListGridUtils;
import org.siemac.metamac.web.common.client.widgets.CustomLinkListGridField;
import org.siemac.metamac.web.common.client.widgets.CustomListGrid;
import org.siemac.metamac.web.common.client.widgets.CustomListGridField;
import org.siemac.metamac.web.common.client.widgets.CustomSectionStack;
import org.siemac.metamac.web.common.client.widgets.ListGridToolStrip;
import org.siemac.metamac.web.common.client.widgets.form.GroupDynamicForm;
import org.siemac.metamac.web.common.client.widgets.form.MainFormLayout;
import org.siemac.metamac.web.common.client.widgets.form.fields.BooleanRequiredSelectItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.BooleanSelectItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.EmailItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.RequiredSelectItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.RequiredTextItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.ViewTextItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.external.SearchMultiExternalItemSimpleItem;
import org.siemac.metamac.web.common.shared.criteria.MetamacWebCriteria;

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
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.FormItemIfFunction;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridFieldIfFunction;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;

public class UsersListViewImpl extends ViewWithUiHandlers<UsersListUiHandlers> implements UsersListPresenter.UsersListView {

    private static final String     USER_USERNAME          = "username";
    private static final String     USER_NAME              = "name";
    private static final String     USER_SURNAME           = "surname";
    private static final String     USER_MAIL              = "mail";

    private static final String     ACCESS_ROLE            = "role";
    private static final String     ACCESS_APP             = "app";
    private static final String     ACCESS_OPERATION       = "operation";
    private static final String     ACCESS_SEND_EMAIL      = "sendEmail";
    private static final String     ACCESS_SEND_EMAIL_VIEW = "sendEmailView";

    private static final String     USER_LAYOUT_ID         = "userlayout";

    private final VLayout           panel;
    private final VLayout           subPanel;

    private final ListGridToolStrip userToolStrip;
    private final CustomListGrid    usersListGrid;

    private final Label             title;

    private final UserPanel         userPanel;

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
                userPanel.selectUser(new UserDto());
            }
        });
        userToolStrip.getNewButton().setVisibility(ClientSecurityUtils.canCreateUser() ? Visibility.VISIBLE : Visibility.HIDDEN);

        userToolStrip.getDeleteConfirmationWindow().getYesButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                getUiHandlers().deleteUsers(getSelectedUsers());
            }
        });

        usersListGrid = new CustomListGrid();
        usersListGrid.setHeight(200);
        CustomListGridField idField = new CustomListGridField(UserRecord.ID, getConstants().identifier());
        idField.setShowIfCondition(ListGridUtils.getFalseListGridFieldIfFunction());
        CustomListGridField usernameField = new CustomListGridField(UserRecord.USERNAME, getConstants().userUsername());
        CustomListGridField nameField = new CustomListGridField(UserRecord.NAME, getConstants().userName());
        CustomListGridField surnameField = new CustomListGridField(UserRecord.SURNAME, getConstants().userSurname());
        CustomListGridField mailField = new CustomListGridField(UserRecord.MAIL, getConstants().userMail());

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
                    userPanel.selectUser(record.getUserDto());
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
        userPanel = new UserPanel();

        subPanel = new VLayout();
        subPanel.setOverflow(Overflow.SCROLL);
        subPanel.addMember(listGridLayout);
        subPanel.addMember(userPanel);

        panel.addMember(subPanel);
    }

    @Override
    public Widget asWidget() {
        return panel;
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

    private List<Long> getSelectedUsers() {
        List<Long> selectedUsers = new ArrayList<Long>();
        ListGridRecord[] records = usersListGrid.getSelectedRecords();
        for (int i = 0; i < records.length; i++) {
            UserRecord record = (UserRecord) records[i];
            selectedUsers.add(record.getId());
        }
        return selectedUsers;
    }

    private void deselectUser() {
        userToolStrip.getDeleteButton().hide();
        userPanel.hide();
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

    private void showUserDeleteButton() {
        if (ClientSecurityUtils.canDeleteUser()) {
            userToolStrip.getDeleteButton().show();
        }
    }

    private void scrollPanelToBottom() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

            @Override
            public void execute() {
                subPanel.scrollToBottom();
            }
        });
    }

    // USER RELATED ACTIONS DELEGATE ACTIONS

    @Override
    public void setUserAccess(List<AccessDto> accessDtos) {
        userPanel.userAccessesPanel.setUserAccesses(accessDtos);
    }

    @Override
    public void setRoleList(List<RoleDto> roles) {
        userPanel.userAccessesPanel.setRolesList(roles);
    }

    @Override
    public void setApplicationList(List<AppDto> apps) {
        userPanel.userAccessesPanel.setApplicationList(apps);
    }

    @Override
    public void setOperations(List<ExternalItemDto> operations, int firstResult, int totalResults) {
        userPanel.userAccessesPanel.setOperations(operations, firstResult, totalResults);
    }

    @Override
    public void onAccessSaved(List<AccessDto> accessDtos, AccessDto accessDto) {
        userPanel.userAccessesPanel.onAccessSaved(accessDtos, accessDto);
    }

    private class UserPanel extends VLayout {

        private final MainFormLayout    userMainFormLayout;
        private GroupDynamicForm        viewUserForm;
        private GroupDynamicForm        editionUserForm;

        private UserDto                 userDto;
        private final UserAccessesPanel userAccessesPanel;

        public UserPanel() {
            setID(USER_LAYOUT_ID);
            setVisibility(Visibility.HIDDEN);

            userMainFormLayout = new MainFormLayout(ClientSecurityUtils.canUpdateUser());
            userMainFormLayout.getSave().addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    if (editionUserForm.validate()) {
                        getUiHandlers().saveUser(getUser());
                    }
                }
            });
            userMainFormLayout.getCancelToolStripButton().addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    // If it is a new user, hide mainFormLayout
                    if (userDto.getId() == null) {
                        hide();
                    }
                }
            });
            createViewUserForm();
            createEditionUserForm();

            // User Access

            userAccessesPanel = new UserAccessesPanel();

            addMember(userMainFormLayout);
            addMember(userAccessesPanel);
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
                getUiHandlers().retrieveUserAccess(userDto.getUsername());
            }
            show();
            if (userDto.getId() == null) {
                // Do not show user access panel
                userAccessesPanel.hide();
            }
            setUser(userDto);
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

        private class UserAccessesPanel extends VLayout {

            private ListGridToolStrip accessToolStrip;
            private NavigableListGrid accessListGrid;
            private MainFormLayout    accessMainFormLayout;

            private GroupDynamicForm  editionAccessForm;

            private List<RoleDto>     roleDtos;

            public UserAccessesPanel() {
                setMargin(15);

                accessToolStrip = new ListGridToolStrip(getMessages().accessDeleteTitle(), getMessages().accessDeleteConfirmation());
                accessToolStrip.getNewButton().addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent event) {
                        selectAccess(buildNewAccess());
                    }

                    private AccessDto buildNewAccess() {
                        AccessDto accessDto = new AccessDto();
                        accessDto.setSendEmail(Boolean.TRUE);
                        return accessDto;
                    }
                });
                accessToolStrip.getNewButton().setVisibility(ClientSecurityUtils.canCreateAccess() ? Visibility.VISIBLE : Visibility.HIDDEN);

                accessToolStrip.getDeleteConfirmationWindow().getYesButton().addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent event) {
                        getUiHandlers().deleteAccess(getSelectedAccess(), userDto.getUsername());
                    }
                });

                accessListGrid = new NavigableListGrid();
                ListGridUtils.setCheckBoxSelectionType(accessListGrid);
                accessListGrid.setHeight(300);
                CustomListGridField accessIdField = new CustomListGridField(AccessRecord.ID, getConstants().identifier());
                accessIdField.setShowIfCondition(new ListGridFieldIfFunction() {

                    @Override
                    public boolean execute(ListGrid grid, ListGridField field, int fieldNum) {
                        return false;
                    }
                });
                CustomListGridField roleField = new CustomListGridField(AccessRecord.ROLE, getConstants().role());
                CustomListGridField appField = new CustomListGridField(AccessRecord.APP, getConstants().app());
                CustomLinkListGridField opField = new CustomLinkListGridField(AccessRecord.OPERATION, getConstants().statisticalOperation());
                CustomListGridField sendEmail = new CustomListGridField(AccessRecord.SEND_EMAIL, getConstants().sendEmail());

                accessListGrid.setDataSource(new AccessDS());
                accessListGrid.setUseAllDataSourceFields(false);
                accessListGrid.setFields(accessIdField, roleField, appField, opField, sendEmail);
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
                accessMainFormLayout.setVisible(false);
                accessMainFormLayout.getSave().addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent event) {
                        if (editionAccessForm.getItem(ACCESS_ROLE).validate() && editionAccessForm.getItem(ACCESS_APP).validate()) {
                            getUiHandlers().saveAccess(getAccessList());
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
                    public void onVisibilityChanged(final VisibilityChangedEvent event) {
                        scrollPanelToBottom();
                    }
                });

                createEditionAccessForm();

                VLayout subAccessLayout = new VLayout();
                subAccessLayout.setAutoHeight();
                subAccessLayout.setBorder("1px solid #D9D9D9");
                subAccessLayout.addMember(accessToolStrip);
                subAccessLayout.addMember(accessListGrid);
                subAccessLayout.addMember(accessMainFormLayout);

                CustomSectionStack userAccessSectionStack = new CustomSectionStack(getConstants().userAccess());
                userAccessSectionStack.setStyleName("customSectionStackStyle");
                userAccessSectionStack.getDefaultSection().setItems(subAccessLayout);
                userAccessSectionStack.getDefaultSection().setExpanded(true);

                addMember(userAccessSectionStack);
            }

            public void setOperations(List<ExternalItemDto> operations, int firstResult, int totalResults) {
                ((SearchMultiExternalItemSimpleItem) editionAccessForm.getItem(ACCESS_OPERATION)).setResources(operations, firstResult, totalResults);
            }

            public void setApplicationList(List<AppDto> apps) {
                ((SearchApplicationItem) editionAccessForm.getItem(ACCESS_APP)).setSourceAppDtos(apps);
            }

            public void setRolesList(List<RoleDto> roles) {
                this.roleDtos = roles;
                editionAccessForm.getItem(ACCESS_ROLE).setValueMap(CommonUtils.getRolesHashMap(roles));
            }

            private List<AccessDto> getAccessList() {
                List<AccessDto> accessList = new ArrayList<AccessDto>();
                List<AppDto> applications = ((SearchApplicationItem) editionAccessForm.getItem(ACCESS_APP)).getSelectedAppDtos();
                Boolean sendEmail = ((BooleanSelectItem) editionAccessForm.getItem(ACCESS_SEND_EMAIL)).getBooleanValue();

                List<ExternalItemDto> operations = ((SearchMultiExternalItemSimpleItem) editionAccessForm.getItem(ACCESS_OPERATION)).getSelectedRelatedResources();
                operations = ExternalItemUtils.removeTitle(operations); // We have to remove title because it can be modified

                if (operations.isEmpty()) {
                    for (AppDto app : applications) {
                        AccessDto accessDto = createBasicAccess(sendEmail, app);
                        accessList.add(accessDto);
                    }
                } else {
                    for (AppDto app : applications) {
                        for (ExternalItemDto op : operations) {
                            AccessDto accessDto = createBasicAccess(sendEmail, app);
                            accessDto.setOperation(op);
                            accessList.add(accessDto);
                        }
                    }
                }
                return accessList;
            }

            private AccessDto createBasicAccess(Boolean sendEmail, AppDto app) {
                AccessDto accessDto = new AccessDto();
                accessDto.setUser(userPanel.userDto);
                accessDto.setRole(getRoleDtoById(editionAccessForm.getValueAsString(ACCESS_ROLE)));
                accessDto.setApp(app);
                accessDto.setSendEmail(isRoleLector(accessDto.getRole()) ? false : sendEmail);
                return accessDto;
            }

            private RoleDto getRoleDtoById(String id) {
                for (RoleDto role : roleDtos) {
                    if (role.getId().compareTo(Long.valueOf(id)) == 0) {
                        return role;
                    }
                }
                return new RoleDto();
            }

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

            private void createEditionAccessForm() {
                editionAccessForm = new GroupDynamicForm(getConstants().role());

                BooleanRequiredSelectItem sendEmail = new BooleanRequiredSelectItem(ACCESS_SEND_EMAIL, getConstants().sendEmail());
                sendEmail.setShowIfCondition(new FormItemIfFunction() {

                    @Override
                    public boolean execute(FormItem item, Object value, DynamicForm form) {
                        return !isRoleLectorSelectedInEditionForm();
                    }
                });
                ViewTextItem sendEmailView = new ViewTextItem(ACCESS_SEND_EMAIL_VIEW, getConstants().sendEmail());
                sendEmailView.setValue(MetamacWebCommon.getConstants().no());
                sendEmailView.setShowIfCondition(new FormItemIfFunction() {

                    @Override
                    public boolean execute(FormItem item, Object value, DynamicForm form) {
                        return isRoleLectorSelectedInEditionForm();
                    }
                });

                RequiredSelectItem role = new RequiredSelectItem(ACCESS_ROLE, getConstants().role());
                role.addChangedHandler(new ChangedHandler() {

                    @Override
                    public void onChanged(ChangedEvent event) {
                        editionAccessForm.markForRedraw();
                    }
                });

                final SearchApplicationItem applicationItem = new SearchApplicationItem(ACCESS_APP, getConstants().app());
                applicationItem.setTitleStyle("staticFormItemTitle");
                // Set required with a custom validator
                applicationItem.setValidators(new CustomValidator() {

                    @Override
                    protected boolean condition(Object value) {
                        return !applicationItem.getSelectedAppDtos().isEmpty();
                    }
                });

                SearchMultiExternalItemSimpleItem operationsItem = new SearchMultiExternalItemSimpleItem(ACCESS_OPERATION, getConstants().statisticalOperation(),
                        CommonWebConstants.FORM_LIST_MAX_RESULTS) {

                    @Override
                    protected void retrieveResources(int firstResult, int maxResults, MetamacWebCriteria webCriteria) {
                        getUiHandlers().retrievePaginatedOperations(firstResult, maxResults, webCriteria.getCriteria());
                    }
                };

                editionAccessForm.setFields(role, sendEmail, sendEmailView, applicationItem, operationsItem);
                accessMainFormLayout.addEditionCanvas(editionAccessForm);
            }

            public void setUserAccesses(List<AccessDto> accessDtos) {
                AccessRecord[] records = new AccessRecord[accessDtos.size()];
                for (int i = 0; i < accessDtos.size(); i++) {
                    records[i] = RecordUtils.getAccessRecord(accessDtos.get(i));
                }
                accessListGrid.setData(records);
                show();
                accessMainFormLayout.hide();
            }

            private List<AccessDto> getSelectedAccess() {
                List<AccessDto> selectedAccess = new ArrayList<AccessDto>();
                ListGridRecord[] records = accessListGrid.getSelectedRecords();
                for (int i = 0; i < records.length; i++) {
                    AccessRecord record = (AccessRecord) records[i];
                    selectedAccess.add(record.getAccessDto());
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
                    ((SearchApplicationItem) editionAccessForm.getItem(ACCESS_APP)).clearRelatedResourceList();
                    ((SearchMultiExternalItemSimpleItem) editionAccessForm.getItem(ACCESS_OPERATION)).clearRelatedResourceList();

                    scrollPanelToBottom();
                } else {
                    showAccessDeleteButton();
                    accessMainFormLayout.hide();
                }
                setAccessEditionMode(accessDto);
            }

            private void setAccessEditionMode(AccessDto accessDto) {
                editionAccessForm.setValue(ACCESS_ROLE, accessDto.getRole() != null ? accessDto.getRole().getId().toString() : null);
                editionAccessForm.setValue(ACCESS_SEND_EMAIL, BooleanWebUtils.getBooleanValue(accessDto.getSendEmail()));
            }

            private void deselectAccess() {
                accessToolStrip.getDeleteButton().hide();
            }

            private void showAccessDeleteButton() {
                if (ClientSecurityUtils.canDeleteAccess()) {
                    accessToolStrip.getDeleteButton().show();
                }
            }

            private boolean isRoleLectorSelectedInEditionForm() {
                if (StringUtils.isBlank(editionAccessForm.getValueAsString(ACCESS_ROLE))) {
                    return false;
                } else {
                    RoleDto roleDto = getRoleDtoById(editionAccessForm.getValueAsString(ACCESS_ROLE));
                    return AccessControlRoleEnum.LECTOR.toString().equals(roleDto.getCode());
                }
            }

            private boolean isRoleLector(RoleDto roleDto) {
                return AccessControlRoleEnum.LECTOR.toString().equals(roleDto.getCode());
            }
        }
    }
}
