package springbook.user.ex5.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.ex1.domain.User;
//DAO의 확장-인터페이스의 도입
public class UserDao {
	
	private ConnectionMaker connectionMaker;
	
	public UserDao() {
		connectionMaker = new DConnectionMaker();//여전히 남아있는 관계설정 생성자 문제
	}
	
	
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



