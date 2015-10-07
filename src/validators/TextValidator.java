package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {
	private Pattern pattern;
	private Matcher matcher;

	private static final String FILLED_PATTERN = 
			"\\A\\S+\\Z";

	public TextValidator() {
	    pattern = Pattern.compile(FILLED_PATTERN);
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
