package org.siemac.metamac.access_control.rest.internal.v1_0.mapper.app;

import java.util.List;

import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.rest.access_control.v1_0.domain.Apps;

public interface AppDo2RestMapperV10 {

    public Apps toApps(List<App> sources);
}
