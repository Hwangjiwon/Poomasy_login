package com.coderdy.myapp.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderdy.myapp.kakao.KakaoLogin;
import com.coderdy.myapp.member.model.MemberVO;
import com.coderdy.myapp.member.service.IMemberService;
import com.coderdy.myapp.naver.NaverLoginBO;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;

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
	
	private KakaoLogin kakaoLogin;
	@Autowired
	private void setKakaoLogin(KakaoLogin kakaoLogin) {
		this.kakaoLogin = kakaoLogin;
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		// redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		model.addAttribute("n_url", naverAuthUrl);

		return "member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login(String userid, String password, Model model, HttpSession session) {

		MemberVO member = memberService.selectMemberService(userid);
		if (member != null) {
			String dbPassword = member.getPassword();
			if (dbPassword == null) { // user 정보 없음
				model.addAttribute("message", "NOT_VALID_USER");
			} else {
				if (dbPassword.equals(password)) { // 비밀번호 일치
					session.setAttribute("userid", userid);
					session.setAttribute("email", member.getEmail());
					session.setAttribute("name", member.getName());
					session.setAttribute("phone", member.getPhone());

					return "member/callback";
				} else { // 비밀번호 불일치
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
	// 메소드 이름은 LOGOUT 매게 변수는 SESSION
	public String logout(HttpSession session, HttpServletRequest request) {
		System.out.println(session + "======logout");
		session.invalidate(); // logout

		return "redirect:/";
	}

	// 네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/member/callback", method = {RequestMethod.POST, RequestMethod.GET})
	public String naverCallback(ModelMap model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException {
		/* 네아로 인증이 성공적으로 완료되면 code 파라미터가 전달되며 이를 통해 access token을 발급 */
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);

		// 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);

		return "member/callback";
	}

	
	
	// 카카오 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/member/kakaoCallback", method = {RequestMethod.POST, RequestMethod.GET})
	public String kakaoCallback(ModelMap model, @RequestParam("code") String code, HttpSession session)
			throws Exception {

		JsonNode userInfo = kakaoLogin.getKakaoUserInfo(code);

		System.out.println(userInfo);

		String id = userInfo.get("id").toString();
		String nickname = userInfo.get("properties").get("nickname").toString();


		model.addAttribute("k_userInfo", userInfo);
		model.addAttribute("id", id);
		model.addAttribute("nickname", nickname);
		
		/*
		JsonNode token = KakaoLogin.getAccessToken(code);

		JsonNode profile = KakaoLogin.getKakaoUserInfo(token.path("access_token").toString());
		System.out.println(profile);
		MemberVO member = KakaoLogin.changeData(profile);
		member.setUserid(member.getUserid());
		System.out.println("member.getUerId() : " + member.getUserid());
		member.setEmail(member.getEmail());
		System.out.println("member.getEmail() : " + member.getEmail());
		member.setSns_type("k");
		System.out.println("member.getSns_type() : " + member.getSns_type());

		System.out.println("session: " + session);
		session.setAttribute("login", member);
		System.out.println(member.toString());

		memberService.insertSnsMemberService(member);
		
		*/
		
		return "member/kakaoCallback";
	}	
}