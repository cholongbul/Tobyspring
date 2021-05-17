package springbook.user.ex7.dao;

//UserDao의 생성 책임을 맡은 팩토리 클래스
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
//위의 코드는 매번 DConnectionMaker를 생성하기에 좋지 않다.
	
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
	} // 분리해서 중복을 제거
	//제어권이 상위 메소드 템플릿에게 맡겨지는 제어의 역전이 일어났다
	
}
