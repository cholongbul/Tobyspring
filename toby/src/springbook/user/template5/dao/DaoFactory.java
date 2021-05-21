package springbook.user.template5.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
//ī���� Ŀ�ؼ� �������� �߰�
//���ø����̼� ���ؽ�Ʈ �Ǵ� �� ���丮�� ����� ���� ������� ǥ��
@Configuration 
public class DaoFactory {

	@Bean 
	public UserDao userDao() {
		UserDao userDao = new UserDaoDeleteAll(); 
		userDao.setDataSource(dataSource());//������ �޼ҵ� ����
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