package org.siemac.metamac.access.control.web.client;

import org.siemac.metamac.access.control.web.client.gin.AccessControlWebGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.gwtplatform.mvp.client.DelayedBindRegistry;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AccessControlWeb implements EntryPoint {
	
    private static AccessControlWebConstants constants;
    private static AccessControlWebMessages messages;
    private static AccessControlWebCoreMessages coreMessages;
    
    public static final AccessControlWebGinjector ginjector = GWT.create(AccessControlWebGinjector.class);
    
    
	interface GlobalResources extends ClientBundle {
		@NotStrict
		@Source("AccessControlWebStyles.css")
		CssResource css();
	}
	
    public void onModuleLoad() {
        // This is required for GWT-Platform proxy's generator.
        DelayedBindRegistry.bind(ginjector);
        ginjector.getPlaceManager().revealCurrentPlace();
        
        // Inject global styles
        GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
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