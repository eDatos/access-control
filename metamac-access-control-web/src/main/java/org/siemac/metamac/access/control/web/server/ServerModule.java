package org.siemac.metamac.gopestat.web.server;

import org.siemac.metamac.gopestat.web.server.handlers.DeleteFamilyListActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.DeleteInstanceListActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.DeleteOperationListActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.FindAllCategorySchemesActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.FindAllCodeListsActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.FindAllCommonMetadataActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.FindAllConceptSchemesActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.FindAllOrganisationSchemesActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetCategoriesFromSchemeActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetCodesFromCodeListActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetConceptsFromSchemeActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetFamilyActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetFamilyAndOperationsActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetFamilyListActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetFrequencyCodesActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetGopestatListsActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetInstanceActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetInstanceListActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetOperationAndInstancesActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetOperationListActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.GetOrganisationsFromSchemeActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.PublishExternallyFamilyActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.PublishExternallyInstanceActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.PublishExternallyOperationActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.PublishInternallyFamilyActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.PublishInternallyInstanceActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.PublishInternallyOperationActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.SaveFamilyActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.SaveInstanceActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.SaveOperationActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.UpdateFamilyOperationsActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.UpdateInstancesOrderActionHandler;
import org.siemac.metamac.gopestat.web.server.handlers.UpdateOperationFamiliesActionHandler;
import org.siemac.metamac.gopestat.web.shared.DeleteFamilyListAction;
import org.siemac.metamac.gopestat.web.shared.DeleteInstanceListAction;
import org.siemac.metamac.gopestat.web.shared.DeleteOperationListAction;
import org.siemac.metamac.gopestat.web.shared.FindAllCategorySchemesAction;
import org.siemac.metamac.gopestat.web.shared.FindAllCodeListsAction;
import org.siemac.metamac.gopestat.web.shared.FindAllCommonMetadataAction;
import org.siemac.metamac.gopestat.web.shared.FindAllConceptSchemesAction;
import org.siemac.metamac.gopestat.web.shared.FindAllOrganisationSchemesAction;
import org.siemac.metamac.gopestat.web.shared.GetCategoriesFromSchemeAction;
import org.siemac.metamac.gopestat.web.shared.GetCodesFromCodeListAction;
import org.siemac.metamac.gopestat.web.shared.GetConceptsFromSchemeAction;
import org.siemac.metamac.gopestat.web.shared.GetFamilyAction;
import org.siemac.metamac.gopestat.web.shared.GetFamilyAndOperationsAction;
import org.siemac.metamac.gopestat.web.shared.GetFamilyListAction;
import org.siemac.metamac.gopestat.web.shared.GetFrequencyCodesAction;
import org.siemac.metamac.gopestat.web.shared.GetGopestatListsAction;
import org.siemac.metamac.gopestat.web.shared.GetInstanceAction;
import org.siemac.metamac.gopestat.web.shared.GetInstanceListAction;
import org.siemac.metamac.gopestat.web.shared.GetOperationAndInstancesAction;
import org.siemac.metamac.gopestat.web.shared.GetOperationListAction;
import org.siemac.metamac.gopestat.web.shared.GetOrganisationsFromSchemeAction;
import org.siemac.metamac.gopestat.web.shared.PublishExternallyFamilyAction;
import org.siemac.metamac.gopestat.web.shared.PublishExternallyInstanceAction;
import org.siemac.metamac.gopestat.web.shared.PublishExternallyOperationAction;
import org.siemac.metamac.gopestat.web.shared.PublishInternallyFamilyAction;
import org.siemac.metamac.gopestat.web.shared.PublishInternallyInstanceAction;
import org.siemac.metamac.gopestat.web.shared.PublishInternallyOperationAction;
import org.siemac.metamac.gopestat.web.shared.SaveFamilyAction;
import org.siemac.metamac.gopestat.web.shared.SaveInstanceAction;
import org.siemac.metamac.gopestat.web.shared.SaveOperationAction;
import org.siemac.metamac.gopestat.web.shared.UpdateFamilyOperationsAction;
import org.siemac.metamac.gopestat.web.shared.UpdateInstancesOrderAction;
import org.siemac.metamac.gopestat.web.shared.UpdateOperationFamiliesAction;
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
	

	
	protected void configureHandlers() {

	}
	
}
