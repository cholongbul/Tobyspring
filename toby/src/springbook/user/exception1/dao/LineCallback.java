package springbook.user.exception1.dao;

public interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);

}
