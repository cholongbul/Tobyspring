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
@RunWith(SpringJUnit4ClassRunner.class)//스프링-테스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations="/springbook/user/serviceAbstract1/dao/applicationContext.xml")
public class UserDaoTest {
	

	@Autowired
	UserDao dao;//직접 주입
	
	private User2 user1; //주로 사용하는 오브젝트 픽스처 적용
	private User2 user2;
	private User2 user3;
	
	@Before //JUnit이 제공하는 애노테이션, @Test 메소드가 실행되기 전에 먼저 실행돼야 하는 메소드를 정의한다.
	public void setUp() {
		this.user1 = new User2("cho1", "초롱불1", "greenligh1", Level.BASIC, 1, 0);
		this.user2 = new User2("cho2", "초롱불2", "greenligh2", Level.SILVER, 55, 10);
		this.user3 = new User2("cho3", "초롱불3", "greenligh3", Level.GOLD, 100, 40);
	}

	 //Junit에게 테스트 메소드임을 알려줌
	@Test
	public void addAndGet() throws SQLException {//테스트 메소드는 반드시 public으로 선언
																		
	
		dao.deleteAll();//테이블 비우기
		assertThat(dao.getCount(), is(0));//0개인지 체크, 아니면 오류 메세지 출력

		dao.add(user1);
		
		User2 userget1 = dao.get(user1.getId());
		checkSameUser(userget1, user1);
		
		dao.add(user2);
		User2 userget2 = dao.get(user2.getId());
		checkSameUser(userget2, user2);
		
	

	}
	
	@Test
	public void count() throws SQLException {//테스트 메소드는 반드시 public으로 선언
		
		
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
	
	//User필드 값 검증 메소드
	private void checkSameUser(User2 user1, User2 user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));

		
	}

	
}
