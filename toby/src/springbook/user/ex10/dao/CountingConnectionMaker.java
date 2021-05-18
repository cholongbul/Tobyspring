package springbook.user.ex10.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {//인터페이스 구현, Dao가 의존하고 있는 인터페이스를 구현하고 있다면 어떤 것이든 의존관계 주입 가능

	int counter = 0;
	private ConnectionMaker realConnectionMaker;

	public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;//DB커넥션은 안만듦. 대신 makeConnection이 호출될 때마다 카운터 증가.
		return realConnectionMaker.makeConnection();//세는 게 끝나면 진짜 커넥션을 DAO에게 돌려줌. 
	}
	
	public int getCounter() {
		return this.counter;
	}

}
