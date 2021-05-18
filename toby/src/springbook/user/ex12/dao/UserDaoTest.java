package springbook.user.ex12.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.ex12.dao.DaoFactory;
import springbook.user.ex8.domain.User;
//애플리케이션 컨텍스트를 적용한 UserDaoTest
public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {//main에서는 주입받을 방법이 없기에 검색을 활용해야 함.
		//애플리케이션 컨텍스트를 적용
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(DaoFactory.class);
		
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("cholong12");
		user.setName("초롱불");
		user.setPassword("greenlight");
		
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		

	}

}
