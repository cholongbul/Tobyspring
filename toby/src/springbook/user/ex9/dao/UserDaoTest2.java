package springbook.user.ex9.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//�̱��� ������Ʈ�� Ȯ�� �׽�Ʈ
public class UserDaoTest2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//���� ����
		DaoFactory factory = new DaoFactory();
		UserDao dao1 = factory.userDao();
		UserDao dao2 = factory.userDao();
		
		System.out.println(dao1);
		System.out.println(dao2);
		//���ø����̼� ���ؽ�Ʈ ����
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		UserDao dao3 = context.getBean("userDao", UserDao.class);
		UserDao dao4 = context.getBean("userDao", UserDao.class);

		System.out.println(dao3);
		System.out.println(dao4);

	}

}
