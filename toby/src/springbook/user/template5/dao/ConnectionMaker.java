package springbook.user.template5.dao;

import java.sql.Connection;
import java.sql.SQLException;
//인터페이스 도입
public interface ConnectionMaker {
	public Connection makeConnection() throws ClassNotFoundException, SQLException;

}
