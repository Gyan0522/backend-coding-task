package com.example.zooplus.exception;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {

	public DataNotFoundException(String id) {
		super("No data found for "+id  );
	}

	public DataNotFoundException() {
		super("No data found");
	}
}