package utils.validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

import entity.User;

public class RequestDataValidator {
	private static final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
	private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$"; 
	private static final String VIN_REGEX = "[A-HJ-NPRR-Z0-9]{17}";
	private static final String NUMBER_PLATE_REGEX = "^[0-9]{4}[ ][ABCEHIKMOPT]{2}[-][1-7]";
	private static final String CAR_NAME_REGEX = "^[a-zA-Z0-9]+$";
	private static final String PERSON_NAME_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
	private static final String PHONE_NUMBER_REGEX ="375(29|44|17|25|33)[1-9]\\d{6}";
	private static final long AGE_LIMIT = 18;
	
	
	public static boolean validateEmail(String email) {
		return Pattern.matches(EMAIL_REGEX, email);
	}
	
	public static boolean validatePassword(String password) {
		return Pattern.matches(PASSWORD_REGEX, password);
	}
	
	public static boolean validateRentPeriod(LocalDate startDate, LocalDate finishDate) {
		if (finishDate.isAfter(startDate) && startDate.isAfter(LocalDate.now())) {
			return true;
		} else {
			return false;
		}
	}	

	public static boolean validateVIN(String VIN) {
		return Pattern.matches(VIN_REGEX, VIN);
	}
	
	public static boolean validateNumberPlate(String NumberPlate) {
		return Pattern.matches(NUMBER_PLATE_REGEX, NumberPlate);
	}
	
	public static boolean validateCarName(String carName) {
		return Pattern.matches(CAR_NAME_REGEX, carName);
	}
	
	public static boolean validatePersonName(String personName) {
		return Pattern.matches(PERSON_NAME_REGEX, personName);
	}
	
	public static boolean validatePrice(String price) {
		try {
		int priceValue = (int)Math.round(Double.parseDouble(price)*100);
		if (priceValue <= 0) {
			return false;
		}
		return true;
		} catch( NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean validatePhoneNumber(String phoneNumber) {
		return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber);
	}
	
	public static boolean validateBirthDate(LocalDate birthDate) {
		if(LocalDate.now().minusYears(AGE_LIMIT).isAfter(birthDate) || LocalDate.now().minusYears(AGE_LIMIT).isEqual(birthDate)) {
			return true;
		}
		return false;
	}
	
	public static boolean validateDocumentTerm(LocalDate term) {
		return LocalDate.now().isBefore(term);
	}
	
	public static boolean validateUserRegisterParameters(User user) {
		if(validateEmail(user.getEmail()) &&
		validatePersonName(user.getName()) &&
		validatePersonName(user.getLastname()) &&
		validateBirthDate(user.getBirthdate()) &&
		validateDocumentTerm(user.getPassportTerm()) &&
		validateDocumentTerm(user.getLicenceTerm()) &&
		validatePhoneNumber(user.getPhoneNumber())) {
			return true;
		}
		return false;
	}
}
