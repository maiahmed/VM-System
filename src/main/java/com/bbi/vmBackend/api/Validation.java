package com.bbi.vmBackend.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bbi.vmBackend.da.dao.Session;

public class Validation {
	public boolean loginValidation(String userName, String password) {
		boolean valid = true;
		if (password.length() > 15 || password.length() < 8) {
			System.out.println("Password should be less than 15 and more than 8 characters in length.");
			valid = false;
		}
		if (password.indexOf(userName) > -1) {
			System.out.println("Password Should not be same as user name");
			valid = false;
		}
		String upperCaseChars = "(.*[A-Z].*)";
		if (!password.matches(upperCaseChars)) {
			System.out.println("Password should contain atleast one upper case alphabet");
			valid = false;
		}
		String lowerCaseChars = "(.*[a-z].*)";
		if (!password.matches(lowerCaseChars)) {
			System.out.println("Password should contain atleast one lower case alphabet");
			valid = false;
		}
		String numbers = "(.*[0-9].*)";
		if (!password.matches(numbers)) {
			System.out.println("Password should contain atleast one number.");
			valid = false;
		}

		return valid;

	}

	// what to return
	public boolean validateSessionParametrs(Session session) {
		boolean valid = true;

		int userId = session.getUser_Id();
		int token = session.getToken();
		String key = session.getKey();
		String lastInsertion = session.getLastInsertion().toString();
		String lastUpdate = session.getLastUpdate().toString();

		if (token < 0 || token == 0)
			valid = false;
		if (userId < 0 || userId==0)
			valid = false;
		if ((Integer.parseInt(key)) < 0 || (Integer.parseInt(key)) == 0)
			valid = false;
		if (validateDate(lastInsertion) == false)
			valid = false;
		if (validateDate(lastUpdate) == false)
			valid = false;

		return valid;
	}

	public boolean validateDate(String date) {

		// For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateFormat.setLenient(false); // create Date object
		try {
			Date insertionDate = dateFormat.parse(date);
			System.out.println(insertionDate + " is valid date format");
			return true;
		} catch (ParseException e) {
			System.out.println("Date is Invalid Date format");
			return false;
		}
	}

}
