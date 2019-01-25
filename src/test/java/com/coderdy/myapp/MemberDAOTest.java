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
	MemberDAO memberDao;

	@Test
	public void testInserMember() throws Exception{
		
		MemberVO member = new MemberVO();
		member.setUserid("test7");
		member.setPassword("12476");
		member.setEmail("TestEmail7@test.com");
		member.setName("tester7");
		member.setPhone("01013675667");
		memberDao.insertMember(member);
	}
}
