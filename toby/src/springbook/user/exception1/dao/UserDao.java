package springbook.user.exception1.dao;

import java.util.List;

import springbook.user.domain.User;
//UserDao�������̽�
public interface UserDao {
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();

}
