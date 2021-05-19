package springbook.user.test3.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.jta.SpringJtaSynchronizationAdapter;

import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)//스프링-테스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations="/springbook/user/test3/dao/applicationContext.xml")//테스트 컨텍스트가 자동으로 만들어줄 애플리케이션 컨텍스트 위치 지정
public class UserDaoTest {
	
	@Autowired
	private ApplicationContext context; //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 값 주입

	
	private UserDao dao;//setup() 메소드에서 만드는 오브젝트를 테스트 메소드에서 사용할 수 있도록 인스턴스 변수로 선언
	private User user1; //주로 사용하는 오브젝트 픽스처 적용
	private User user2;
	private User user3;

	
	@Before //JUnit이 제공하는 애노테이션, @Test 메소드가 실행되기 전에 먼저 실행돼야 하는 메소드를 정의한다.
	public void setUp() {
		System.out.println(this.context); //컨텍스트 체크 - 컨텍스트는 한 번만 만들어진다
		System.out.println(this); //오브젝트 값 체크 - 오브젝트는 매번 만들어진다
		this.dao = context.getBean("userDao", UserDao.class);
		this.user1 = new User("cho1", "초롱불1", "greenligh1");
		this.user2 = new User("cho2", "초롱불2", "greenligh2");
		this.user3 = new User("cho3", "초롱불3", "greenligh3");
	}

	 //Junit에게 테스트 메소드임을 알려줌
	@Test
	public void addAndGet() throws SQLException {//테스트 메소드는 반드시 public으로 선언
																		
		//테스트에서 반복되는 부분 @Before로 추출
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		//UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();//테이블 비우기
		assertThat(dao.getCount(), is(0));//0개인지 체크, 아니면 오류 메세지 출력

		User user = new User("cho", "초롱불", "greenlight");
		
		dao.add(user);
		assertThat(dao.getCount(), is(1));//1개인지 체크, 아니면 오류 메세지 출력


		System.out.println(user.getId() + "등록 성공");

		User user2 = dao.get(user.getId());

		
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
		
	

	}
	
	@Test
	public void count() throws SQLException {//테스트 메소드는 반드시 public으로 선언
		
		//테스트에서 반복되는 부분 @Before로 추출
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		//UserDao dao = context.getBean("userDao", UserDao.class);

//		User user1 = new User("cho1", "초롱불1", "greenligh1");
//		User user2 = new User("cho2", "초롱불2", "greenligh2");
//		User user3 = new User("cho3", "초롱불3", "greenligh3"); 픽스처를 적용
		
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
		//테스트에서 반복되는 부분 @Before로 추출
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", UserDao.class);
		//UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
	
}
