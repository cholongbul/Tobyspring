package springbook.user.ex4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//메소드 단계에서 분리하는 걸 넘어서 클래스로 따로 분리
//더 이상 상속을 이용한 확장 방식을 사용할 필요가 없다
public class SimpleConnectionMaker {
	
	public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/springbook?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=UTF8&", "root", "1234");
		
		return c;
		
	}

}
