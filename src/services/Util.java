package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <p>utilty class for a hotfix for different utilities</p>
 */
public class Util {
    public String getTxtFileFromResource(String fileName) {
        String text = null;

        InputStream in = getClass().getResourceAsStream("/templates/" + fileName);
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        try {
            StringBuilder builder = new StringBuilder();
            String aux = "";

            while ((aux = input.readLine()) != null) {
                builder.append(aux + System.getProperty("line.separator"));
            }
            text = builder.toString();
            input.close();
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}
