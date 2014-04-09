package com.socialchef.service.models;

import org.junit.Assert;
import org.junit.Test;

public class UsersTest {

    @Test
    public void validUserstest() {
	for(User u : User.mockUsers()) {
	    Assert.assertTrue("User should be valid: "+u.toString(),
		    u.validateUser());
	}
    }

}
