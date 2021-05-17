package springbook.user.ex7.dao;

//UserDao�� ���� å���� ���� ���丮 Ŭ����
public class DaoFactory {
//	public UserDao userDao() {
//		return new UserDao(new DConnectionMaker());
//	}
//	
//	public AccountDao accountDao() {
//		return new AccountDao(new DConnectionMaker());
//	}
//	
//	public MessageDao messageDao() {
//		return new MessageDao(new DConnectionMaker());
//	}
//���� �ڵ�� �Ź� DConnectionMaker�� �����ϱ⿡ ���� �ʴ�.
	
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}
	
	public AccountDao accountDao() {
		return new AccountDao(connectionMaker());
	}
	
	public MessageDao messageDao() {
		return new MessageDao(connectionMaker());
	}
	
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	} // �и��ؼ� �ߺ��� ����
	//������� ���� �޼ҵ� ���ø����� �ð����� ������ ������ �Ͼ��
	
}
