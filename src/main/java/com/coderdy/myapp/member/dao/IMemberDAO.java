package com.coderdy.myapp.member.dao;

import java.util.List;

import com.coderdy.myapp.member.model.MemberVO;
import com.coderdy.myapp.member.model.SnsMemberVO;

//Service와 구현상 큰 차이는 없지만, DAO는 DB와 연동되어 세션 및 Mybatis코드가 적용된다
public interface IMemberDAO {
	public void insertMember(MemberVO member);
	public MemberVO idCheck(String userid);
	MemberVO selectMember(String userid);
	List<MemberVO> selectAllMembers();
	public void updateMember(MemberVO member);
	public void insertSnsMember(SnsMemberVO snsMember);
	SnsMemberVO selectSnsMember(String sns_id);
	public void updateSnsMember(SnsMemberVO snsMember);
}
