package springbook.user.test1.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springbook.user.domain.User;
//���ø����̼� ���ؽ�Ʈ�� ������ UserDaoTest
public class UserDaoTest {

	@Test //Junit���� �׽�Ʈ �޼ҵ����� �˷���
	public static void addAndGet() throws ClassNotFoundException, SQLException {//�׽�Ʈ �޼ҵ�� �ݵ�� public���� ����
																		
		// ���ø����̼� ���ؽ�Ʈ�� ����
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);

		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("cholong12");
		user.setName("�ʷպ�");
		user.setPassword("greenlight");
		
		dao.add(user);
		
		System.out.println(user.getId() + "��� ����");
		
		
		User user2 = dao.get(user.getId());
//		System.out.println(user2.getName());
//		System.out.println(user2.getPassword());
//		
//		System.out.println(user2.getId() + "��ȸ ����");
		//user1�� user2�� ��ġ�ϴ����� ����� �������� üũ�ؾ� ��.
		
		
		//��ġ�� �ڵ������� üũ
		if(!user.getName().equals(user2.getName())) {
			System.out.println("�׽�Ʈ ���� (name)");
		}
		else if(!user.getPassword().equals(user2.getPassword())) {
			System.out.println("�׽�Ʈ ���� (password)");
		}
		else {
			System.out.println("��ȸ �׽�Ʈ ����");
		}
	}

}
