package springbook.user.exception1.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.User;

//스프링의 JdbcTemplate
public class UserDao {

	private JdbcTemplate jdbcTemplate;// 기존 JdbcContext에서 JdbcTemplate로

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);// 생성 및 주입

	}// 수정자 메소드 이용해서 주입
	
	private RowMapper<User> userMapper = new RowMapper<User>() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	};//중복 분리

	public void add(final User user) {// 클라이언트 final변수

		this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)", user.getId(), user.getName(),
				user.getPassword());
		// 내장 콜백 활용
	}

	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] { id }, this.userMapper);

	}

	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
	}

	// 데이터 모두 삭제
	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");// 내장 콜백 사용
	}

	// 테이블 레코드 갯수 리턴
	public int getCount() {
//		return this.jdbcTemplate.query(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				return con.prepareStatement("select count(*) from users");
//			}
//		}, new ResultSetExtractor<Integer>() {
//			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
//				rs.next();
//				return rs.getInt(1);
//			}
//		});
//	}

		return this.jdbcTemplate.queryForInt("select count(*) from users");// 위의 코드 압축버전
	}

}
