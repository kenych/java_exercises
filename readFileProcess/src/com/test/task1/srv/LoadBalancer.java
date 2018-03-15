package com.test.task1.srv;

import java.util.HashMap;
import java.util.Map;

public class LoadBalancer <T> {
	
	
	private Sequence seq;
	
	private Map<T, Integer> map = new HashMap<>();


	
	public LoadBalancer(Sequence seq) {
		super();
		this.seq = seq;
	}



	public int getNode(T t){
		Integer id = map.get(t);
		if(id==null){
		   id = seq.getNext();
		   map.put(t, id);
		}
		
		
		
		return id;
		
	}

	
	
	

}
