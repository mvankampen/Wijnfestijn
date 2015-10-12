package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Sander de Jong on 24-9-2015.
 */
public class PDFService {
    private Document document;
    private File out = new File(System.getProperty("user.home") + "/Wijnfestijn/factuur/");

    public PDFService(Document document) {
        this.document = document;
    }

    public void writePDF() {
        checkDirectory(this.out);

        try {
            PdfWriter.getInstance(this.document, new FileOutputStream(out.toString() + "/test.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkDirectory(File path) {
        if (!path.exists()) {
            try {
                path.mkdirs();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    public Document getDocument() {
        return this.document;
    }
}