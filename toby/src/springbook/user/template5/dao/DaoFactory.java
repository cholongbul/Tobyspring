package springbook.user.template5.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
//카운팅 커넥션 의존관계 추가
//애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정 정보라는 표시
@Configuration 
public class DaoFactory {

	@Bean 
	public UserDao userDao() {
		UserDao userDao = new UserDaoDeleteAll(); 
		userDao.setDataSource(dataSource());//수정자 메소드 주입
		return userDao;
	}
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:3306/springbook?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=UTF8&");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		return dataSource;
	}
	

	


}