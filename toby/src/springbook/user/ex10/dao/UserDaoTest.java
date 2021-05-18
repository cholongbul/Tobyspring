package springbook.user.ex10.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.ex8.domain.User;
//���ø����̼� ���ؽ�Ʈ�� ������ UserDaoTest
public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {//main������ ���Թ��� ����� ���⿡ �˻��� Ȱ���ؾ� ��.
		//���ø����̼� ���ؽ�Ʈ�� ����
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(DaoFactory.class);
		
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("cholong4");
		user.setName("�ʷպ�");
		user.setPassword("greenlight");
		
		dao.add(user);
		
		System.out.println(user.getId() + "��� ����");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + "��ȸ ����");
	}

}