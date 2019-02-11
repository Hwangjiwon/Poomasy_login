package com.coderdy.myapp.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderdy.myapp.facebook.FacebookController;
import com.coderdy.myapp.kakao.KakaoLogin;
import com.coderdy.myapp.member.model.MemberVO;
import com.coderdy.myapp.member.model.SnsMemberVO;
import com.coderdy.myapp.member.service.IMemberService;
import com.coderdy.myapp.naver.NaverLoginBO;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Autowired
	IMemberService memberService;

	// ȸ�� Ȯ��
	@ResponseBody
	@RequestMapping(value = "/member/idCheck", method = RequestMethod.POST)
	public int postIdCheck(HttpServletRequest req) throws Exception {

		String userid = req.getParameter("userid");
		MemberVO idCheck = memberService.idCheck(userid);

		int result = 0;

		if (idCheck != null) {
			result = 1;
		}
		return result;
	}

	@RequestMapping(value = "/member/*", method = RequestMethod.GET)
	public String home() {
		return "redirect:/";
	}

	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		return "member/insert";
	}

	@RequestMapping(value = "/member/insert", method = RequestMethod.POST)
	public String insert(MemberVO member) {

		memberService.insertMemberService(member);
		return "redirect:/";
	}

	@RequestMapping(value = "/member/memberList", method = RequestMethod.GET)
	public String memberList(Model model) {
		System.out.println("SELECT ALL MEMBERS");
		List<MemberVO> memberList = memberService.selectAllMembersService();
		model.addAttribute("memberList", memberList);
		return "member/memberList";
	}

	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	/* NaverLoginBO */
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	/* KakaoLogin */
	private KakaoLogin kakaoLogin;

	@Autowired
	private void setKakaoLogin(KakaoLogin kakaoLogin) {
		this.kakaoLogin = kakaoLogin;
	}

	/* GoogleLogin */
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	/* FacebookLogin */
	@Autowired
	private FacebookController facebookLogin;

	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login(Model model, HttpSession session, String code) throws Exception {
		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		model.addAttribute("n_url", naverAuthUrl);

		/* ����code ���� ���۷α��������� �̵� url���� */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String googleAuthUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		model.addAttribute("g_url", googleAuthUrl);

		/* facebook �α��� �̵� url ���� */
		String facebookAuthUrl = facebookLogin.getAuthorizationUrl(session);
		model.addAttribute("f_url", facebookAuthUrl);

		return "member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login(String userid, String password, Model model, HttpSession session) {

		MemberVO member = memberService.selectMemberService(userid);
		if (member != null) {
			String dbPassword = member.getPassword();
			if (dbPassword == null) { // user ���� ����
				model.addAttribute("message", "NOT_VALID_USER");
			} else {
				if (dbPassword.equals(password)) { // ��й�ȣ ��ġ
					session.setAttribute("userid", userid);
					session.setAttribute("email", member.getEmail());
					session.setAttribute("name", member.getName());
					session.setAttribute("phone", member.getPhone());

					return "member/callback";
				} else { // ��й�ȣ ����ġ
					model.addAttribute("massage", "WRONG_PASSWORD");
				}
			}
		} else {
			model.addAttribute("message", "USER_NOT_FOUND");
		}
		session.invalidate();

		return "member/login";
	}

	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	// �޼ҵ� �̸��� LOGOUT �Ű� ������ SESSION
	public String logout(HttpSession session, HttpServletRequest request) {
		System.out.println(session + "======logout");
		session.invalidate(); // logout

		return "redirect:/";
	}
	
	
	// ���̹� �α��� ������ callbackȣ�� �޼ҵ�
	@RequestMapping(value = "/member/naverCallback", method = { RequestMethod.POST, RequestMethod.GET })
	public String naverCallback(ModelMap model, @RequestParam String code, @RequestParam String state,
			HttpSession session) throws IOException {
		/* �׾Ʒ� ������ ���������� �Ϸ�Ǹ� code �Ķ���Ͱ� ���޵Ǹ� �̸� ���� access token�� �߱� */
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);

		// �α��� ����� ������ �о�´�.
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);

		JsonParser Parser = new JsonParser();
		JsonObject jsonObj = (JsonObject) Parser.parse(apiResult);
		JsonObject memberObj = (JsonObject) jsonObj.get("response");

		model.addAttribute("n_id", memberObj.get("id"));
		model.addAttribute("n_email", memberObj.get("email"));

		SnsMemberVO snsMember = new SnsMemberVO();
		snsMember.setSns_type("n");
		snsMember.setSns_id(memberObj.get("id").toString());
		snsMember.setSns_email(memberObj.get("email").toString());

		memberService.insertSnsMemberService(snsMember);

		return "member/naverCallback";
	}

	// īī�� �α��� ������ callbackȣ�� �޼ҵ�
	@RequestMapping(value = "/member/kakaoCallback", method = { RequestMethod.POST, RequestMethod.GET })
	public String kakaoCallback(ModelMap model, @RequestParam("code") String code, HttpSession session)
			throws Exception {

		JsonNode userInfo = kakaoLogin.getKakaoUserInfo(code);

		System.out.println(userInfo);
		model.addAttribute("k_userInfo", userInfo);

		model.addAttribute("k_id", userInfo.get("id").asText());
		model.addAttribute("k_name", userInfo.get("properties").get("nickname").asText());

		SnsMemberVO snsMember = new SnsMemberVO();
		snsMember.setSns_type("k");
		snsMember.setSns_id(userInfo.get("id").asText());
		snsMember.setSns_name(userInfo.get("properties").get("nickname").asText());

		memberService.insertSnsMemberService(snsMember);

		return "member/kakaoCallback";
	}

	/* Google callback controller */
	@RequestMapping(value = "/member/googleCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public String googleCallback(ModelMap model, HttpServletRequest request) throws Exception {

		System.out.println("Google login success");
		String code = request.getParameter("code");

		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(),
				null);

		String accessToken = accessGrant.getAccessToken();
		Long expireTime = accessGrant.getExpireTime();
		if (expireTime != null && expireTime < System.currentTimeMillis()) {
			accessToken = accessGrant.getRefreshToken();
			System.out.printf("accessToken is expired. refresh token = {}", accessToken);
		}
		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();

		PlusOperations plusOperations = google.plusOperations();
		Person profile = plusOperations.getGoogleProfile();
		System.out.println(profile.getDisplayName());
		model.addAttribute("g_userInfo", profile);
		model.addAttribute("g_userName", profile.getDisplayName());
		model.addAttribute("g_userId", profile.getId());

		System.out.println(profile.getId());

		SnsMemberVO snsMember = new SnsMemberVO();
		snsMember.setSns_type("g");
		snsMember.setSns_id(profile.getId());
		snsMember.setSns_name(profile.getDisplayName());
		memberService.insertSnsMemberService(snsMember);

		return "member/googleCallback";
	}

	/* Facebook callback controller */
	@RequestMapping(value = "/member/facebookCallback")
	public String facebookCallback(ModelMap model, String code, HttpSession session, String state) throws Exception {
		// logger.debug("facebookAccessToken / code : "+code);

		String accessToken = facebookLogin.requesFaceBooktAccesToken(session, code);
		String facebookResult = facebookLogin.facebookUserDataLoadAndSave(accessToken, session);

		// ���������� ������
		model.addAttribute("fResult", facebookResult);
		System.out.println(facebookResult);

		JsonParser Parser = new JsonParser();
		JsonObject jsonObj = (JsonObject) Parser.parse(facebookResult);

		model.addAttribute("f_name", jsonObj.get("name"));
		model.addAttribute("f_id", jsonObj.get("id"));
		model.addAttribute("f_email", jsonObj.get("email"));

		SnsMemberVO snsMember = new SnsMemberVO();
		snsMember.setSns_type("f");
		snsMember.setSns_id(jsonObj.get("id").toString());
		snsMember.setSns_email(jsonObj.get("email").toString());
		snsMember.setSns_name(jsonObj.get("name").toString());
		memberService.insertSnsMemberService(snsMember);

		return "member/facebookCallback";
	}
	
	@RequestMapping(value = "/member/updateSnsMember", method = RequestMethod.POST)
	public void updateSnsMamber(SnsMemberVO snsMember) {
		
		memberService.updateSnsMember(snsMember);
	}
}