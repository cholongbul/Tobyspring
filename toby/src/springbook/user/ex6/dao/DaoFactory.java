package springbook.user.ex6.dao;

//UserDao�� ���� å���� ���� ���丮 Ŭ����
public class DaoFactory {
	public UserDao userDao() {
ConnectionMaker connectionMaker = new DConnectionMaker(); //���丮�� �޼ҵ尡 userdaoŸ���� ������Ʈ�� ��� ����� �غ��ų�� ����
		UserDao userdao = new UserDao(connectionMaker);
		return new UserDao(connectionMaker);
	}

}
