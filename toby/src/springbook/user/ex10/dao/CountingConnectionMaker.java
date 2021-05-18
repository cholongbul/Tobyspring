package springbook.user.ex10.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {//�������̽� ����, Dao�� �����ϰ� �ִ� �������̽��� �����ϰ� �ִٸ� � ���̵� �������� ���� ����

	int counter = 0;
	private ConnectionMaker realConnectionMaker;

	public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;//DBĿ�ؼ��� �ȸ���. ��� makeConnection�� ȣ��� ������ ī���� ����.
		return realConnectionMaker.makeConnection();//���� �� ������ ��¥ Ŀ�ؼ��� DAO���� ������. 
	}
	
	public int getCounter() {
		return this.counter;
	}

}
