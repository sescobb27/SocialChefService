package com.socialchef.service.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.models.Category;
import com.socialchef.service.models.Location;
import com.socialchef.service.repositories.implementation.ValuesServiceRepository;

//testing my fork
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
	
	@RequestMapping(value="/oauthtwitter", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> oAuthTwitter() {
		OAuthService service = new ServiceBuilder()
    		.provider(TwitterApi.class)
    		.apiKey("hIjIvn6gDwTcGm6cE12GNFT7G")
    		.apiSecret("3bnAGJWzgG7rJexkKn1yci7vapr4r9XrsXuWQJw9qqsjzOuqcD")
    		.callback("http://127.0.0.1:8080/oauthtwittertoken")
    		.build();
		// Fetching the Request Token...
		Token requestToken = service.getRequestToken();
		// Got the Request Token!
		// Now go and authorize
		Map<String, String> response = new HashMap<String, String>();
		String url = service.getAuthorizationUrl(requestToken);
		response.put("url", url);
		return response;
	}
	
	@RequestMapping(value="/oauthtwittertoken", method=RequestMethod.GET)
	public void oAuthTwitterToken(
			@RequestParam(value="oauth_token")String oauth_token,
			@RequestParam(value="oauth_verifier")String oauth_verifier,
			HttpServletResponse http_response) {
		
		OAuthService service = new ServiceBuilder()
			.provider(TwitterApi.class)
			.apiKey("hIjIvn6gDwTcGm6cE12GNFT7G")
			.apiSecret("3bnAGJWzgG7rJexkKn1yci7vapr4r9XrsXuWQJw9qqsjzOuqcD")
			.build();
		// Trade the Request Token and Verfier for the Access Token
		Verifier verifier = new Verifier(oauth_verifier);
		Token request_token = new Token(oauth_token, "");
		// Get the Access Token!
		Token accessToken = service.getAccessToken(request_token, verifier);
		System.out.println(accessToken.getToken());
		OAuthRequest request = 
				new OAuthRequest(Verb.GET,
						"https://api.twitter.com/1.1/account/verify_credentials.json");
		service.signRequest(accessToken, request);
		request.addBodyParameter("include_entities", "true");
		request.addBodyParameter("skip_status", "true");
		Response response = request.send();
		try {
			JSONObject jsonResponse = new JSONObject(response.getBody());
			String username = (String) jsonResponse.get("screen_name");
			String name = (String) jsonResponse.get("name");
			String urlFormat = "%s/username=%s&name=%s";
			http_response.sendRedirect(
					String.format(urlFormat,
							"http://127.0.0.1:8080/#/register",
							username, name));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
