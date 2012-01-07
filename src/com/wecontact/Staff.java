package com.wecontact;

public class Staff {
	
	public String name;
	public String id;
	
	public Staff() 
	{
	}
	
	public Staff(String name, String id)
	{
		this.name = name;
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}