package springbook.user.template14.dao;

public interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);

}
