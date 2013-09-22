package de.slackspace.qspriter.writer;

import java.io.IOException;
import java.io.OutputStream;

public interface Writer<T> {

	public void write(OutputStream stream, T data) throws IOException;
}
