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
	
	interface GlobalResources extends ClientBundle {
		@NotStrict
		@Source("AccessControlWebStyles.css")
		CssResource css();
	}

	
//	private static AccessControlWebConstants constants;
//	private static AccessControlWebMessages messages;
	
	public static final AccessControlWebGinjector ginjector = GWT.create(AccessControlWebGinjector.class);
	
    public void onModuleLoad() {
        // This is required for GWT-Platform proxy's generator.
        DelayedBindRegistry.bind(ginjector);
        ginjector.getPlaceManager().revealCurrentPlace();
        
        // Inject global styles
        GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
    }


//    public static GopestatWebConstants getConstants() {
//    	if (constants == null) {
//    		constants = (GopestatWebConstants) GWT.create(GopestatWebConstants.class);
//    	}
//    	return constants;
//    }
//    
//    
//    public static GopestatWebMessages getMessages() {
//    	if (messages == null) {
//    		messages = (GopestatWebMessages) GWT.create(GopestatWebMessages.class);
//    	}
//    	return messages;
//    }

    
    public static AccessControlWebGinjector getAccessControlWebGinjector() {
      return ginjector;
    }
    
}