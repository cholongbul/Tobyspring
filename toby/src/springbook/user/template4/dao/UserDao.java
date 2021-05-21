package springbook.user.template4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;
//전략 패턴을 통한 확장 - 인터페이스- 구현을 통한 확장
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
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = dataSource.getConnection();
			StatementStrategy strategy = new DeleteAllStatement();//인터페이스 구현체를 알고 있는 건 개방폐쇄 원칙, 전략패턴의 효율성에서 어긋난다.
			ps = strategy.makePreparedStatement(c);//변하는 부분을 메소드로 추출하고 변하지 않는 부분에서 호출하도록 만들었다.
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw e;
		} finally {
			if(ps != null) {
				try {
					ps.close();

				} catch (SQLException e) {//ps.close()메소드에서도 예외가 발생할 수 있기에 이를 잡아줘야 한다. 그렇지 않으면 connection을 close하지 못하고 빠져나갈 수 있다.
				}
			}
			if (c!= null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	
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



