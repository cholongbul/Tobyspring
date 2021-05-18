package springbook.user.ex9.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.ex8.domain.User;
//싱글톤 패턴을 적용한 UserDao
public class UserDao {
	
	private ConnectionMaker connectionMaker; //초기에 설정하면 사용 중에는 바뀌지 않는 읽기전용 인스턴스 변수
	//private Connection c;
	//private User user; 
	//매번 새로운 값으로 바뀌는 정보를 담은 인스턴스 변수는 심각한 문제를 야기한다.
	//쓰레드 환경에서 여러 사용자가 이용하는 와중에 정보가 겹쳐써진다든지 하는 문제 발생.
	
	//생성자 매개 변수를 이용해 클라이언트에게 관계 맺는 책임 넘기기
	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
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



