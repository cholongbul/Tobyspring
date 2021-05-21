package springbook.user.template10.dao;

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
	
	
	

}
