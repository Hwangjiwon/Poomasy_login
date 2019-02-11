package com.coderdy.myapp.member.service;

import java.util.List;

import com.coderdy.myapp.member.model.MemberVO;
import com.coderdy.myapp.member.model.SnsMemberVO;

public interface IMemberService {
	void insertMemberService(MemberVO member);
	public MemberVO idCheck(String userid);
	MemberVO selectMemberService(String userid);
	List<MemberVO> selectAllMembersService();
	void insertSnsMemberService(SnsMemberVO snsMember);
	public void updateSnsMember(SnsMemberVO snsMember);
}
