package springbook.user.ex12.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.ex12.dao.DaoFactory;
import springbook.user.ex8.domain.User;
//���ø����̼� ���ؽ�Ʈ�� ������ UserDaoTest
public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {//main������ ���Թ��� ����� ���⿡ �˻��� Ȱ���ؾ� ��.
		//���ø����̼� ���ؽ�Ʈ�� ����
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(DaoFactory.class);
		
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("cholong12");
		user.setName("�ʷպ�");
		user.setPassword("greenlight");
		
		dao.add(user);
		
		System.out.println(user.getId() + "��� ����");
		

	}

}