package controllers;

import DAO.OrderDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import models.Order;
import models.Pdf;
import services.PDFService;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.annotation.Documented;
import java.util.Date;

/**
 * Created by sander on 8-10-2015.
 */
public class PdfController {
    private OrderDAO orderDAO;
    private Pdf pdf;
    private Document document;
    private File out = new File(System.getProperty("user.home") + "/Wijnfestijn/factuur/");
    private Font fontHelvetica = new Font(Font.FontFamily.HELVETICA, 17, Font.NORMAL);

    public PdfController(OrderDAO orderDAO, Pdf pdf) {
        this. orderDAO = orderDAO;
        this.pdf = pdf;
    }

    public void createOrderPdf() {
        try {
            this.document = new Document();
            checkDirectory(this.out);
            PdfWriter.getInstance(this.document, new FileOutputStream(out.toString() + "/test.pdf"));
            document.open();
            addMetaData(document);

            Paragraph preface = new Paragraph();
            // Titel toevoegen

            Paragraph title = new Paragraph("Lionsclub Oegstgeest/Warmond", this.fontHelvetica);
            title.setAlignment(Element.ALIGN_CENTER);
            addEmptyLine(preface, 1);
            preface.add(title);

            addEmptyLine(preface, 2);
            preface.add(new Paragraph("S. de Jong"));
            preface.add(new Paragraph("Burggravenambacht 13"));
            preface.add(new Paragraph("1433NR Kudelstaart"));

            addEmptyLine(preface, 2);

            Paragraph orderDetails = new Paragraph();
            orderDetails.add("Factuurdatum");
            orderDetails.add(Chunk.TABBING);
            orderDetails.add(":");
            orderDetails.add(Chunk.TABBING);
            orderDetails.add("23 Septebmer 2013");

            orderDetails.add(Chunk.NEWLINE);

            orderDetails.add("Factuurnummer");
            orderDetails.add(Chunk.TABBING);
            orderDetails.add(":");
            orderDetails.add(Chunk.TABBING);
            orderDetails.add("10001");

            orderDetails.add(Chunk.NEWLINE);

            orderDetails.add("Debiteurennummer");
            orderDetails.add(Chunk.TABBING);
            orderDetails.add(":");
            orderDetails.add(Chunk.TABBING);
            orderDetails.add("10001");

            preface.add(orderDetails);


            document.add(preface);
            document.newPage();
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMetaData(Document document) {
        document.addSubject(this.pdf.getSubject());
        document.addKeywords(this.pdf.getKeywords());
        document.addCreator(this.pdf.getCreator());
        document.addAuthor(this.pdf.getAuthor());
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
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
}