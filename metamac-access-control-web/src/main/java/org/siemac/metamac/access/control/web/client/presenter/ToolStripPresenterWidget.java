package org.siemac.metamac.access.control.web.client.presenter;

import org.siemac.metamac.access.control.web.client.NameTokens;

import com.google.web.bindery.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.HasClickHandlers;

public class ToolStripPresenterWidget extends PresenterWidget<ToolStripPresenterWidget.ToolStripView> {

    private final PlaceManager placeManager;

    public interface ToolStripView extends View {

        HasClickHandlers getUsersButton();
        HasClickHandlers getRoleHistoryButton();
    }

    @Inject
    public ToolStripPresenterWidget(EventBus eventBus, ToolStripView toolStripView, PlaceManager placeManager) {
        super(eventBus, toolStripView);
        this.placeManager = placeManager;
    }

    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getView().getUsersButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                placeManager.revealPlace(new PlaceRequest(NameTokens.usersListPage));
            }
        }));

        registerHandler(getView().getRoleHistoryButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                placeManager.revealPlace(new PlaceRequest(NameTokens.roleHistoryPage));
            }
        }));
    }

    @Override
    protected void onReveal() {
        super.onReveal();
    }

    @Override
    protected void onHide() {
        super.onHide();
    }

    @Override
    protected void onUnbind() {
        super.onUnbind();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

}
