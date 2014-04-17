package com.socialchef.service.helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;

public class Encryption {

	public static String encryptSHA256(String []words)
			throws UnsupportedEncodingException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			for (String word : words) {
				md.update(word.getBytes("UTF-8"));
			}
			byte[] digest = md.digest();
			return new String(Hex.encode(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
