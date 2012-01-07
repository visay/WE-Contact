package com.wecontact;

import android.util.Log;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StaffParser {
	
	private final List<Staff> list;
	private final HashMap<String, String> map;
	private static final String tag = "StaffParser";
	
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	
	public StaffParser()
	{
		this.list = new ArrayList<Staff>();
		this.map = new HashMap<String, String>();
	}
	
	private String getNodeValue(NamedNodeMap map, String key)
	{
		String nodeValue = null;
		Node node = map.getNamedItem(key);
		if (node != null) {
			nodeValue = node.getNodeValue();
		}
		
		return nodeValue;
	}
	
	public List<Staff> getList()
	{
		return this.list;
	}
	
	public void parse(InputStream inStream)
	{
		try {
			this.factory = DocumentBuilderFactory.newInstance();
			this.builder = this.factory.newDocumentBuilder();
			this.builder.isValidating();
			Document doc = this.builder.parse(inStream, null);
			doc.getDocumentElement().normalize();
			NodeList staffList = doc.getElementsByTagName("staff");
			final int length = staffList.getLength();
			for (int i = 0; i < length; i++) {
				final NamedNodeMap attr = staffList.item(i).getAttributes();
				final String staffName = getNodeValue(attr, "name");
				final String staffId = getNodeValue(attr, "id");
				Staff staff = new Staff(staffName, staffId);
				this.list.add(staff);
				this.map.put(staffName, staffName);
				Log.d(tag, staff.toString());
			}
		} catch (Exception e) {
		}
	}
}