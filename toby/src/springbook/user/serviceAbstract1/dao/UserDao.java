package springbook.user.serviceAbstract1.dao;

import java.util.List;

import springbook.user.domain.User;
import springbook.user.domain.User2;
//UserDao�������̽�
public interface UserDao {
	void add(User2 user);
	User2 get(String id);
	List<User2> getAll();
	void deleteAll();
	int getCount();

}
