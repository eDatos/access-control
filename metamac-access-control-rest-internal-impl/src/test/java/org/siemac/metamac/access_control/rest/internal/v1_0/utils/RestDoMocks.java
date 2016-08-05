package org.siemac.metamac.access_control.rest.internal.v1_0.utils;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.common.test.utils.MetamacMocks;

public class RestDoMocks {

    public User mockUser(String username) {
        User user = new User();

        user.setUsername(username);
        user.setName(MetamacMocks.mockString(20));
        user.setUsername(MetamacMocks.mockString(20));
        user.setCreatedDate(new DateTime(2000, 1, 1, 1, 1, 1, 1));
        user.setCreatedBy("ADMIN");
        user.setMail(MetamacMocks.mockString(20) + "@" + MetamacMocks.mockString(20) + ".com");

        return user;
    }

    public List<User> mockUsers(int count) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < count; i++) {
            users.add(mockUser("username" + count));
        }
        return users;
    }

    public Role mockRole(String code) {
        Role role = new Role();
        role.setCode(code);
        role.setTitle(MetamacMocks.mockString(10));
        role.setDescription(MetamacMocks.mockString(20));
        role.setCreatedDate(new DateTime(2000, 1, 1, 1, 1, 1, 1));
        role.setCreatedBy("ADMIN");
        return role;
    }

    public List<Role> mockRoles(int count) {
        List<Role> roles = new ArrayList<Role>();
        for (int i = 0; i < count; i++) {
            roles.add(mockRole("code" + i));
        }
        return roles;
    }

    public App mockApp(String code) {
        App app = new App();
        app.setCode(code);
        app.setTitle(MetamacMocks.mockString(10));
        app.setDescription(MetamacMocks.mockString(20));
        app.setCreatedDate(new DateTime(2000, 1, 1, 1, 1, 1, 1));
        app.setCreatedBy("ADMIN");
        return app;
    }

    public List<App> mockApps(int count) {
        List<App> apps = new ArrayList<App>();
        for (int i = 0; i < count; i++) {
            apps.add(mockApp("code" + i));
        }
        return apps;
    }
}