package com.coderdy.myapp.member.dao;

import java.util.List;

import com.coderdy.myapp.member.model.MemberVO;
import com.coderdy.myapp.member.model.SnsMemberVO;

//Service�� ������ ū ���̴� ������, DAO�� DB�� �����Ǿ� ���� �� Mybatis�ڵ尡 ����ȴ�
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
