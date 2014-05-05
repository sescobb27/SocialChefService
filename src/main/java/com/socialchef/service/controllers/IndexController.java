package com.socialchef.service.controllers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.models.Category;
import com.socialchef.service.models.Location;
import com.socialchef.service.repositories.implementation.ValuesServiceRepository;

@Controller
public class IndexController {

	@Autowired
	private ValuesServiceRepository valuesRepo;
	
	@RequestMapping(value="/")
	public String index() {
		return "resources/index.html";
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	@ResponseBody
	public List<Category> getCategories() {
		return valuesRepo.getCategories();
	}
	
	@RequestMapping(value="/locations", method=RequestMethod.GET)
	@ResponseBody
	public List<Location> getLocations() {
		return valuesRepo.getLocations();
	}
	
	@RequestMapping(value="/signature", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> signature(
			@RequestParam(value="signature", required=true) String signature,
			@RequestParam(value="secret", required=true, defaultValue="") String secret) {
		
		Map<String, String> response = new HashMap<String, String>();
		String twitterSecret = System.getenv("TWITTER_SECRET");
		String signKey = twitterSecret+"&"+secret;
		String HMAC_SHA1_ALGORITHM = "HmacSHA1";
		try {
			SecretKeySpec key = new SecretKeySpec(signKey.getBytes("UTF-8"),
					HMAC_SHA1_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(key);
			byte[] rawHmac = mac.doFinal(signature.getBytes());
			String oauthSignature = Base64.encodeBase64String(rawHmac);
			response.put("signature", oauthSignature);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
