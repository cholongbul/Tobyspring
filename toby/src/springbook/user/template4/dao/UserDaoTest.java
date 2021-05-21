package springbook.user.template4.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

public class UserDaoTest {
	
	private UserDao dao;//setup() �޼ҵ忡�� ����� ������Ʈ�� �׽�Ʈ �޼ҵ忡�� ����� �� �ֵ��� �ν��Ͻ� ������ ����
	private User user1; //�ַ� ����ϴ� ������Ʈ �Ƚ�ó ����
	private User user2;
	private User user3;

	
	@Before //JUnit�� �����ϴ� �ֳ����̼�, @Test �޼ҵ尡 ����Ǳ� ���� ���� ����ž� �ϴ� �޼ҵ带 �����Ѵ�.
	public void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		this.dao = context.getBean("userDao", UserDao.class);
		this.user1 = new User("cho1", "�ʷպ�1", "greenligh1");
		this.user2 = new User("cho2", "�ʷպ�2", "greenligh2");
		this.user3 = new User("cho3", "�ʷպ�3", "greenligh3");
	}

	 //Junit���� �׽�Ʈ �޼ҵ����� �˷���
	@Test
	public void addAndGet() throws SQLException {//�׽�Ʈ �޼ҵ�� �ݵ�� public���� ����
																		
		//�׽�Ʈ���� �ݺ��Ǵ� �κ� @Before�� ����
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		//UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();//���̺� ����
		assertThat(dao.getCount(), is(0));//0������ üũ, �ƴϸ� ���� �޼��� ���

		User user = new User("cho", "�ʷպ�", "greenlight");
		
		dao.add(user);
		assertThat(dao.getCount(), is(1));//1������ üũ, �ƴϸ� ���� �޼��� ���


		System.out.println(user.getId() + "��� ����");

		User user2 = dao.get(user.getId());

		
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
		
	

	}
	
	@Test
	public void count() throws SQLException {//�׽�Ʈ �޼ҵ�� �ݵ�� public���� ����
		
		//�׽�Ʈ���� �ݺ��Ǵ� �κ� @Before�� ����
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		//UserDao dao = context.getBean("userDao", UserDao.class);

//		User user1 = new User("cho1", "�ʷպ�1", "greenligh1");
//		User user2 = new User("cho2", "�ʷպ�2", "greenligh2");
//		User user3 = new User("cho3", "�ʷպ�3", "greenligh3"); �Ƚ�ó�� ����
		
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
		//�׽�Ʈ���� �ݺ��Ǵ� �κ� @Before�� ����
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		//UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
	
}
