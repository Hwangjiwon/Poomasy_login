package com.coderdy.myapp.member.dao;

import java.util.List;

import com.coderdy.myapp.member.model.MemberVO;

//Service와 구현상 큰 차이는 없지만, DAO는 DB와 연동되어 세션 및 Mybatis코드가 적용된다
public interface IMemberDAO {
	public void insertMember(MemberVO member);
	MemberVO selectMember(String userid);
	List<MemberVO> selectAllMembers();
	public void insertSnsMember(MemberVO member);
}
