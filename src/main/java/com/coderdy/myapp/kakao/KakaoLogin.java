package com.coderdy.myapp.kakao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KakaoLogin {

	private final static String K_CLIENT_ID = "32cf5047e2290a65b781450e52fab213";
	private final static String K_REDIRECT_URI = "http://localhost:8090/myapp/member/kakaoCallback";


      public String getAccessToken(String autorize_code) {

        final String RequestUrl = "https://kauth.kakao.com/oauth/token";
        final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
        postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
        postParams.add(new BasicNameValuePair("client_id", K_CLIENT_ID)); // REST API KEY
        postParams.add(new BasicNameValuePair("redirect_uri", K_REDIRECT_URI)); // 리다이렉트 URI
        postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정 중 얻은 code 값

        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(RequestUrl);
        JsonNode returnNode = null;

        try {

          post.setEntity(new UrlEncodedFormEntity(postParams));
          final HttpResponse response = client.execute(post);
          final int responseCode = response.getStatusLine().getStatusCode();

          // JSON 형태 반환값 처리

          ObjectMapper mapper = new ObjectMapper();
          returnNode = mapper.readTree(response.getEntity().getContent());

        } catch (UnsupportedEncodingException e) {

          e.printStackTrace();

        } catch (ClientProtocolException e) {

          e.printStackTrace();

        } catch (IOException e) {

          e.printStackTrace();

        } finally {
          // clear resources
        }
        return returnNode.get("access_token").toString();
      }

      public JsonNode getKakaoUserInfo(String autorize_code) {

        final String RequestUrl = "https://kapi.kakao.com/v1/user/me";
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(RequestUrl);
        String accessToken = getAccessToken(autorize_code);
        // add header
        post.addHeader("Authorization", "Bearer " + accessToken);

        JsonNode returnNode = null;

        try {

          final HttpResponse response = client.execute(post);
          final int responseCode = response.getStatusLine().getStatusCode();
          System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
          System.out.println("Response Code : " + responseCode);

          // JSON 형태 반환값 처리
          ObjectMapper mapper = new ObjectMapper();
          returnNode = mapper.readTree(response.getEntity().getContent());
        } catch (UnsupportedEncodingException e) {

          e.printStackTrace();
        } catch (ClientProtocolException e) {

          e.printStackTrace();
        } catch (IOException e) {

          e.printStackTrace();
        } finally {

          // clear resources
        }
        return returnNode;
      }
}
