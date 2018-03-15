package com.test.task1.domain;

public class Event {
	private String id;
	private String action;
	private int value;

	public Event(String id, String action, int value) {
		super();
		this.id = id;
		this.action = action;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + "]";
	}
	
	

}
