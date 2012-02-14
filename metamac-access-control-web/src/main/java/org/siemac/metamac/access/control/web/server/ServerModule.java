package org.siemac.metamac.access.control.web.server;

import org.siemac.metamac.access.control.web.server.handlers.FindAllAccessActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllAppsActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllRolesActionHandler;
import org.siemac.metamac.access.control.web.server.handlers.FindAllUsersActionHandler;
import org.siemac.metamac.access.control.web.shared.FindAllAccessAction;
import org.siemac.metamac.access.control.web.shared.FindAllAppsAction;
import org.siemac.metamac.access.control.web.shared.FindAllRolesAction;
import org.siemac.metamac.access.control.web.shared.FindAllUsersAction;
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
	
	protected void configureHandlers() {
	    bindHandler(FindAllAccessAction.class, FindAllAccessActionHandler.class);
	    bindHandler(FindAllAppsAction.class, FindAllAppsActionHandler.class);
	    bindHandler(FindAllRolesAction.class, FindAllRolesActionHandler.class);
	    bindHandler(FindAllUsersAction.class, FindAllUsersActionHandler.class);
	}
	
}
