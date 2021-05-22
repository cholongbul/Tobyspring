package springbook.user.exception1.dao;

import java.sql.SQLException;

import com.mysql.cj.exceptions.MysqlErrorNumbers;

public class ExecptionTransform2 {
	//��Ÿ�� ������ �Ϲ�ȭ
	public void add() throws DuplicateUserIdException {
		try {
			//JDBC�� �̿��� user������ DB�� �߰��ϴ� �ڵ� 
			//�Ǵ� �׷� ����� ���� �ٸ� SQLException�� ������ �޼ҵ带 ȣ���ϴ� �ڵ�
		} 
		catch(SQLException e) {
			//ErrorCode�� Mysql�� Duplicate Entry(1062)�� ���� ��ȯ
			if(e.getErrorCode()==MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new DuplicateUserIdException(e); //���� ��ȯ
			else
				throw new RuntimeException(e); //���� ����
		}
	}

}
