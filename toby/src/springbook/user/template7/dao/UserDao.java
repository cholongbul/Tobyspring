package springbook.user.template7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;
//로컬 클래스
public class UserDao {
	




	private DataSource dataSource;//DB커넥션 기능을 가진 제공되는 인터페이스 DataSource인터페이스로 변환
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}//수정자 메소드 이용해서 주입
	

	//try/catch/finally컨텍스트 코드 메소드로 분리
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			 c= dataSource.getConnection();
			 ps = stmt.makePreparedStatement(c);
			 ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if ( ps != null) {try {ps.close();} catch (SQLException e) {}}
			if ( ps != null) {try {c.close();} catch (SQLException e) {}}
		}
		
	}
	
	
	public void add(final User user) throws SQLException{//로컬 클래스에 final로 선언된 User 를 전달할 수 있다.
		class AddStatement implements StatementStrategy{//로컬 클래스로 선언하여 클래스가 많아지는 문제 해결
			
			@Override
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());

				return ps;
			}
			
		}
		StatementStrategy st = new AddStatement();//생성자 파라미터로 user를 전달하지 않아도 된다.
		jdbcContextWithStatementStrategy(st);
		
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
	//클라이언트 책임을 담당
	public void deleteAll() throws SQLException {
			
			StatementStrategy st = new DeleteAllStatement(); //선정한 전략 클래스의 오브젝트 생성
			jdbcContextWithStatementStrategy(st); //컨텍스트 호출 전략 오브젝트 전달
	
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



