package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.base;

import javax.annotation.PostConstruct;

import org.siemac.metamac.access.control.core.conf.AccessControlConfigurationService;
import org.siemac.metamac.access_control.rest.internal.AccessControlRestInternalConstants;
import org.siemac.metamac.rest.common.v1_0.domain.ResourceLink;
import org.siemac.metamac.rest.utils.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonDo2RestMapperV10Impl implements CommonDo2RestMapperV10 {

    @Autowired
    private AccessControlConfigurationService configurationService;

    private String                            statisticalResourcesApiInternalEndpointV10;

    @PostConstruct
    public void init() throws Exception {

        // ENDPOINTS
        // Access Control Internal Api V1.0
        String accessControlApiInternalEndpoint = configurationService.retrieveAccessControlInternalApiUrlBase();
        statisticalResourcesApiInternalEndpointV10 = RestUtils.createLink(accessControlApiInternalEndpoint, AccessControlRestInternalConstants.API_VERSION_1_0);
    }

    @Override
    public ResourceLink toResourceLink(String kind, String href) {
        ResourceLink target = new ResourceLink();
        target.setKind(kind);
        target.setHref(href);
        return target;
    }

    @Override
    public String toResourceLink(String resourceSubpath) {
        String link = RestUtils.createLink(statisticalResourcesApiInternalEndpointV10, resourceSubpath);
        return link;
    }
}
