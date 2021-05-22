package springbook.user.exception1.dao;

import java.sql.SQLException;
//�ʳ��� ����ó��
public class WrongException {
	
	public void no1() {
		try {
			//...
		} catch (SQLException e) {//��Ƴ��� �ƹ��͵� �� �ϴ� �ڵ�
		}
		
	}

	public void no2() {
		try {
			//...
		} catch (SQLException e) {//��Ƴ��� ��¸� �ϴ� �ڵ�
		System.out.println(e);
		}
	}
	
	public void no3() {
		try {
			//...
		} catch (SQLException e) {//��Ƴ��� ��¸� �ϴ� �ڵ�
		e.printStackTrace();
		}
	}
	
	public void no4() {
		try {
			//...
		} catch (SQLException e) {//��� ���� �׳��� ����
		e.printStackTrace();
		System.exit(1);
		}
	}
	
	public void method1() throws Exception {//���ǹ��� throws
		method2();//ȣ��
		//...
	}
	public void method2() throws Exception {//���ǹ��� throws
		method3();//ȣ��
		//...
	}
	public void method3() throws Exception {//���ǹ��� throws
		//...
	}
}
