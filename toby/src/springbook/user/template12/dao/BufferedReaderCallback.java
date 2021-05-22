package springbook.user.template12.dao;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
	Integer doSomethingWithReader(BufferedReader br) throws IOException;

}
