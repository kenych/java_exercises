package com.test.task1.srv;

public class Sequence {
	
	int seq = 0;
	
	final int size ;

	public Sequence(int size) {
		super();
		this.size = size;
	}
	
	public int getNext(){
		if(seq==size){
			seq = 0;
		}
		
		return seq++;
	}

}
