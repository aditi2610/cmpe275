package com.trySpring;

public class ImplGreeter implements Greeter {
	String name;
	
//	public ImplGreeter( String name) {
//		this.name = name;
//	}
	@Override
	public void setName(String name) {
		this.name= name;
		
	}
	/**
	 * this method would append the name passed in the beans.xml to Hello World from
	 */
	@Override
	public String getGreeting() {
		
		return "Hello World from " +this.name;
	}

}
