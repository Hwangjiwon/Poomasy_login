package com.coderdy.myapp.member.dao;

import java.util.List;

import com.coderdy.myapp.member.model.MemberVO;

//Service�� ������ ū ���̴� ������, DAO�� DB�� �����Ǿ� ���� �� Mybatis�ڵ尡 ����ȴ�
public interface IMemberDAO {
	public void insertMember(MemberVO member);
	MemberVO selectMember(String userid);
	List<MemberVO> selectAllMembers();
	public void insertSnsMember(MemberVO member);
}
