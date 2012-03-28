package org.siemac.metamac.access.control.web.client.view;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getConstants;

import java.util.List;

import org.siemac.metamac.access.control.web.client.presenter.MainPagePresenter;
import org.siemac.metamac.access.control.web.client.view.handlers.MainPageUiHandlers;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.widgets.ErrorMessagePanel;
import org.siemac.metamac.web.common.client.widgets.MasterHead;
import org.siemac.metamac.web.common.client.widgets.SuccessMessagePanel;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.HasClickHandlers;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class MainPageViewImpl extends ViewWithUiHandlers<MainPageUiHandlers> implements MainPagePresenter.MainPageView {

    private static final int          NORTH_HEIGHT   = 85;
    private static final String       DEFAULT_MARGIN = "0px";

    private final MasterHead          masterHead;

    private final SuccessMessagePanel successMessagePanel;
    private final ErrorMessagePanel   errorMessagePanel;

    private VLayout                   panel;
    private VLayout                   northLayout;
    private HLayout                   headerLayout;
    private HLayout                   southLayout;
    private HLayout                   footerLayout;

    private ToolStripButton           usersButton;
    private ToolStripButton           roleHistoryButton;

    @Inject
    public MainPageViewImpl(MasterHead masterHead, SuccessMessagePanel successMessagePanel, ErrorMessagePanel errorMessagePanel) {
        this.masterHead = masterHead;
        this.successMessagePanel = successMessagePanel;
        this.errorMessagePanel = errorMessagePanel;
        // get rid of scroll bars, and clear out the window's built-in margin,
        // because we want to take advantage of the entire client area
        Window.enableScrolling(false);
        Window.setMargin(DEFAULT_MARGIN);

        // Initialize the main layout container
        panel = new VLayout();
        panel.setWidth100();
        panel.setHeight100();
        panel.setAlign(Alignment.CENTER);
        panel.setCanDrag(false);

        // Initialize the North layout container
        headerLayout = new HLayout();
        headerLayout.setHeight(NORTH_HEIGHT);

        // Nested layout container
        VLayout vLayout = new VLayout();
        vLayout.addMember(this.masterHead);

        // Nested layout container to the North layout container
        headerLayout.addMember(vLayout);
        headerLayout.setHeight(80);

        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth100();
        toolStrip.setAlign(Alignment.LEFT);

        usersButton = new ToolStripButton(getConstants().users());
        usersButton.setAutoFit(true);
        toolStrip.addButton(usersButton);
        roleHistoryButton = new ToolStripButton(getConstants().roleHistory());
        roleHistoryButton.setAutoFit(true);
        toolStrip.addButton(roleHistoryButton);

        northLayout = new VLayout();
        northLayout.addMember(headerLayout);
        northLayout.addMember(toolStrip);

        // Initialize the South layout container
        southLayout = new HLayout();
        southLayout.setHeight100();

        footerLayout = new HLayout();
        footerLayout.setBorder("1px solid #A7ABB4");
        footerLayout.addMember(this.successMessagePanel);
        footerLayout.addMember(this.errorMessagePanel);

        // Add the North and South layout containers to the main layout
        // container
        panel.addMember(northLayout);
        panel.addMember(southLayout);
        panel.addMember(footerLayout);

    }

    @Override
    public Widget asWidget() {
        return panel;
    }

    /****************************************************
     * Code for nested presenters.
     ***************************************************/

    /*
     * GWTP will call setInSlot when a child presenter asks to be added under this view
     */
    @Override
    public void setInSlot(Object slot, Widget content) {
        if (slot == MainPagePresenter.TYPE_SetContextAreaContent) {
            if (content != null) {
                southLayout.setMembers((VLayout) content);
            }
        } else {
            // To support inheritance in your views it is good practice to call super.setInSlot when you can't handle the call.
            // Who knows, maybe the parent class knows what to do with this slot.
            super.setInSlot(slot, content);
        }
    }

    @Override
    public void removeFromSlot(Object slot, Widget content) {
        super.removeFromSlot(slot, content);
    }

    /****************************************************
     * End code for nested presenters.
     ***************************************************/

    @Override
    public void showMessage(List<String> messages, MessageTypeEnum type) {
        // Hide messages before showing the new ones
        hideMessages();
        if (MessageTypeEnum.SUCCESS.equals(type)) {
            successMessagePanel.showMessage(messages);
            Timer timer = new Timer() {

                @Override
                public void run() {
                    successMessagePanel.animateHide(AnimationEffect.FADE);
                }
            };
            timer.schedule(6000);
        } else if (MessageTypeEnum.ERROR.equals(type)) {
            errorMessagePanel.showMessage(messages);
        }
    }

    @Override
    public void hideMessages() {
        successMessagePanel.hide();
        errorMessagePanel.hide();
    }

    @Override
    public void setTitle(String title) {
        masterHead.setTitleLabel(title);
    }

    @Override
    public HasClickHandlers goToUsersListPage() {
        return usersButton;
    }

    @Override
    public HasClickHandlers goToRoleHistoryPage() {
        return roleHistoryButton;
    }

}
