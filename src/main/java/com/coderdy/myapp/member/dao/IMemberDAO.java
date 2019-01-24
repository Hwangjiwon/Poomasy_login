package com.coderdy.myapp.member.dao;

import java.util.List;

import com.coderdy.myapp.member.model.MemberVO;

public interface IMemberDAO {
	public void insertMember(MemberVO member)throws Exception;
	MemberVO selectMember(String userid)throws Exception;
	List<MemberVO> selectAllMembers()throws Exception;
}
