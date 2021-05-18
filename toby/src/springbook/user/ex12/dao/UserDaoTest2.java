package springbook.user.ex12.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springbook.user.ex8.domain.User;

//�̱��� ������Ʈ�� Ȯ�� �׽�Ʈ
public class UserDaoTest2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {//main������ ���Թ��� ����� ���⿡ �˻��� Ȱ���ؾ� ��.
		//���ø����̼� ���ؽ�Ʈ�� ����
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("cholong13");
		user.setName("�ʷպ�");
		user.setPassword("greenlight");
		
		dao.add(user);
		
		System.out.println(user.getId() + "��� ����");
		

	}

}
