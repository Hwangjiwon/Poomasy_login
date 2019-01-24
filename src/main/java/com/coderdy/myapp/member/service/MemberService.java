package com.coderdy.myapp.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderdy.myapp.member.controller.MemberController;
import com.coderdy.myapp.member.dao.IMemberDAO;
import com.coderdy.myapp.member.model.MemberVO;

@Service
public class MemberService implements IMemberService{

	@Autowired
	private IMemberDAO memberDao;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	
	@Override
	public void insertMemberService(MemberVO member) {
		// TODO Auto-generated method stub
		logger.info("insertMemberService "+member.toString());
		memberDao.insertMember(member);
	}

	@Override
	public MemberVO selectMemberService(String userid) {
		// TODO Auto-generated method stub
		logger.info("selectMemberService "+userid.toString());
		return memberDao.selectMember(userid);
	}

	@Override
	public List<MemberVO> selectAllMembersService() {
		// TODO Auto-generated method stub
		logger.info("SelectAllMembersService ");
		return memberDao.selectAllMembers();
	}

}
