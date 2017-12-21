package com.bbi.vmBackend.api;

public class Validation {
	public boolean loginValidation(String userName, String password) {
		boolean valid = true;
		if (password.length() > 15 || password.length() < 8) {
			System.out
					.println("Password should be less than 15 and more than 8 characters in length.");
			valid = false;
		}
		if (password.indexOf(userName) > -1) {
			System.out.println("Password Should not be same as user name");
			valid = false;
		}
		String upperCaseChars = "(.*[A-Z].*)";
		if (!password.matches(upperCaseChars)) {
			System.out
					.println("Password should contain atleast one upper case alphabet");
			valid = false;
		}
		String lowerCaseChars = "(.*[a-z].*)";
		if (!password.matches(lowerCaseChars)) {
			System.out
					.println("Password should contain atleast one lower case alphabet");
			valid = false;
		}
		String numbers = "(.*[0-9].*)";
		if (!password.matches(numbers)) {
			System.out.println("Password should contain atleast one number.");
			valid = false;
		}

		return valid;

	}
}
