package com.coderdy.myapp.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coderdy.myapp.member.model.MemberVO;

@Repository // �ش� Ŭ������ DAO��� ���� �˸��� ���� ������̼�
public class MemberDAO implements IMemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.coderdy.myapp.member.dao.mapper";

	@Override
	public void insertMember(MemberVO member){
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".insertMember", member);
	}

	@Override
	public MemberVO selectMember(String userid){
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".selectMember", userid);
	}

	@Override
	public List<MemberVO> selectAllMembers(){
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".selectAllMembers");
	}

}
