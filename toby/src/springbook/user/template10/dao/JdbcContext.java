package springbook.user.template10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	
private DataSource dataSource;//DB커넥션 기능을 가진 제공되는 인터페이스 DataSource인터페이스로 변환
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}//수정자 메소드 이용해서 주입
	

	//try/catch/finally컨텍스트 코드 메소드로 분리
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
