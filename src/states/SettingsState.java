package states;

import java.io.IOException;

/**
 * @author Dennis Sloove, Michael van Kampen
 * @version 0.1, november 2015
 *          Description:
 *          Interface classes to handle different mail templates
 */

public interface SettingsState {


    /**
     * <P>Retrieves the content of the selected file and loads it
     * into a variable</P>
     */
    public void fileToVariable();

    /**
     * @param fileName Location of the file
     * @return String with file content
     * @throws IOException Signals that an I/O exception of some sort has occurred. This
     *                     class is the general class of exceptions produced by failed or
     *                     interrupted I/O operations.
     */
    public String readFile(String fileName) throws IOException;

    /**
     * @return String with the body of a template
     */
    public String getBody();

    /**
     * @return String with the title of a template
     */
    public String getTitle();

    /**
     * <P>Write content of the variable to a file</P>
     */
    public void writeToFile();

    /**
     * @param add contains new input for the body
     */
    public void updateBody(String add);

    /**
     * @param add contains new input for the title
     */
    public void updateTitle(String add);

    /**
     * @return Default Body
     */
    public String getDefaultBody();

    /**
     * @return Default Title
     */
    public String getDefaultTitle();

    /**
     * <P>Default location for the templates</P>
     */
    public final String DEFAULTPATH = "src/templates/";
}
