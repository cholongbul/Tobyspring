package springbook.user.serviceAbstract1.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.Level;
import springbook.user.domain.User;
import springbook.user.domain.User2;

//�������� JdbcTemplate
public class UserDaoJdbc implements UserDao {

	private JdbcTemplate jdbcTemplate;// ���� JdbcContext���� JdbcTemplate��

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);// ���� �� ����

	}// ������ �޼ҵ� �̿��ؼ� ����
	
	private RowMapper<User2> userMapper = new RowMapper<User2>() {
		public User2 mapRow(ResultSet rs, int rowNum) throws SQLException {
			User2 user = new User2();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setLevel(Level.valueOf(rs.getInt("level")));
			user.setLogin(rs.getInt("login"));
			user.setRecommend(rs.getInt("recommend"));
			return user;
		}
	};//�ߺ� �и�

	public void add(final User2 user) {// Ŭ���̾�Ʈ final����

		this.jdbcTemplate.update("insert into users(id, name, password, level, login, recommend) values(?,?,?,?,?,?)", user.getId(), user.getName(),
				user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
		// ���� �ݹ� Ȱ��
	}

	public User2 get(String id) {
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] { id }, this.userMapper);

	}

	public List<User2> getAll() {
		return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
	}

	// ������ ��� ����
	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");// ���� �ݹ� ���
	}

	// ���̺� ���ڵ� ���� ����
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

		return this.jdbcTemplate.queryForInt("select count(*) from users");// ���� �ڵ� �������
	}

}
