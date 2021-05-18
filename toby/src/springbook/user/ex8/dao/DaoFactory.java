package springbook.user.ex8.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//���ø����̼� ���ؽ�Ʈ �Ǵ� �� ���丮�� ����� ���� ������� ǥ��
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