package springbook.user.ex3.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DUserDao extends UserDao {

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		//D�� DB connection ���� �ڵ�
		return null;
	}


}
