package springbook.user.exception1.dao;

import java.sql.SQLException;
//초난감 예외처리
public class WrongException {
	
	public void no1() {
		try {
			//...
		} catch (SQLException e) {//잡아놓고 아무것도 안 하는 코드
		}
		
	}

	public void no2() {
		try {
			//...
		} catch (SQLException e) {//잡아놓고 출력만 하는 코드
		System.out.println(e);
		}
	}
	
	public void no3() {
		try {
			//...
		} catch (SQLException e) {//잡아놓고 출력만 하는 코드
		e.printStackTrace();
		}
	}
	
	public void no4() {
		try {
			//...
		} catch (SQLException e) {//잡고 종료 그나마 나음
		e.printStackTrace();
		System.exit(1);
		}
	}
	
	public void method1() throws Exception {//무의미한 throws
		method2();//호출
		//...
	}
	public void method2() throws Exception {//무의미한 throws
		method3();//호출
		//...
	}
	public void method3() throws Exception {//무의미한 throws
		//...
	}
}
