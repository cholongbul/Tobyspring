package springbook.user.ex6.dao;

import java.sql.SQLException;

import springbook.user.ex1.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConnectionMaker connectionMaker = new DConnectionMaker(); //UserDao�� ����� ���� Ŭ������ �׽�Ʈ�� �Է��Ͽ� ����
		
		UserDao dao = new UserDao(connectionMaker);
		
		User user = new User();
		user.setId("cholong");
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
