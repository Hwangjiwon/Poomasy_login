package com.coderdy.myapp.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coderdy.myapp.member.model.MemberVO;
import com.coderdy.myapp.member.service.IMemberService;

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
		return "member/insert";
	}

	@RequestMapping(value = "/member/insert", method = RequestMethod.POST)
	public String insert(MemberVO member) {

		System.out.println("저장시작");
		System.out.println("INSERT INFO : "+ member.getUserid());
		System.out.println("INSERT INFO : "+ member.getPassword());
		System.out.println("INSERT INFO : "+ member.getEmail());
		System.out.println("INSERT INFO : "+ member.getName());
		System.out.println("INSERT INFO : "+ member.getPhone());
		memberService.insertMemberService(member);
		System.out.println("저장완료");
		return "member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "member/login";
	}
}
