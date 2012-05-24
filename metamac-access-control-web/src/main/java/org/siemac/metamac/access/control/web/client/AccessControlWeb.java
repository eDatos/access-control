package org.siemac.metamac.access.control.web.client;

import org.siemac.metamac.access.control.web.client.gin.AccessControlWebGinjector;
import org.siemac.metamac.access.control.web.shared.GetLoginPageUrlAction;
import org.siemac.metamac.access.control.web.shared.GetLoginPageUrlResult;
import org.siemac.metamac.access.control.web.shared.ValidateTicketAction;
import org.siemac.metamac.access.control.web.shared.ValidateTicketResult;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.web.common.client.widgets.WaitingAsyncCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AccessControlWeb implements EntryPoint {

    private static MetamacPrincipal               principal;
    private static AccessControlWebConstants      constants;
    private static AccessControlWebMessages       messages;
    private static AccessControlWebCoreMessages   coreMessages;

    public static final AccessControlWebGinjector ginjector = GWT.create(AccessControlWebGinjector.class);

    interface GlobalResources extends ClientBundle {

        @NotStrict
        @Source("AccessControlWebStyles.css")
        CssResource css();
    }

    public void onModuleLoad() {
        String ticket = Window.Location.getParameter("ticket");
        if (ticket != null) {
            String url = Window.Location.createUrlBuilder().removeParameter("ticket").setHash(";ticket=" + ticket).buildString();
            Window.Location.replace(url);
            return;
        }

        ticket = Window.Location.getHash().replace("#;ticket=", "");
        if (ticket == null || ticket.length() == 0) {
            displayLoginView();
        } else {
            String serviceUrl = Window.Location.createUrlBuilder().buildString();
            ginjector.getDispatcher().execute(new ValidateTicketAction(ticket, serviceUrl), new WaitingAsyncCallback<ValidateTicketResult>() {

                @Override
                public void onWaitFailure(Throwable arg0) {
                    // TODO log
                }
                @Override
                public void onWaitSuccess(ValidateTicketResult result) {
                    AccessControlWeb.principal = result.getMetamacPrincipal();

                    String url = Window.Location.createUrlBuilder().setHash("").buildString();
                    Window.Location.assign(url);

                    // This is required for GWT-Platform proxy's generator.
                    DelayedBindRegistry.bind(ginjector);
                    ginjector.getPlaceManager().revealCurrentPlace();

                    // Inject global styles
                    GWT.<GlobalResources> create(GlobalResources.class).css().ensureInjected();
                }
            });
        }
    }

    public void displayLoginView() {
        String serviceUrl = Window.Location.createUrlBuilder().buildString();
        ginjector.getDispatcher().execute(new GetLoginPageUrlAction(serviceUrl), new WaitingAsyncCallback<GetLoginPageUrlResult>() {

            @Override
            public void onWaitFailure(Throwable caught) {

            }
            @Override
            public void onWaitSuccess(GetLoginPageUrlResult result) {
                Window.Location.replace(result.getLoginPageUrl());
            }
        });
    }

    public static MetamacPrincipal getCurrentUser() {
        return AccessControlWeb.principal;
    }

    public static AccessControlWebConstants getConstants() {
        if (constants == null) {
            constants = (AccessControlWebConstants) GWT.create(AccessControlWebConstants.class);
        }
        return constants;
    }

    public static AccessControlWebMessages getMessages() {
        if (messages == null) {
            messages = (AccessControlWebMessages) GWT.create(AccessControlWebMessages.class);
        }
        return messages;
    }

    public static AccessControlWebCoreMessages getCoreMessages() {
        if (coreMessages == null) {
            coreMessages = (AccessControlWebCoreMessages) GWT.create(AccessControlWebCoreMessages.class);
        }
        return coreMessages;
    }

    public static AccessControlWebGinjector getAccessControlWebGinjector() {
        return ginjector;
    }

}