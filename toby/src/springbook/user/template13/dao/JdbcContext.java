package springbook.user.template13.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	
private DataSource dataSource;//DBĿ�ؼ� ����� ���� �����Ǵ� �������̽� DataSource�������̽��� ��ȯ
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}//������ �޼ҵ� �̿��ؼ� ����
	

	//try/catch/finally���ؽ�Ʈ �ڵ� �޼ҵ�� �и�
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException{
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
	
	//������ �ʴ� �и��ؼ� jdbccontext�� �ű�
	public void executeSql(final String query) throws SQLException {//�и��ؼ� ��Ȱ�� ����
		workWithStatementStrategy(// ���ø�
				new StatementStrategy() {// �ݹ� �������̽�

					@Override
					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {// �ݹ� �޼ҵ�
						PreparedStatement ps = c.prepareStatement(query);
						return ps;
					}
				}); // ���ؽ�Ʈ ȣ�� ���� ������Ʈ ����
	}
	
	
	

}
