package org.siemac.metamac.access.control.web.client;

import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.access.control.web.client.gin.AccessControlWebGinjector;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.web.common.client.MetamacSecurityEntryPoint;
import org.siemac.metamac.web.common.client.gin.MetamacWebGinjector;

import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AccessControlWeb extends MetamacSecurityEntryPoint {

    private static final Boolean                  SECURITY_ENABLED = true;

    private static MetamacPrincipal               principal;
    private static AccessControlWebConstants      constants;
    private static AccessControlWebMessages       messages;

    public static final AccessControlWebGinjector ginjector        = GWT.create(AccessControlWebGinjector.class);

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
        AccessControlWeb.principal = principal;
    }

    @Override
    protected String getSecurityApplicationId() {
        return AccessControlConstants.SECURITY_APPLICATION_ID;
    }

    @Override
    protected MetamacWebGinjector getWebGinjector() {
        return getAccessControlWebGinjector();
    }

    @Override
    protected String getBundleName() {
        return "messages-access_control-web";
    }
}