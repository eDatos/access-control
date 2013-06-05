package org.siemac.metamac.access.control.web.client.widgets;

import java.util.List;

import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.web.common.client.widgets.BaseNavigableListGrid;

import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class NavigableListGrid extends BaseNavigableListGrid<ExternalItemDto> {

    @Override
    protected List<PlaceRequest> buildLocation(ExternalItemDto relatedResourceDto) {
        throw new UnsupportedOperationException(); // there is no related resources in this application
    }
}
