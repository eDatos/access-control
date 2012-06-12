package org.siemac.metamac.access.control.web.client.view;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getConstants;

import org.siemac.metamac.access.control.web.client.presenter.ToolStripPresenterWidget;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.HasClickHandlers;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.toolbar.ToolStripSeparator;

public class ToolStripViewImpl implements ToolStripPresenterWidget.ToolStripView {

    private ToolStrip       toolStrip;

    private ToolStripButton usersButton;
    private ToolStripButton roleHistoryButton;

    @Inject
    public ToolStripViewImpl() {
        super();
        toolStrip = new ToolStrip();
        toolStrip.setWidth100();
        toolStrip.setAlign(Alignment.LEFT);

        usersButton = new ToolStripButton(getConstants().users());
        roleHistoryButton = new ToolStripButton(getConstants().roleHistory());

        // Add buttons to toolStrip
        toolStrip.addButton(usersButton);
        toolStrip.addMember(new ToolStripSeparator());
        toolStrip.addButton(roleHistoryButton);
    }

    @Override
    public void addToSlot(Object slot, Widget content) {
        System.out.println();
    }

    @Override
    public Widget asWidget() {
        return toolStrip;
    }

    @Override
    public void removeFromSlot(Object slot, Widget content) {

    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        System.out.println();
    }

    @Override
    public HasClickHandlers getUsersButton() {
        return usersButton;
    }

    @Override
    public HasClickHandlers getRoleHistoryButton() {
        return roleHistoryButton;
    }

}
