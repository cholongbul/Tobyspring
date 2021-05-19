package springbook.user.test3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/springbook?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=UTF8&", "root", "1234");
		
		return c;
	}

}
