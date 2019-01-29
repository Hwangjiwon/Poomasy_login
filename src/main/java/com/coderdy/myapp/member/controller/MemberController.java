package com.coderdy.myapp.member.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderdy.myapp.member.model.MemberVO;
import com.coderdy.myapp.member.service.IMemberService;
import com.coderdy.myapp.naver.NaverLoginBO;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

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
	public String insert(Model model) {
		System.out.println("GET INSERT");
		return "member/insert";
	}

	@RequestMapping(value = "/member/insert", method = RequestMethod.POST)
	public String insert(MemberVO member) {
		System.out.println("POST INSERT");
		System.out.println("�������");
		System.out.println("INSERT INFO : " + member.getUserid());
		System.out.println("INSERT INFO : " + member.getPassword());
		System.out.println("INSERT INFO : " + member.getEmail());
		System.out.println("INSERT INFO : " + member.getName());
		System.out.println("INSERT INFO : " + member.getPhone());
		memberService.insertMemberService(member);
		System.out.println("����Ϸ�");
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
	

	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpSession session) {

		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		// redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("���̹�:" + naverAuthUrl);
		// ���̹�
		model.addAttribute("url", naverAuthUrl);

		return "member/login";
	}

	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	// �޼ҵ� �̸��� LOGOUT �Ű� ������ SESSION
	public String logout(HttpSession session) {
		System.out.println(session + "======logout");
		session.invalidate();
		
		return "member/logout";
	}

	// ���̹� �α��� ������ callbackȣ�� �޼ҵ�
	@RequestMapping(value = "/member/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException {
		System.out.println("����� callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);

		// �α��� ����� ������ �о�´�.
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		System.out.println(naverLoginBO.getUserProfile(oauthToken).toString());
		model.addAttribute("result", apiResult);
		System.out.println("result" + apiResult);
		
		
		
		
		return "member/callback";
	}
}