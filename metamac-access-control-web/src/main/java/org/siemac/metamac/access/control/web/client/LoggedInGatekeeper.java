package org.siemac.metamac.access.control.web.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.access.control.core.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.sso.client.MetamacPrincipalAccess;
import org.siemac.metamac.web.common.client.events.LoginAuthenticatedEvent;
import org.siemac.metamac.web.common.client.events.LoginAuthenticatedEvent.LoginAuthenticatedEventHandler;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

public class LoggedInGatekeeper implements Gatekeeper {

    private static Logger logger = Logger.getLogger(LoggedInGatekeeper.class.getName());

    @Inject
    public LoggedInGatekeeper(EventBus eventBus) {
        eventBus.addHandler(LoginAuthenticatedEvent.getType(), new LoginAuthenticatedEventHandler() {

            @Override
            public void onLogin(LoginAuthenticatedEvent event) {
                logger.log(Level.INFO, event.getMetamacPrincipal() + " " + " credentials have been authenticated.");
            }
        });
    }

    @Override
    public boolean canReveal() {
        return hasAnyAllowedRole(AccessControlWeb.getCurrentUser());
    }

    private boolean hasAnyAllowedRole(MetamacPrincipal metamacPrincipal) {
        for (MetamacPrincipalAccess access : metamacPrincipal.getAccesses()) {
            if (AccessControlConstants.APPLICATION_ID.equals(access.getApplication()) && isRoleAllowed(access.getRole())) {
                return true;
            }
        }
        return false;
    }

    private boolean isRoleAllowed(String role) {
        for (AccessControlRoleEnum roleEnum : AccessControlRoleEnum.values()) {
            if (roleEnum.toString().equals(role)) {
                return true;
            }
        }
        return false;
    }

}
