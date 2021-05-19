package springbook.user.test1.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest2 {

	 //Junit에게 테스트 메소드임을 알려줌
	@Test
	public void addAndGet() throws SQLException {//테스트 메소드는 반드시 public으로 선언
																		
		// 애플리케이션 컨텍스트를 적용
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);

		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("cho1");
		user.setName("초롱불");
		user.setPassword("greenlight");

		dao.add(user);

		System.out.println(user.getId() + "등록 성공");

		User user2 = dao.get(user.getId());

		
		//일치를 자동적으로 체크
//		if(!user.getName().equals(user2.getName())) {
//			System.out.println("테스트 실패 (name)");
//		}
//		else if(!user.getPassword().equals(user2.getPassword())) {
//			System.out.println("테스트 실패 (password)");
//		}
//		else {
//			System.out.println("조회 테스트 성공");
//		}
		
		//JUnit 문법을 이용해서더 간단히 작성	
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	

	}
	
	public static void main(String[] args) {
		
		JUnitCore.main("springbook.user.test1.dao.UserDaoTest2");
	}

}
