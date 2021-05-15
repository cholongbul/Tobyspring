package springbook.user.ex4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//�޼ҵ� �ܰ迡�� �и��ϴ� �� �Ѿ Ŭ������ ���� �и�
//�� �̻� ����� �̿��� Ȯ�� ����� ����� �ʿ䰡 ����
public class SimpleConnectionMaker {
	
	public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/springbook?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=UTF8&", "root", "1234");
		
		return c;
		
	}

}
