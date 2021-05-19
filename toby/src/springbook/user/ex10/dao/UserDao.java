package springbook.user.ex10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.domain.User;
//DI(의존관계 주입) 설명 세가지 조건
//클래스 모델이나 코드에는 런타임 시점의 의존관계가 드러나지 않는다. 그러기 위해서는 인터페이스에만 의존하고 있어야 한다.
//런타임 시점의 의존관계는 컨테이너나 팩토리 같은 제 3의 존재가 결정한다.
//의존관계는 사용할 오브젝트에 대한 레퍼런스를 외부에서 제공해줌으로써 만들어진다.
public class UserDao {
	
	
//	public UserDao() {
//		connectionMaker = new DConnectionMaker();
//	}
	// ex5때까지의 생성자, DConnectionMaker();이라는 구체적인 클래스의 존재를 알고 있고 의존관계가 이미 결정되어 있다.


	//아래로 DaoFactory를 만든 이후의 코드
	//DaoFactory가 UserDao의 생성자 파라미터로 UserDao가 사용할 오브젝트를 결정하고 생성한다. == 의존관계 주입을 이용하고 있다.
	private ConnectionMaker connectionMaker;//인스턴스
	
	//생성자 매개 변수를 이용해 클라이언트에게 관계 맺는 책임 넘기기
	public UserDao(ConnectionMaker connectionMaker) {//생성자를 통해 DI컨테이너가 주입
		this.connectionMaker = connectionMaker;
	}
	
	
	//의존관계 검색 DaoFactory를 이용하는 생성자
	public UserDao( ) {
//		DaoFactory daoFactory = new DaoFactory();
//		this.connectionMaker = daoFactory.connectionMaker();//자신이 어떤 ConnectionMaker를 사용할지 알지 못한다. 외부 주입이 아니라 팩토리에 요청
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class); //애플리케이션 컨텍스트 이용 구현
	}//의존관계 검색은 주입과 큰 차이 없음. 방법의 차이. 주입 쪽이 깔끔한 코드.
	
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		
		Connection c =connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
		
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		
		Connection c =connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement(
				"select * from users where id = ?"
				);
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
				
				
		
	}
	
	

	

}



