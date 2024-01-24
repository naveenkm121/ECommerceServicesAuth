package com.ecommerce.ui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ecommerce.ui.dto.BaseApiResModel;

public class CommonUtility {

	public static boolean isValidEmail(String email) {
		String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic regex pattern													// for email validation
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidMobileNumber(String mobileNumber) {
		// Define a regular expression pattern for a 10-digit number starting with 9, 8, 7, or 6
		String MOBILE_REGEX = "^[6-9]\\d{9}$";
		Pattern pattern = Pattern.compile(MOBILE_REGEX);
		Matcher matcher = pattern.matcher(mobileNumber);
		return matcher.matches();
	}
	

}
