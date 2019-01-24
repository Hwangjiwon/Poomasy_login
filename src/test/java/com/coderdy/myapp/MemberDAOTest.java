package com.coderdy.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coderdy.myapp.member.dao.MemberDAO;
import com.coderdy.myapp.member.model.MemberVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDAOTest {

	@Autowired
	MemberDAO dao;

	@Test
	public void testInserMember() throws Exception{
		
		MemberVO vo = new MemberVO();
		vo.setUserid("test3");
		vo.setPassword("12345");
		vo.setEmail("TestEmail3@test.com");
		vo.setName("tester3");
		vo.setPhone("01012245667");
		dao.insertMember(vo);
	}
}
