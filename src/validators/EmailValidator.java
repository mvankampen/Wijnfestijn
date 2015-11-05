package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>This class is used to validate different email addresses.</p>
 *
 * @author Alex van der Wal
 * @version 0.1, november 2015
 */
public class EmailValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * <p>Creates the pattern that is used in the matcher to validate the email.</p>
     */
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * <p>Validate hex with regular expression</p>
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */

    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
