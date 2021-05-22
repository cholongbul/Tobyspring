package springbook.user.exception1.dao;

import java.sql.SQLException;

import com.mysql.cj.exceptions.MysqlErrorNumbers;

public class ExecptionTransform2 {
	//런타임 예외의 일반화
	public void add() throws DuplicateUserIdException {
		try {
			//JDBC를 이용해 user정보를 DB에 추가하는 코드 
			//또는 그런 기능을 가진 다른 SQLException을 던지는 메소드를 호출하는 코드
		} 
		catch(SQLException e) {
			//ErrorCode가 Mysql의 Duplicate Entry(1062)면 예외 전환
			if(e.getErrorCode()==MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new DuplicateUserIdException(e); //예외 전환
			else
				throw new RuntimeException(e); //예외 포장
		}
	}

}
