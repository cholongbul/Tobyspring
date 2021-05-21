package springbook.user.template7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;
//���� Ŭ����
public class UserDao {
	




	private DataSource dataSource;//DBĿ�ؼ� ����� ���� �����Ǵ� �������̽� DataSource�������̽��� ��ȯ
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}//������ �޼ҵ� �̿��ؼ� ����
	

	//try/catch/finally���ؽ�Ʈ �ڵ� �޼ҵ�� �и�
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
	
	
	public void add(final User user) throws SQLException{//���� Ŭ������ final�� ����� User �� ������ �� �ִ�.
		class AddStatement implements StatementStrategy{//���� Ŭ������ �����Ͽ� Ŭ������ �������� ���� �ذ�
			
			@Override
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());

				return ps;
			}
			
		}
		StatementStrategy st = new AddStatement();//������ �Ķ���ͷ� user�� �������� �ʾƵ� �ȴ�.
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
	
	//������ ��� ���� 
	//Ŭ���̾�Ʈ å���� ���
	public void deleteAll() throws SQLException {
			
			StatementStrategy st = new DeleteAllStatement(); //������ ���� Ŭ������ ������Ʈ ����
			jdbcContextWithStatementStrategy(st); //���ؽ�Ʈ ȣ�� ���� ������Ʈ ����
	
	}
	
	//���̺� ���ڵ� ���� ����
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



