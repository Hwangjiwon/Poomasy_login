package com.coderdy.myapp.member.service;

import java.util.List;

import com.coderdy.myapp.member.model.MemberVO;

public interface IMemberService {
	void insertMemberService(MemberVO member);
	MemberVO selectMemberService(String userid);
	List<MemberVO> selectAllMembersService();
}
