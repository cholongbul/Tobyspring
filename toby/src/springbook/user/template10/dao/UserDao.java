package springbook.user.template10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;
//코드를 이용하는 수동DI
public class UserDao {
	
	private JdbcContext jdbcContext;
	
	

	private DataSource dataSource;//아직은 모든 메소드가 jdbccontext를 사용하는 게 아니므로 유지

	public void setDataSource(DataSource dataSource) {//jdbccontext에 대한 생성 DI작업을 동시에 수행
		this.jdbcContext = new JdbcContext();//생성
		this.jdbcContext.setDataSource(dataSource);//주입
		this.dataSource = dataSource;
	}//수정자 메소드 이용해서 주입
	public void add(final User user) throws SQLException{//로컬 클래스에 final로 선언된 User 를 전달할 수 있다.
		
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {//인터페이스를 생성자처럼 이용해서 익명 내부 클래스 생성
			
			@Override
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());

				return ps;
			}
		}
				);
		
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
			
		this.jdbcContext.workWithStatementStrategy(
					new StatementStrategy() {//내부 익명 클래스
						
						@Override
						public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
							PreparedStatement ps = c.prepareStatement("delete from users");
							return ps;
						}
					}
					); //컨텍스트 호출 전략 오브젝트 전달
	
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



