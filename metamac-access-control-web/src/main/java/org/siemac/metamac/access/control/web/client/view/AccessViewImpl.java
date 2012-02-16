package org.siemac.metamac.access.control.web.client.view;

import static org.siemac.metamac.access.control.web.client.AccessControlWeb.getConstants;

import java.util.List;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.web.client.model.ds.AccessDS;
import org.siemac.metamac.access.control.web.client.model.record.AccessRecord;
import org.siemac.metamac.access.control.web.client.presenter.AccessPresenter;
import org.siemac.metamac.access.control.web.client.utils.RecordUtils;
import org.siemac.metamac.access.control.web.client.view.handlers.AccessUiHandlers;
import org.siemac.metamac.web.common.client.widgets.CustomListGrid;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;


public class AccessViewImpl extends ViewWithUiHandlers<AccessUiHandlers> implements AccessPresenter.AccessView {

    private VLayout panel;
    private CustomListGrid listGrid;
    
    
    public AccessViewImpl() {
        super();
        panel = new VLayout();
        
        listGrid = new CustomListGrid();
        ListGridField userField = new ListGridField(AccessRecord.USER, getConstants().user());
        ListGridField roleField = new ListGridField(AccessRecord.ROLE, getConstants().role());
        ListGridField appField = new ListGridField(AccessRecord.APP, getConstants().app());
        ListGridField opField = new ListGridField(AccessRecord.OPERATION, getConstants().statisticalOperation());
        listGrid.setFields(userField, roleField, appField, opField);
        
        AccessDS accessDS = new AccessDS();
        listGrid.setDataSource(accessDS);
        
        panel.addMember(listGrid);
        
    }
    
    @Override
    public Widget asWidget() {
        return panel;
    }

    @Override
    public void setAccessList(List<AccessDto> accessDtos) {
        listGrid.removeAllData();
        for (AccessDto accessDto : accessDtos) {
            AccessRecord record = RecordUtils.getAccessRecord(accessDto);
            listGrid.addData(record);
        }
    }
    
}
