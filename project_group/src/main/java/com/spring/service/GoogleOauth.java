package com.spring.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.spring.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements OauthInterface {
		private String GOOGLE_SNS_BASE_URL = "https://accounts.google.com/o/oauth2/v2/auth";
		private String GOOGLE_SNS_TOKEN_BASE_URL = "https://oauth2.googleapis.com/token";
		private String GOOGLE_SNS_USER_BASE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
		private String GOOGLE_SNS_CLIENT_ID ="351044074609-1t4e5hpi8cu5nekkodtsrgrmj3bccmoe.apps.googleusercontent.com";
		private String GOOGLE_SNS_REDIRECT_URI="http://localhost:8081/signin/GOOGLE/oauth";
		private String GOOGLE_SNS_SECRET = "nivciEgwFqct77JKLj8AWz4G";
	    @Override
	    public String getRedirectURL() {
	        Map<String, Object> params = new HashMap<>();
	        params.put("scope", "email%20profile%20https://www.googleapis.com/auth/user.birthday.read%20https://www.googleapis.com/auth/user.gender.read");
	        params.put("response_type", "code");
	        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
	        params.put("redirect_uri", GOOGLE_SNS_REDIRECT_URI);

	        String parameterString = params.entrySet().stream()
	                .map(x -> x.getKey() + "=" + x.getValue()) 
	                .collect(Collectors.joining("&"));

	        return GOOGLE_SNS_BASE_URL + "?" + parameterString;
	    }

	    @Override
	    public String requestAccessToken(String code) {
	    	String access_Token = "";
	        String refresh_Token = "";
	        
	        try {
				URL url = new URL(GOOGLE_SNS_TOKEN_BASE_URL);
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

				// POST ????????? ?????? ???????????? false??? setDoOutput??? true???
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);

				// POST ????????? ????????? ???????????? ???????????? ???????????? ?????? ??????
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				// ?????? ?????? ??? ?????????????????? ????????? StringBuffer???????????????
				StringBuilder sb = new StringBuilder();
				sb.append("code=" + code);
				sb.append("&client_id="+GOOGLE_SNS_CLIENT_ID);
				sb.append("&client_secret="+GOOGLE_SNS_SECRET);
				sb.append("&redirect_uri="+GOOGLE_SNS_REDIRECT_URI);
				sb.append("&grant_type=authorization_code");
				bw.write(sb.toString());
				bw.flush();

				// ?????? ????????? 200????????? ??????
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);
				if(responseCode ==200) {
				// ????????? ?????? ?????? JSON????????? Response ????????? ????????????
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}
				System.out.println("response body : " + result);

				// Gson ?????????????????? ????????? ???????????? JSON?????? ?????? ??????
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);

				access_Token = element.getAsJsonObject().get("access_token").getAsString();
				// refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

				System.out.println("access_token : " + access_Token);
				// System.out.println("refresh_token : " + refresh_Token);

				br.close();
				bw.close();
				
				}else {
					
					access_Token="";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return access_Token;
	    }
	    @Override
	    public HashMap<String, Object> getUserinfo(String access_Token) {

			// ???????????? ????????????????????? ?????? ????????? ?????? ??? ????????? HashMap???????????? ??????
			HashMap<String, Object> userInfo = new HashMap<>();
			String reqURL = GOOGLE_SNS_USER_BASE_URL+"?access_Token=" + access_Token;

			try {
				URL url = new URL(reqURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				// ????????? ????????? Header??? ????????? ??????
				conn.setRequestProperty("Authorization", "Bearer " + access_Token);

				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}
				System.out.println("response body : " + result);

				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);

				String id = element.getAsJsonObject().get("id").getAsString();
				String email = element.getAsJsonObject().get("email").getAsString();
				String name = element.getAsJsonObject().get("name").getAsString();
				String img = element.getAsJsonObject().get("picture").getAsString();
				
				UserVO userVO = new UserVO();
				
				String usercode = "g-" + id;
				userVO.setThumnail_img(img);
				userVO.setProfile_img(img);
				userVO.setUsercode(usercode);
				userVO.setUserid(email);
				userVO.setUsername(name);
				userVO.setGender("");
				userVO.setUserpw("none");
				userVO.setLoginsort("GOOGLE");
				
				userInfo.put("userVO", userVO);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return userInfo;
	    }
	    
	  
}
