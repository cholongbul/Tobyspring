package springbook.user.exception1.dao;

import java.sql.SQLException;

import com.mysql.cj.exceptions.MysqlErrorNumbers;

public class ExecptionTransform {
	
	public void add(User user) throws DuplicateUserIdException, SQLException {
		try {
			//JDBC�� �̿��� user������ DB�� �߰��ϴ� �ڵ� 
			//�Ǵ� �׷� ����� ���� �ٸ� SQLException�� ������ �޼ҵ带 ȣ���ϴ� �ڵ�
		} 
		catch(SQLException e) {
			//ErrorCode�� Mysql�� Duplicate Entry(1062)�� ���� ��ȯ
			if(e.getErrorCode()==MysqlErrorNumbers.ER_DUP_ENTRY)
				throw DuplicateUserIdExcepion();
			else
				throw e;
		}
	}

}
