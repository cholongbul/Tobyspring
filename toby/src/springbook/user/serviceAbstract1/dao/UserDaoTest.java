package springbook.user.serviceAbstract1.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.Level;
import springbook.user.domain.User;
import springbook.user.domain.User2;
@RunWith(SpringJUnit4ClassRunner.class)//������-�׽�Ʈ �����ӿ�ũ�� JUnit Ȯ���� ����
@ContextConfiguration(locations="/springbook/user/serviceAbstract1/dao/applicationContext.xml")
public class UserDaoTest {
	

	@Autowired
	UserDao dao;//���� ����
	
	private User2 user1; //�ַ� ����ϴ� ������Ʈ �Ƚ�ó ����
	private User2 user2;
	private User2 user3;
	
	@Before //JUnit�� �����ϴ� �ֳ����̼�, @Test �޼ҵ尡 ����Ǳ� ���� ���� ����ž� �ϴ� �޼ҵ带 �����Ѵ�.
	public void setUp() {
		this.user1 = new User2("cho1", "�ʷպ�1", "greenligh1", Level.BASIC, 1, 0);
		this.user2 = new User2("cho2", "�ʷպ�2", "greenligh2", Level.SILVER, 55, 10);
		this.user3 = new User2("cho3", "�ʷպ�3", "greenligh3", Level.GOLD, 100, 40);
	}

	 //Junit���� �׽�Ʈ �޼ҵ����� �˷���
	@Test
	public void addAndGet() throws SQLException {//�׽�Ʈ �޼ҵ�� �ݵ�� public���� ����
																		
	
		dao.deleteAll();//���̺� ����
		assertThat(dao.getCount(), is(0));//0������ üũ, �ƴϸ� ���� �޼��� ���

		dao.add(user1);
		
		User2 userget1 = dao.get(user1.getId());
		checkSameUser(userget1, user1);
		
		dao.add(user2);
		User2 userget2 = dao.get(user2.getId());
		checkSameUser(userget2, user2);
		
	

	}
	
	@Test
	public void count() throws SQLException {//�׽�Ʈ �޼ҵ�� �ݵ�� public���� ����
		
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));

		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));


	}
	
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
	
	//User�ʵ� �� ���� �޼ҵ�
	private void checkSameUser(User2 user1, User2 user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));

		
	}

	
}
