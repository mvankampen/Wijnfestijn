package states;

import services.Util;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dennis Sloove
 * @author Michael van Kampen
 * @version 0.1, november 2015
 *          Description:
 *          SettingsStateOpenOrder implements the SettingsState, treated the Open Order mail.
 */

public class SettingsStateOpenOrder implements SettingsState {

    private String returnBody, returnTitle, defaultBody = "", defaultTitle = "", pathToFile;

    /**
     * <P>Default Constructor</P>
     */
    public SettingsStateOpenOrder() {
        fileToVariable();
    }

    /**
     * <P>Retrieves the content of the selected file and loads it
     * into a variable</P>
     */
    @Override
    public void fileToVariable() {
        String line;
        //defaultPath from SettingsState class
        try {
            returnBody = "";
            returnTitle = "";

            Pattern pattern = Pattern.compile("<title>(.+?)</title>");
            Matcher matcher = pattern.matcher(new Util().getTxtFileFromResource("OPENORDER.html"));
            matcher.find();

            returnTitle = matcher.group(1);
            defaultTitle = matcher.group(1);

            returnBody += new Util().getTxtFileFromResource("OPENORDER.html");
            defaultBody += new Util().getTxtFileFromResource("OPENORDER.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName Location of the file
     * @return String with file content
     * @throws IOException Signals that an I/O exception of some sort has occurred. This
     *                     class is the general class of exceptions produced by failed or
     *                     interrupted I/O operations.
     */
    @Override
    public String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    /**
     * @return String with the body of a template
     */
    @Override
    public String getBody() {
        return this.returnBody;
    }

    /**
     * @return String with the title of a template
     */
    @Override
    public String getTitle() {
        return this.returnTitle;
    }

    /**
     * <P>Write content of the variable to a file</P>
     */
    @Override
    public void writeToFile() {
        try {
            Pattern pattern = Pattern.compile("<title>(.+?)</title>");
            Matcher matcher = pattern.matcher(readFile(pathToFile));
            matcher.find();
            returnBody = returnBody.replace(matcher.group(1), returnTitle);

            // Create a new PrintWriter
            PrintWriter pw = new PrintWriter(pathToFile);
            pw.close();
            // Re-initialize pw
            pw = new PrintWriter(new BufferedWriter(new FileWriter(pathToFile, true)));
            pw.write(returnBody);
            // Closing the PrintWriter
            pw.close();
            defaultBody = returnBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param add contains new input for the body
     */
    @Override
    public void updateBody(String add) {
        this.returnBody = add;
    }

    /**
     * @param add contains new input for the title
     */
    @Override
    public void updateTitle(String add) {
        this.returnTitle = add;
    }

    /**
     * @return Default Body
     */
    @Override
    public String getDefaultBody() {
        return this.defaultBody;
    }

    /**
     * @return Default Title
     */
    @Override
    public String getDefaultTitle() {
        return this.defaultTitle;
    }
}
