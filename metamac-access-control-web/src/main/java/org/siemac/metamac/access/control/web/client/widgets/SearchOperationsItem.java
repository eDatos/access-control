package org.siemac.metamac.access.control.web.client.widgets;

import java.util.List;

import org.siemac.metamac.access.control.web.client.AccessControlWeb;
import org.siemac.metamac.access.control.web.client.view.handlers.UsersListUiHandlers;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.web.common.client.MetamacWebCommon;
import org.siemac.metamac.web.common.client.utils.RecordUtils;
import org.siemac.metamac.web.common.client.widgets.BaseSearchWindow;
import org.siemac.metamac.web.common.client.widgets.actions.PaginatedAction;
import org.siemac.metamac.web.common.client.widgets.actions.SearchPaginatedAction;
import org.siemac.metamac.web.common.client.widgets.form.fields.SearchExternalPaginatedDragAndDropItem;
import org.siemac.metamac.web.common.client.widgets.form.fields.external.ExternalItemListItem;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;

public class SearchOperationsItem extends ExternalItemListItem {

    protected SearchOperationsWindow searchOperationsWindow;

    protected UsersListUiHandlers    uiHandlers;

    public SearchOperationsItem(final String name, String title) {
        super(name, title, true);

        getSearchIcon().addFormItemClickHandler(new FormItemClickHandler() {

            @Override
            public void onFormItemClick(FormItemIconClickEvent event) {
                searchOperationsWindow = new SearchOperationsWindow(name);
                uiHandlers.retrievePaginatedOperations(0, SearchOperationsPaginatedDragAndDropItem.MAX_RESULTS, null);
                searchOperationsWindow.setTargetOperations(getSelectedRelatedResources());
                searchOperationsWindow.getSaveButton().addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent event) {
                        List<ExternalItemDto> selectedOperations = searchOperationsWindow.getSelectedOperations();
                        searchOperationsWindow.markForDestroy();
                        getListGrid().setData(RecordUtils.getExternalItemRecords(selectedOperations));
                    }
                });
            }
        });
    }

    public void setUiHandlers(UsersListUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    public void setOperations(List<ExternalItemDto> operations, int firstResult, int totalResults) {
        if (searchOperationsWindow != null) {
            searchOperationsWindow.setOperations(operations, firstResult, totalResults);
        }
    }

    private class SearchOperationsWindow extends BaseSearchWindow {

        protected SearchOperationsPaginatedDragAndDropItem searchOperationsPaginatedDragAndDropItem;
        protected ButtonItem                               saveItem;

        public SearchOperationsWindow(String name) {
            super(AccessControlWeb.getConstants().statisticalOperationSelection());

            searchOperationsPaginatedDragAndDropItem = new SearchOperationsPaginatedDragAndDropItem(name, "", String.valueOf(FORM_ITEM_CUSTOM_WIDTH/2), new PaginatedAction() {

                @Override
                public void retrieveResultSet(int firstResult, int maxResults) {
                    uiHandlers.retrievePaginatedOperations(firstResult, maxResults, null);
                }
            });
            searchOperationsPaginatedDragAndDropItem.setSearchAction(new SearchPaginatedAction() {

                @Override
                public void retrieveResultSet(int firstResult, int maxResults, String criteria) {
                    uiHandlers.retrievePaginatedOperations(firstResult, maxResults, criteria);
                }
            });
            searchOperationsPaginatedDragAndDropItem.setShowTitle(false);

            saveItem = new ButtonItem("save-button", MetamacWebCommon.getConstants().actionSave());
            saveItem.setAlign(Alignment.CENTER);

            getForm().setFields(searchOperationsPaginatedDragAndDropItem, saveItem);
        }

        public ButtonItem getSaveButton() {
            return saveItem;
        }

        public void setOperations(List<ExternalItemDto> operations, int firstResult, int totalResults) {
            searchOperationsPaginatedDragAndDropItem.setSourceExternalItems(operations);
            searchOperationsPaginatedDragAndDropItem.refreshSourcePaginationInfo(firstResult, operations.size(), totalResults);
        }

        public List<ExternalItemDto> getSelectedOperations() {
            return searchOperationsPaginatedDragAndDropItem.getSelectedExternalItems();
        }

        public void setTargetOperations(List<ExternalItemDto> externalItemDtos) {
            searchOperationsPaginatedDragAndDropItem.setTargetExternalItems(externalItemDtos);
        }
    }

    private class SearchOperationsPaginatedDragAndDropItem extends SearchExternalPaginatedDragAndDropItem {

        public final static int MAX_RESULTS = 8;

        public SearchOperationsPaginatedDragAndDropItem(String name, String title, String formItemWidth, PaginatedAction action) {
            super(name, title, MAX_RESULTS, formItemWidth, action);

            targetList.setHeight(50);
            targetList.setAutoFitData(Autofit.VERTICAL);
            targetList.setAutoFitMaxRecords(MAX_RESULTS);

            form.setColWidths("47%");
            form.markForRedraw();
        }
    }
}
