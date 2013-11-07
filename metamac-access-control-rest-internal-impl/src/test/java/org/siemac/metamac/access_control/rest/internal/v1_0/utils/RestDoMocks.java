package org.siemac.metamac.access_control.rest.internal.v1_0.utils;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
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
}