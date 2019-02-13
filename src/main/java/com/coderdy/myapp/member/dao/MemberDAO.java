package com.coderdy.myapp.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coderdy.myapp.member.controller.MemberController;
import com.coderdy.myapp.member.model.MemberVO;
import com.coderdy.myapp.member.model.SnsMemberVO;

@Repository // 해당 클래스가 DAO라는 것을 알리기 위한 어노테이션
public class MemberDAO implements IMemberDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Override
	public void insertMember(MemberVO member){
		// TODO Auto-generated method stub
		logger.info("insertMember_memberDAO");
		sqlSession.insert("com.coderdy.myapp.member.dao.mapper.MemberMapper.insertMember", member);
		
		logger.info("insertMember_memberDAO_ END");
	}

	@Override
	public MemberVO selectMember(String userid){
		// TODO Auto-generated method stub
		return (MemberVO) sqlSession.selectOne("com.coderdy.myapp.member.dao.mapper.MemberMapper.selectMember",userid);
	}

	@Override
	public List<MemberVO> selectAllMembers(){
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.coderdy.myapp.member.dao.mapper.MemberMapper.selectAllMembers");
	}
	
	@Override
	public void updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		sqlSession.update("com.coderdy.myapp.member.dao.mapper.MemberMapper.updateMember", member);
	}

	@Override
	public void insertSnsMember(SnsMemberVO snsMember) {
		// TODO Auto-generated method stub
		sqlSession.insert("com.coderdy.myapp.member.dao.mapper.MemberMapper.insertSnsMember", snsMember);
	}

	@Override
	public MemberVO idCheck(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.coderdy.myapp.member.dao.mapper.MemberMapper.idCheck",userid);
	}

	@Override
	public SnsMemberVO selectSnsMember(String sns_id) {
		// TODO Auto-generated method stub
		return (SnsMemberVO) sqlSession.selectOne("com.coderdy.myapp.member.dao.mapper.MemberMapper.selectSnsMember",sns_id);
	}

	@Override
	public void updateSnsMember(SnsMemberVO snsMember) {
		// TODO Auto-generated method stub
		sqlSession.update("com.coderdy.myapp.member.dao.mapper.MemberMapper.updateSnsMember", snsMember);
	}

	
}
