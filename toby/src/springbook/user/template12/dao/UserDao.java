package springbook.user.template12.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

//템플릿 콜백
public class UserDao {

	private JdbcContext jdbcContext;

	private DataSource dataSource;// 아직은 모든 메소드가 jdbccontext를 사용하는 게 아니므로 유지

	public void setDataSource(DataSource dataSource) {// jdbccontext에 대한 생성 DI작업을 동시에 수행
		this.jdbcContext = new JdbcContext();// 생성
		this.jdbcContext.setDataSource(dataSource);// 주입
		this.dataSource = dataSource;
	}// 수정자 메소드 이용해서 주입

	public void add(final User user) throws SQLException {// 클라이언트 final변수

		this.jdbcContext.workWithStatementStrategy(// 템플릿
				new StatementStrategy() {// 콜백 인터페이스

					@Override
					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
						PreparedStatement ps = c
								.prepareStatement("insert into users(id, name, password) values(?,?,?)");
						ps.setString(1, user.getId());
						ps.setString(2, user.getName());
						ps.setString(3, user.getPassword());

						return ps;
					}
				});

	}

	public User get(String id) throws SQLException {

		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		User user = null;
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}

		rs.close();
		ps.close();
		c.close();

		if (user == null)
			throw new EmptyResultDataAccessException(1);

		return user;

	}

	// 데이터 모두 삭제
	// 변하지 않는 부분을 분리시킨 deleteAll()
	public void deleteAll() throws SQLException {
		this.jdbcContext.executeSql("delete from users");//변하는 sql문장
	}



	// 테이블 레코드 갯수 리턴
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
