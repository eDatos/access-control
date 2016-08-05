package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.base;

import org.siemac.metamac.rest.common.v1_0.domain.ResourceLink;

public interface CommonDo2RestMapperV10 {

    public ResourceLink toResourceLink(String kind, String href);

    public String toResourceLink(String resourceSubpath);
}
