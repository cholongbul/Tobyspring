package springbook.user.test3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;
//수정자 메소드 DI 적용
public class UserDao {
	




	private DataSource dataSource;//DB커넥션 기능을 가진 제공되는 인터페이스 DataSource인터페이스로 변환
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}//수정자 메소드 이용해서 주입
	

	
	
	
	
	public void add(User user) throws SQLException{
		
		Connection c =dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
		
	}
	
	public User get(String id) throws SQLException {
		
		Connection c =dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement(
				"select * from users where id = ?"
				);
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();

		User user = null;
		if(rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		
		rs.close();
		ps.close();
		c.close();
		
		if(user == null) throw new EmptyResultDataAccessException(1);
		
		return user;
				
				
		
	}
	
	//데이터 모두 삭제
	public void deleteAll() throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("delete from users");
		ps.executeUpdate();
		
		ps.close();
		c.close();
		
	}
	
	//테이블 레코드 갯수 리턴
	public int getCount() throws SQLException {
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("select count(*) from users");
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
		
	}
	
	

	

}



