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

	 //Junit���� �׽�Ʈ �޼ҵ����� �˷���
	@Test
	public void addAndGet() throws SQLException {//�׽�Ʈ �޼ҵ�� �ݵ�� public���� ����
																		
		// ���ø����̼� ���ؽ�Ʈ�� ����
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);

		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("cho1");
		user.setName("�ʷպ�");
		user.setPassword("greenlight");

		dao.add(user);

		System.out.println(user.getId() + "��� ����");

		User user2 = dao.get(user.getId());

		
		//��ġ�� �ڵ������� üũ
//		if(!user.getName().equals(user2.getName())) {
//			System.out.println("�׽�Ʈ ���� (name)");
//		}
//		else if(!user.getPassword().equals(user2.getPassword())) {
//			System.out.println("�׽�Ʈ ���� (password)");
//		}
//		else {
//			System.out.println("��ȸ �׽�Ʈ ����");
//		}
		
		//JUnit ������ �̿��ؼ��� ������ �ۼ�	
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	

	}
	
	public static void main(String[] args) {
		
		JUnitCore.main("springbook.user.test1.dao.UserDaoTest2");
	}

}
