package com.logreader.restservice;

public class LogOutput {

	private final long id;
	private final String content;

	public LogOutput(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
