package springbook.user.test1.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springbook.user.domain.User;
//애플리케이션 컨텍스트를 적용한 UserDaoTest
public class UserDaoTest {

	@Test //Junit에게 테스트 메소드임을 알려줌
	public static void addAndGet() throws ClassNotFoundException, SQLException {//테스트 메소드는 반드시 public으로 선언
																		
		// 애플리케이션 컨텍스트를 적용
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);

		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("cholong12");
		user.setName("초롱불");
		user.setPassword("greenlight");
		
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		
		
		User user2 = dao.get(user.getId());
//		System.out.println(user2.getName());
//		System.out.println(user2.getPassword());
//		
//		System.out.println(user2.getId() + "조회 성공");
		//user1과 user2가 일치하는지는 사람이 수동으로 체크해야 함.
		
		
		//일치를 자동적으로 체크
		if(!user.getName().equals(user2.getName())) {
			System.out.println("테스트 실패 (name)");
		}
		else if(!user.getPassword().equals(user2.getPassword())) {
			System.out.println("테스트 실패 (password)");
		}
		else {
			System.out.println("조회 테스트 성공");
		}
	}

}
