package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipcodeValidator {
	private Pattern pattern;
	private Matcher matcher;

	private static final String ZIPCODE_PATTERN = 
	    "^[0-9]{4}[A-Za-z]{2}";

	public ZipcodeValidator() {
	    pattern = Pattern.compile(ZIPCODE_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

	    matcher = pattern.matcher(hex);
	    return matcher.matches();

	}
}
