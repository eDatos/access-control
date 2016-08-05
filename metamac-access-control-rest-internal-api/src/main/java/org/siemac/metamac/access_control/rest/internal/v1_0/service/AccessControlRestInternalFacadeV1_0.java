package org.siemac.metamac.access_control.rest.internal.v1_0.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.siemac.metamac.rest.access_control.v1_0.domain.Apps;
import org.siemac.metamac.rest.access_control.v1_0.domain.Roles;
import org.siemac.metamac.rest.access_control.v1_0.domain.Users;

@Path("v1.0")
// IMPORTANT: If a new version of API is added, remember change latest url y urlrewrite.xml in war
public interface AccessControlRestInternalFacadeV1_0 {

    @GET
    @Produces({"application/xml"})
    @Path("users")
    Users findUsers(@QueryParam("query") String query, @QueryParam("limit") String limit, @QueryParam("offset") String offset);

    @GET
    @Produces({"application/xml"})
    @Path("roles")
    Roles findRoles();

    @GET
    @Produces({"application/xml"})
    @Path("applications")
    Apps findApps();
}
