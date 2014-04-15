package com.socialchef.service.models;

import java.security.MessageDigest;

import org.bouncycastle.util.encoders.Hex;
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
	
	@Test
	public void passwordHashTest() throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		for(User u : User.mockUsers()) {
			String old_pass = u.getPassword();
			md.update(u.getPassword().getBytes("UTF-8"));
			md.update(u.getCreatedAt().toString().getBytes("UTF-8"));
			byte[] digest = md.digest();
			md.reset();
			u.makePasswordSalt();
			String new_pass = new String(Hex.encode(digest));
			Assert.assertFalse("Disgest should produce a different password",
					old_pass.equalsIgnoreCase(new_pass));
			Assert.assertTrue("Password's Salt should be equals"+" Digest: "+new_pass+" Pass: "+u.getPassword(),
					new_pass.equals(u.getPassword()));
		}
	}

}
