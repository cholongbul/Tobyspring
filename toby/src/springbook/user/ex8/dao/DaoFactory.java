package springbook.user.ex8.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정 정보라는 표시
@Configuration 
public class DaoFactory {

	@Bean 
	public UserDao userDao() {
		UserDao userDao = new UserDao(connectionMaker()); 
		return userDao;
	}
	
	
	@Bean 
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
	

}