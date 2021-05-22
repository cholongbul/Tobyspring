package springbook.user.template13.dao;

public interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);

}
