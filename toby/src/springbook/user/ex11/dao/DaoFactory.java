package springbook.user.ex11.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//카운팅 커넥션 의존관계 추가
//애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정 정보라는 표시
@Configuration 
public class DaoFactory {

	@Bean 
	public UserDao userDao() {
		UserDao userDao = new UserDao(connectionMaker()); 
		userDao.setConnectionMaker(connectionMaker());//수정자 메소드 주입
		return userDao;
	}
	
	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(realConnectionMaker());//realConnectionMaker에서 커넥트 요소 주입.
	}
	
	@Bean public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}
	
//	@Bean 
//	public ConnectionMaker connectionMaker() {
//		return new DConnectionMaker();
//		//return new LocalDBConnectionMaker();
//		//return new ProductionDBConnectionMaker();
//		//의존관계 주입을 활용하면 새 클래스를 도입하고 이 부분을 수정하는 것만으로 모든 Dao를 수정 가능하다.
//	}
//	

}