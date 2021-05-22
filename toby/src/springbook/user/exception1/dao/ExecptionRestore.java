package springbook.user.exception1.dao;

//재시도를 통한 예외 복구
public class ExecptionRestore {

	public void restore() {

		int maxretry = MAX_RETRY; // 최대 몇번 재시도할지
		while (maxretry-- > 0) {//

			try {
				// ... //예외가 발생할 가능성이 있는 시도
				return; // 작업 성공

			} catch (Exception e) {
				// 로그 출력. 정해진 시간만큼 대기
			} finally {
				// 리소스 반납. 정리 작업
			}

		}
		throw new RetryFailedException();// 최대 재시도 횟수를 넘기면 직접 예외 발생
	}
}
