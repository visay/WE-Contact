package com.wecontact;

public class Staff {
	
	public String id;
	public String name;
	public String email;
	public String position;
	public String mobile;
	public String skype;
	
	public Staff() 
	{
	}
	
	public Staff(String id, String name, String email, String position, String mobile, String skype)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.position = position;
		this.mobile = mobile;
		this.skype = skype;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}