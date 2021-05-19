package springbook.user.ex10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.domain.User;
//DI(�������� ����) ���� ������ ����
//Ŭ���� ���̳� �ڵ忡�� ��Ÿ�� ������ �������谡 �巯���� �ʴ´�. �׷��� ���ؼ��� �������̽����� �����ϰ� �־�� �Ѵ�.
//��Ÿ�� ������ ��������� �����̳ʳ� ���丮 ���� �� 3�� ���簡 �����Ѵ�.
//��������� ����� ������Ʈ�� ���� ���۷����� �ܺο��� �����������ν� ���������.
public class UserDao {
	
	
//	public UserDao() {
//		connectionMaker = new DConnectionMaker();
//	}
	// ex5�������� ������, DConnectionMaker();�̶�� ��ü���� Ŭ������ ���縦 �˰� �ְ� �������谡 �̹� �����Ǿ� �ִ�.


	//�Ʒ��� DaoFactory�� ���� ������ �ڵ�
	//DaoFactory�� UserDao�� ������ �Ķ���ͷ� UserDao�� ����� ������Ʈ�� �����ϰ� �����Ѵ�. == �������� ������ �̿��ϰ� �ִ�.
	private ConnectionMaker connectionMaker;//�ν��Ͻ�
	
	//������ �Ű� ������ �̿��� Ŭ���̾�Ʈ���� ���� �δ� å�� �ѱ��
	public UserDao(ConnectionMaker connectionMaker) {//�����ڸ� ���� DI�����̳ʰ� ����
		this.connectionMaker = connectionMaker;
	}
	
	
	//�������� �˻� DaoFactory�� �̿��ϴ� ������
	public UserDao( ) {
//		DaoFactory daoFactory = new DaoFactory();
//		this.connectionMaker = daoFactory.connectionMaker();//�ڽ��� � ConnectionMaker�� ������� ���� ���Ѵ�. �ܺ� ������ �ƴ϶� ���丮�� ��û
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class); //���ø����̼� ���ؽ�Ʈ �̿� ����
	}//�������� �˻��� ���԰� ū ���� ����. ����� ����. ���� ���� ����� �ڵ�.
	
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		
		Connection c =connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
		
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		
		Connection c =connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement(
				"select * from users where id = ?"
				);
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
				
				
		
	}
	
	

	

}



