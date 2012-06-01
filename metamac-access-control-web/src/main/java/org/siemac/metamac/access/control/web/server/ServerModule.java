package org.siemac.metamac.access.control.web.server;

import org.siemac.metamac.access.control.web.server.handlers.DeleteAccessListActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.DeleteAppListActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.DeleteRoleListActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.DeleteUserListActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAccessByUserActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllAccessActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllAppsActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllRemovedAccessActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllRolesActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllStatisticalOperationsActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllUsersActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.SaveAccessActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.SaveAccessListActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.SaveAppActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.SaveRoleActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.SaveUserActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.ValidateTicketActionHandler;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListAction;
import org.siemac.metamac.access.control.web.shared.DeleteAppListAction;
import org.siemac.metamac.access.control.web.shared.DeleteRoleListAction;
import org.siemac.metamac.access.control.web.shared.DeleteUserListAction;
import org.siemac.metamac.access.control.web.shared.FindAccessByUserAction;
import org.siemac.metamac.access.control.web.shared.FindAllAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllAppsAction;
import org.siemac.metamac.access.control.web.shared.FindAllRemovedAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllRolesAction;
import org.siemac.metamac.access.control.web.shared.FindAllStatisticalOperationsAction;
import org.siemac.metamac.access.control.web.shared.FindAllUsersAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessListAction;
import org.siemac.metamac.access.control.web.shared.SaveAppAction;
import org.siemac.metamac.access.control.web.shared.SaveRoleAction;
import org.siemac.metamac.access.control.web.shared.SaveUserAction;
import org.siemac.metamac.web.common.server.handlers.CloseSessionActionHandler;
import org.siemac.metamac.web.common.server.handlers.GetLoginPageUrlActionHandler;
import org.siemac.metamac.web.common.server.handlers.MockCASUserActionHandler;
import org.siemac.metamac.web.common.shared.CloseSessionAction;
import org.siemac.metamac.web.common.shared.GetLoginPageUrlAction;
import org.siemac.metamac.web.common.shared.MockCASUserAction;
import org.siemac.metamac.web.common.shared.ValidateTicketAction;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.spring.HandlerModule;

/**
 * Module which binds the handlers and configurations.
 */
@Component
public class ServerModule extends HandlerModule {

    public ServerModule() {
    }

    protected void configureHandlers() {
        bindHandler(FindAllAccessAction.class, FindAllAccessActionHandler.class);
        bindHandler(FindAllAppsAction.class, FindAllAppsActionHandler.class);
        bindHandler(FindAllRolesAction.class, FindAllRolesActionHandler.class);
        bindHandler(FindAllUsersAction.class, FindAllUsersActionHandler.class);
        bindHandler(DeleteAccessListAction.class, DeleteAccessListActionHandler.class);
        bindHandler(DeleteUserListAction.class, DeleteUserListActionHandler.class);
        bindHandler(DeleteRoleListAction.class, DeleteRoleListActionHandler.class);
        bindHandler(DeleteAppListAction.class, DeleteAppListActionHandler.class);
        bindHandler(SaveAccessAction.class, SaveAccessActionHandler.class);
        bindHandler(SaveUserAction.class, SaveUserActionHandler.class);
        bindHandler(SaveRoleAction.class, SaveRoleActionHandler.class);
        bindHandler(SaveAppAction.class, SaveAppActionHandler.class);
        bindHandler(FindAccessByUserAction.class, FindAccessByUserActionHandler.class);
        bindHandler(FindAllStatisticalOperationsAction.class, FindAllStatisticalOperationsActionHandler.class);
        bindHandler(FindAllRemovedAccessAction.class, FindAllRemovedAccessActionHandler.class);
        bindHandler(SaveAccessListAction.class, SaveAccessListActionHandler.class);
        bindHandler(ValidateTicketAction.class, ValidateTicketActionHandler.class);
        bindHandler(GetLoginPageUrlAction.class, GetLoginPageUrlActionHandler.class);
        bindHandler(CloseSessionAction.class, CloseSessionActionHandler.class);

        // This action should be removed to use CAS authentication
        bindHandler(MockCASUserAction.class, MockCASUserActionHandler.class);
    }

}
