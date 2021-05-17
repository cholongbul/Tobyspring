package springbook.user.ex6.dao;

//UserDao의 생성 책임을 맡은 팩토리 클래스
public class DaoFactory {
	public UserDao userDao() {
ConnectionMaker connectionMaker = new DConnectionMaker(); //팩토리의 메소드가 userdao타입의 오브젝트를 어떻게 만들고 준비시킬지 결정
		UserDao userdao = new UserDao(connectionMaker);
		return new UserDao(connectionMaker);
	}

}
