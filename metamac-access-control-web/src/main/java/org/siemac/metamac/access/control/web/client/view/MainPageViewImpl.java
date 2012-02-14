package org.siemac.metamac.access.control.web.client.view;

import org.siemac.metamac.access.control.web.client.presenter.MainPagePresenter;
import org.siemac.metamac.access.control.web.client.view.handlers.MainPageUiHandlers;
import org.siemac.metamac.web.common.client.widgets.ErrorMessagePanel;
import org.siemac.metamac.web.common.client.widgets.SuccessMessagePanel;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainPageViewImpl extends ViewWithUiHandlers<MainPageUiHandlers> implements MainPagePresenter.MainPageView {

	private static final int NORTH_HEIGHT = 85;
	private static final String DEFAULT_MARGIN = "0px";

	private final SuccessMessagePanel successMessagePanel = null;
	private final ErrorMessagePanel errorMessagePanel = null;
	
	private VLayout panel;
	private HLayout northLayout;
	private HLayout southLayout;
	private HLayout footerLayout;
	

	@Inject
	public MainPageViewImpl() {
	}

	@Override
	public Widget asWidget() {
		return null;
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


}
