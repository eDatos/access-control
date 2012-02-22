package org.siemac.metamac.access.control.web.server;

import org.siemac.metamac.access.control.web.server.handlers.DeleteAccessListActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.DeleteAppListActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.DeleteRoleListActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.DeleteUserListActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAccessByUserActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllAccessActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllAppsActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllRolesActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllUsersActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.SaveAccessActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.SaveAppActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.SaveRoleActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.SaveUserActionHandler;
import org.siemac.metamac.access.control.web.shared.DeleteAccessListAction;
import org.siemac.metamac.access.control.web.shared.DeleteAppListAction;
import org.siemac.metamac.access.control.web.shared.DeleteRoleListAction;
import org.siemac.metamac.access.control.web.shared.DeleteUserListAction;
import org.siemac.metamac.access.control.web.shared.FindAccessByUserAction;
import org.siemac.metamac.access.control.web.shared.FindAllAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllAppsAction;
import org.siemac.metamac.access.control.web.shared.FindAllRolesAction;
import org.siemac.metamac.access.control.web.shared.FindAllUsersAction;
import org.siemac.metamac.access.control.web.shared.SaveAccessAction;
import org.siemac.metamac.access.control.web.shared.SaveAppAction;
import org.siemac.metamac.access.control.web.shared.SaveRoleAction;
import org.siemac.metamac.access.control.web.shared.SaveUserAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.gwtplatform.dispatch.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.server.spring.HandlerModule;
import com.gwtplatform.dispatch.server.spring.actionvalidator.DefaultActionValidator;
import com.gwtplatform.dispatch.server.spring.configuration.DefaultModule;

/**
 * Module which binds the handlers and configurations.
 */
@Configuration
@Import(DefaultModule.class)
public class ServerModule extends HandlerModule {

    public ServerModule() {
    }

    @Bean
    public ActionValidator getDefaultActionValidator() {
        return new DefaultActionValidator();
    }

    @Bean
    public FindAllAccessActionHandler getFindAllAccessActionHandler() {
        return new FindAllAccessActionHandler();
    }

    @Bean
    public FindAllAppsActionHandler getFindAllAppsActionHandler() {
        return new FindAllAppsActionHandler();
    }

    @Bean
    public FindAllRolesActionHandler getFindAllRolesActionHandler() {
        return new FindAllRolesActionHandler();
    }

    @Bean
    public FindAllUsersActionHandler getFindAllUsersActionHandler() {
        return new FindAllUsersActionHandler();
    }

    @Bean
    public DeleteAccessListActionHandler getDeleteAccessListActionHandler() {
        return new DeleteAccessListActionHandler();
    }

    @Bean
    public DeleteUserListActionHandler getDeleteUserListActionHandler() {
        return new DeleteUserListActionHandler();
    }

    @Bean
    public DeleteRoleListActionHandler getDeleteRoleListActionHandler() {
        return new DeleteRoleListActionHandler();
    }

    @Bean
    public DeleteAppListActionHandler getDeleteAppListActionHandler() {
        return new DeleteAppListActionHandler();
    }

    @Bean
    public SaveAccessActionHandler getSaveAccessActionHandler() {
        return new SaveAccessActionHandler();
    }

    @Bean
    public SaveUserActionHandler getSaveUsersActionHandler() {
        return new SaveUserActionHandler();
    }

    @Bean
    public SaveRoleActionHandler getSaveRolesActionHandler() {
        return new SaveRoleActionHandler();
    }

    @Bean
    public SaveAppActionHandler getSaveAppsActionHandler() {
        return new SaveAppActionHandler();
    }
    
    @Bean
    public FindAccessByUserActionHandler getFindAccessByUserActionHandler() {
        return new FindAccessByUserActionHandler();
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
    }

}
