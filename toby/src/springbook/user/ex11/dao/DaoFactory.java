package springbook.user.ex11.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//ī���� Ŀ�ؼ� �������� �߰�
//���ø����̼� ���ؽ�Ʈ �Ǵ� �� ���丮�� ����� ���� ������� ǥ��
@Configuration 
public class DaoFactory {

	@Bean 
	public UserDao userDao() {
		UserDao userDao = new UserDao(connectionMaker()); 
		userDao.setConnectionMaker(connectionMaker());//������ �޼ҵ� ����
		return userDao;
	}
	
	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(realConnectionMaker());//realConnectionMaker���� Ŀ��Ʈ ��� ����.
	}
	
	@Bean public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}
	
//	@Bean 
//	public ConnectionMaker connectionMaker() {
//		return new DConnectionMaker();
//		//return new LocalDBConnectionMaker();
//		//return new ProductionDBConnectionMaker();
//		//�������� ������ Ȱ���ϸ� �� Ŭ������ �����ϰ� �� �κ��� �����ϴ� �͸����� ��� Dao�� ���� �����ϴ�.
//	}
//	

}