package org.siemac.metamac.access.control.web.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.access.control.web.client.gin.AccessControlWebGinjector;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.web.common.client.MetamacSecurityEntryPoint;
import org.siemac.metamac.web.common.client.events.LoginAuthenticatedEvent;
import org.siemac.metamac.web.common.client.gin.MetamacWebGinjector;
import org.siemac.metamac.web.common.client.utils.ApplicationEditionLanguages;
import org.siemac.metamac.web.common.client.utils.ApplicationOrganisation;
import org.siemac.metamac.web.common.client.widgets.WaitingAsyncCallback;
import org.siemac.metamac.web.common.shared.GetLoginPageUrlAction;
import org.siemac.metamac.web.common.shared.GetLoginPageUrlResult;
import org.siemac.metamac.web.common.shared.LoadConfigurationPropertiesAction;
import org.siemac.metamac.web.common.shared.LoadConfigurationPropertiesResult;
import org.siemac.metamac.web.common.shared.MockCASUserAction;
import org.siemac.metamac.web.common.shared.MockCASUserResult;
import org.siemac.metamac.web.common.shared.ValidateTicketAction;
import org.siemac.metamac.web.common.shared.ValidateTicketResult;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AccessControlWeb extends MetamacSecurityEntryPoint {

    private static final Boolean                  SECURITY_ENABLED = true;

    private static Logger                         logger           = Logger.getLogger(AccessControlWeb.class.getName());

    private static MetamacPrincipal               principal;
    private static AccessControlWebConstants      constants;
    private static AccessControlWebMessages       messages;

    public static final AccessControlWebGinjector ginjector        = GWT.create(AccessControlWebGinjector.class);

    interface GlobalResources extends ClientBundle {

        @NotStrict
        @Source("AccessControlWebStyles.css")
        CssResource css();
    }

    @Override
    public void onModuleLoad() {
        setUncaughtExceptionHandler();
 
        prepareApplication(SECURITY_ENABLED);
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

    public static AccessControlWebGinjector getAccessControlWebGinjector() {
        return ginjector;
    }
    
    @Override
    protected String getApplicationTitle() {
        return getConstants().appTitle();
    }
    
    @Override
    protected MetamacPrincipal getPrincipal() {
        return principal;
    }
    
    @Override
    protected void setPrincipal(MetamacPrincipal principal) {
        this.principal = principal;
    }
    
    @Override
    protected String getSecurityApplicationId() {
        return AccessControlConstants.SECURITY_APPLICATION_ID;
    }
    
    @Override
    protected MetamacWebGinjector getWebGinjector() {
        return getAccessControlWebGinjector();
    }
    
    
    
}