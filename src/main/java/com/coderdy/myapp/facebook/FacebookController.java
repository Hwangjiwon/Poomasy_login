package com.coderdy.myapp.facebook;

import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;

@Controller
public class FacebookController {

	private final static String F_CLIENT_ID= "759529031097620";
	private final static String F_CLIENT_SECRET= "b0630c2035441e311ec9e89a1160e26b";
	private final static String F_REDIRECT_URI= "http://localhost:8090/myapp/member/facebookCallback";

	public String getAuthorizationUrl(HttpSession session){

    //로그인 버튼연결 주소 생성
		String facebookUrl = "https://www.facebook.com/v2.8/dialog/oauth?"+
				"client_id="+F_CLIENT_ID+
				"&redirect_uri="+F_REDIRECT_URI+
				"&scope=public_profile,email";

		return facebookUrl;
	}

	public String requesFaceBooktAccesToken(HttpSession session, String code) throws Exception {

		String facebookUrl = "https://graph.facebook.com/v2.8/oauth/access_token?"+
						 	"client_id=" + F_CLIENT_ID +
						 	"&redirect_uri=" + F_REDIRECT_URI +
						 	"&client_secret=" + F_CLIENT_SECRET +
						 	"&code="+code;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(facebookUrl);
		String rawJsonString = client.execute(getRequest, new BasicResponseHandler());
		//logger.debug("facebookAccessToken / raw json : "+rawJsonString);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
		String faceBookAccessToken = (String) jsonObject.get("access_token");
		//logger.debug("facebookAccessToken / accessToken : "+faceBookAccessToken);

		session.setAttribute("faceBookAccessToken", faceBookAccessToken);

		return faceBookAccessToken;
	}

	public String facebookUserDataLoadAndSave(String accessToken, HttpSession session) throws Exception {
	    String facebookUrl = "https://graph.facebook.com/me?"+
	            "access_token="+accessToken+
	            "&fields=id,name,email,picture";

	    HttpClient client = HttpClientBuilder.create().build();
	    HttpGet getRequest = new HttpGet(facebookUrl);
	    String rawJsonString = client.execute(getRequest, new BasicResponseHandler());
	    //logger.debug("facebookAccessToken / rawJson!  : "+rawJsonString);

	    JSONParser jsonParser = new JSONParser();
	    JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
	    //logger.debug("facebookUserDataLoadAndSave / raw json : "+jsonObject);

		/* 가져온 데이터를 서비스에 맞춰 가공하는 로직*/
	    return jsonObject.toString();
	}
}