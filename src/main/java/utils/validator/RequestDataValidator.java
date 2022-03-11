package utils.validator;

import java.time.LocalDate;

public class RequestDataValidator {
	
	public static boolean validateRentPeriod(LocalDate startDate, LocalDate finishDate) {
		if (finishDate.isAfter(startDate) && startDate.isAfter(LocalDate.now())) {
			return true;
		} else {
			return false;
		}
	}

}
