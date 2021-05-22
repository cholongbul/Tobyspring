package springbook.user.exception1.dao;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
	Integer doSomethingWithReader(BufferedReader br) throws IOException;

}
