package org.siemac.metamac.access.control.base.serviceapi.utils;

import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.core.common.bt.domain.ExternalItemBt;

/**
 * Mocks
 */
public class AccessControlDoMocks {

    public static Role createRole() {
        Role role = new Role();
        role.setCode("TECNICO_DIFUSION");
        role.setTitle("Técnico de difusión");
        role.setDescription("Técnico de difusión estadística encargado de las labores de publicación");
        return role;
    }

    public static App createApp() {
        App app = new App();
        app.setCode("METAMAC");
        app.setTitle("Usuario METAMAC");
        app.setDescription("Usuario de pruebas METAMAC");
        return app;
    }

    public static User createUser() {
        User user = new User();
        user.setUsername("rdiaada");
        user.setName("Rita");
        user.setSurname("Díaz Adán");
        user.setMail("rdiaada@arte-consultores.com");
        return user;
    }

    public static Access createAccess(App app, Role role, User user, ExternalItemBt operation) {
        Access access = new Access();
        access.setApp(app);
        access.setUser(user);
        access.setRole(role);
        access.setOperation(operation);
        return access;
    }

}
