package com.socialchef.service.models;

import org.junit.Assert;
import org.junit.Test;

import com.socialchef.service.helpers.Encryption;

public class UsersTest {

	@Test
	public void validUserstest() {
		for(User u : User.mockUsers()) {
			Assert.assertTrue("User should be valid: "+u.toString(),
					u.validateUser());
		}
	}
	
	@Test
	public void passwordHashTest() throws Exception {
		for(User u : User.mockUsers()) {
			String []encrypt = {u.getPassword(), u.getCreatedAt().toString()};
			String old_pass = u.getPassword();
			u.makePasswordSalt();
			String new_pass = Encryption.encryptSHA256(encrypt); 
			Assert.assertFalse("Disgest should produce a different password",
					old_pass.equalsIgnoreCase(new_pass));
			Assert.assertTrue("Password's Salt should be equals"+" Digest: "+
					new_pass+" Pass: "+u.getPassword(),
					new_pass.equals(u.getPassword()));
		}
	}

}
