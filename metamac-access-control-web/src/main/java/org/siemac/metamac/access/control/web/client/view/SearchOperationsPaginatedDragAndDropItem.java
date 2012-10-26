package org.siemac.metamac.access.control.web.client.view;

import org.siemac.metamac.web.common.client.widgets.actions.PaginatedAction;
import org.siemac.metamac.web.common.client.widgets.form.fields.SearchExternalPaginatedDragAndDropItem;

public class SearchOperationsPaginatedDragAndDropItem extends SearchExternalPaginatedDragAndDropItem {

    public SearchOperationsPaginatedDragAndDropItem(String name, String title, int maxResults, int formItemWidth, PaginatedAction action) {
        super(name, title, maxResults, formItemWidth, action);
        targetList.setHeight(400);
    }

}
