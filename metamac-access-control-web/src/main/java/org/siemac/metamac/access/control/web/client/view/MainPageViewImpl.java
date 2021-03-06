package org.siemac.metamac.access.control.web.client.view;

import org.siemac.metamac.access.control.web.client.AccessControlWeb;
import org.siemac.metamac.access.control.web.client.presenter.MainPagePresenter;
import org.siemac.metamac.access.control.web.client.view.handlers.MainPageUiHandlers;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.web.common.client.enums.MessageTypeEnum;
import org.siemac.metamac.web.common.client.widgets.FooterLayout;
import org.siemac.metamac.web.common.client.widgets.MasterHead;
import org.siemac.metamac.web.common.client.widgets.MessagePanel;
import org.siemac.metamac.web.common.client.widgets.MetamacNavBar;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainPageViewImpl extends ViewWithUiHandlers<MainPageUiHandlers> implements MainPagePresenter.MainPageView {

    private static final int    NORTH_HEIGHT   = 85;
    private static final String DEFAULT_MARGIN = "0px";

    private MainPageUiHandlers  uiHandlers;

    private final MasterHead    masterHead;

    private final MessagePanel  messagePanel;

    private final VLayout       panel;
    private final VLayout       northLayout;
    private final HLayout       southLayout;
    private final FooterLayout  footerLayout;

    @Inject
    public MainPageViewImpl(MasterHead masterHead, MessagePanel messagePanel) {
        this.masterHead = masterHead;
        this.messagePanel = messagePanel;

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
        northLayout = new VLayout();
        northLayout.setHeight(NORTH_HEIGHT);

        // Nested layout container to the North layout container
        northLayout.addMember(this.masterHead);
        northLayout.setHeight(65);

        // Initialize the South layout container
        southLayout = new HLayout();
        southLayout.setHeight100();

        footerLayout = new FooterLayout(this.messagePanel, AccessControlWeb.getProjectVersion());

        // Set user name
        masterHead.getUserNameLabel().setContents(getUserName());

        // Add handlers to logout button
        masterHead.getLogoutLink().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                uiHandlers.closeSession();
            }
        });

        // Help section
        masterHead.getHelpLink().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                uiHandlers.openHelpUrl();
            }
        });

        MetamacNavBar navBar = new MetamacNavBar();
        northLayout.setZIndex(0);
        southLayout.setZIndex(0);
        footerLayout.setZIndex(0);
        // Add the North and South layout containers to the main layout
        // container
        panel.addMember(navBar);
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
    public void showMessage(Throwable throwable, String message, MessageTypeEnum type) {
        // Hide messages before showing the new ones
        messagePanel.showMessage(throwable, message, type);
    }

    @Override
    public void hideMessages() {
        messagePanel.hide();
    }

    @Override
    public void setTitle(String title) {
        masterHead.setTitleLabel(title);
    }

    @Override
    public void setUiHandlers(MainPageUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    private String getUserName() {
        MetamacPrincipal metamacPrincipal = AccessControlWeb.getCurrentUser();
        if (metamacPrincipal != null) {
            return metamacPrincipal.getUserId();
        }
        return new String();
    }
}
