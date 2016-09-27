package com.sar2016.util;

import java.util.ArrayList;
import java.util.List;

import com.sar2016.entities.Contact;

public class Helper {
	static List<Contact> contacts = new ArrayList<Contact>();

	public static List<Contact> getPersistanceLayer(){
		return Helper.contacts;
	}
}
