package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.app;

import java.util.List;

import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access_control.rest.internal.AccessControlRestInternalConstants;
import org.siemac.metamac.access_control.rest.internal.v1_0.mapper.base.CommonDo2RestMapperV10;
import org.siemac.metamac.rest.access_control.v1_0.domain.Apps;
import org.siemac.metamac.rest.common.v1_0.domain.ResourceLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppDo2RestMapperv10Impl implements AppDo2RestMapperV10 {

    @Autowired
    private CommonDo2RestMapperV10 commonDo2RestMapperV10;

    @Override
    public Apps toApps(List<App> sources) {
        Apps targets = new Apps();
        targets.setKind(AccessControlRestInternalConstants.KIND_APPS);

        // Values
        for (App source : sources) {
            org.siemac.metamac.rest.access_control.v1_0.domain.App target = toApp(source);
            targets.getApps().add(target);
        }

        return targets;
    }

    public org.siemac.metamac.rest.access_control.v1_0.domain.App toApp(App source) {
        if (source == null) {
            return null;
        }

        org.siemac.metamac.rest.access_control.v1_0.domain.App target = new org.siemac.metamac.rest.access_control.v1_0.domain.App();
        target.setKind(AccessControlRestInternalConstants.KIND_APP);
        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
        target.setTitle(source.getTitle());
        target.setParentLink(toAppParentLink());

        return target;
    }

    private String toAppsLink() {
        String resourceSubpath = AccessControlRestInternalConstants.LINK_SUBPATH_APPS;
        return commonDo2RestMapperV10.toResourceLink(resourceSubpath);
    }

    private ResourceLink toAppParentLink() {
        return commonDo2RestMapperV10.toResourceLink(AccessControlRestInternalConstants.KIND_APPS, toAppsLink());
    }
}
