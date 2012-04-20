package org.siemac.metamac.access.control.web.client.view;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getConstants;

import java.util.List;

import org.siemac.metamac.access.control.web.client.model.ds.AccessDS;
import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;
import org.siemac.metamac.access.control.web.client.presenter.RoleHistoryPresenter;
import org.siemac.metamac.access.control.web.client.utils.RecordUtils;
import org.siemac.metamac.access.control.web.client.view.handlers.RoleHistoryUiHandlers;
import org.siemac.metamac.domain.access.control.dto.AccessDto;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridFieldIfFunction;
import com.smartgwt.client.widgets.layout.VLayout;

public class RoleHistoryViewImpl extends ViewWithUiHandlers<RoleHistoryUiHandlers> implements RoleHistoryPresenter.RoleHistoryView {

    private VLayout  panel;
    private Label    title;

    private ListGrid accessListGrid;

    public RoleHistoryViewImpl() {
        super();
        panel = new VLayout();
        panel.setOverflow(Overflow.SCROLL);

        title = new Label(getConstants().roleHistory());
        title.setStyleName("sectionTitle");
        title.setMargin(15);
        title.setHeight(30);

        accessListGrid = new ListGrid();
        accessListGrid.setLeaveScrollbarGap(false);
        ListGridField accessIdField = new ListGridField(AccessRecord.ID, getConstants().identifier());
        accessIdField.setShowIfCondition(new ListGridFieldIfFunction() {

            @Override
            public boolean execute(ListGrid grid, ListGridField field, int fieldNum) {
                return false;
            }
        });
        ListGridField userField = new ListGridField(AccessRecord.USER, getConstants().user());
        ListGridField roleField = new ListGridField(AccessRecord.ROLE, getConstants().role());
        ListGridField appField = new ListGridField(AccessRecord.APP, getConstants().app());
        ListGridField opField = new ListGridField(AccessRecord.OPERATION, getConstants().statisticalOperation());
        ListGridField dateField = new ListGridField(AccessRecord.REMOVAL_DATE, getConstants().removalDate());

        accessListGrid.setDataSource(new AccessDS());
        accessListGrid.setUseAllDataSourceFields(false);
        accessListGrid.setFields(accessIdField, userField, roleField, appField, opField, dateField);
        accessListGrid.setShowGroupSummary(true);
        accessListGrid.setGroupStartOpen(GroupStartOpen.ALL);
        accessListGrid.setGroupByField(AccessRecord.USER);

        panel.addMember(title);
        panel.addMember(accessListGrid);

    }

    @Override
    public Widget asWidget() {
        return panel;
    }

    @Override
    public void setRemovedAccess(List<AccessDto> accessDtos) {
        AccessRecord[] records = new AccessRecord[accessDtos.size()];
        for (int i = 0; i < accessDtos.size(); i++) {
            records[i] = RecordUtils.getAccessRecord(accessDtos.get(i));
        }
        accessListGrid.setData(records);
    }

}
