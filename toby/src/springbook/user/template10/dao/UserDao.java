package springbook.user.template10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;
//�ڵ带 �̿��ϴ� ����DI
public class UserDao {
	
	private JdbcContext jdbcContext;
	
	

	private DataSource dataSource;//������ ��� �޼ҵ尡 jdbccontext�� ����ϴ� �� �ƴϹǷ� ����

	public void setDataSource(DataSource dataSource) {//jdbccontext�� ���� ���� DI�۾��� ���ÿ� ����
		this.jdbcContext = new JdbcContext();//����
		this.jdbcContext.setDataSource(dataSource);//����
		this.dataSource = dataSource;
	}//������ �޼ҵ� �̿��ؼ� ����
	public void add(final User user) throws SQLException{//���� Ŭ������ final�� ����� User �� ������ �� �ִ�.
		
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {//�������̽��� ������ó�� �̿��ؼ� �͸� ���� Ŭ���� ����
			
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
	
	//������ ��� ���� 
	//Ŭ���̾�Ʈ å���� ���
	public void deleteAll() throws SQLException {
			
		this.jdbcContext.workWithStatementStrategy(
					new StatementStrategy() {//���� �͸� Ŭ����
						
						@Override
						public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
							PreparedStatement ps = c.prepareStatement("delete from users");
							return ps;
						}
					}
					); //���ؽ�Ʈ ȣ�� ���� ������Ʈ ����
	
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



