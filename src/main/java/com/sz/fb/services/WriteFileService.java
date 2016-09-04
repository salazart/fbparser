package com.sz.fb.services;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFileService {
	private static final String FILE_EXTENSION = ".txt";
	private static final boolean APPENDER = false;
	private String fileName;
	private static int counter;
	
	public WriteFileService(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public void writeContent(String content){
		try (FileWriter writer = new FileWriter(fileName + counter++ + FILE_EXTENSION, APPENDER);) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
