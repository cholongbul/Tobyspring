package springbook.user.exception1.dao;

//��õ��� ���� ���� ����
public class ExecptionRestore {

	public void restore() {

		int maxretry = MAX_RETRY; // �ִ� ��� ��õ�����
		while (maxretry-- > 0) {//

			try {
				// ... //���ܰ� �߻��� ���ɼ��� �ִ� �õ�
				return; // �۾� ����

			} catch (Exception e) {
				// �α� ���. ������ �ð���ŭ ���
			} finally {
				// ���ҽ� �ݳ�. ���� �۾�
			}

		}
		throw new RetryFailedException();// �ִ� ��õ� Ƚ���� �ѱ�� ���� ���� �߻�
	}
}
