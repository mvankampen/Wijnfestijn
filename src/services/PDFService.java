package services;

import DAO.OrderDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import models.Order;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Sander de Jong on 24-9-2015.
 */
public class PDFService {
    private File out = new File(System.getProperty("user.home") + "/Wijnfestijn/factuur/");
    private Font fontHelveticaHeader = new Font(Font.FontFamily.HELVETICA, 17, Font.NORMAL);
    private Font fontHelveticaNormalBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private Font fontHelveticaNormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

    public void createOrderPdf(Order order, OrderDAO orderDAO) {
        Document document = new Document();
        try {
            checkDirectory(this.out);
            PdfWriter.getInstance(document, new FileOutputStream(out.toString() + "/test.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.open();
        // Main paragraph
        Paragraph preface = new Paragraph();

        // Title
        Paragraph title = new Paragraph("Lionsclub Oegstgeest/Warmond", this.fontHelveticaHeader);
        title.setAlignment(Element.ALIGN_CENTER);

        addEmptyLine(preface, 3);
        preface.add(new Paragraph("S. de Jong"));
        preface.add(new Paragraph("Burggravenambacht 13"));
        preface.add(new Paragraph("1433NR Kudelstaart"));
        preface.add(title);

        addEmptyLine(preface, 2);

        Paragraph orderDetails = new Paragraph("", this.fontHelveticaNormal);
        orderDetails.add("Factuurdatum");
        addTab(orderDetails, 2);
        orderDetails.add(":");
        addTab(orderDetails, 1);
        orderDetails.add("23 Septebmer 2013");

        //addNewLine(orderDetails);

        orderDetails.add("Factuurnummer");
        addTab(orderDetails, 2);
        orderDetails.add(":");
        addTab(orderDetails, 1);
        orderDetails.add("10001");

        //addNewLine(orderDetails);

        orderDetails.add("Debiteurennummer");
        addTab(orderDetails, 1);
        orderDetails.add(":");
        addTab(orderDetails, 1);
        orderDetails.add("10001");
        addEmptyLine(orderDetails, 2);

        Paragraph orderLines = new Paragraph("", this.fontHelveticaNormalBold);
        orderLines.add("Code");
        addTab(orderLines, 1);
        orderLines.add("Aantal");
        addTab(orderLines, 1);
        orderLines.add("Wijn");
        addTab(orderLines, 6);
        orderLines.add("Jaar");
        addTab(orderLines, 2);
        orderLines.add("Per fles");
        addTab(orderLines, 2);
        orderLines.add("Bedrag");
        addTab(orderLines, 2);
        LineSeparator ls = new LineSeparator();
        orderLines.add(ls);

        preface.add(orderDetails);
        preface.add(orderLines);
        addEmptyLine(orderDetails, 1);

    }

    public void createCustomOrderList() {

    }

    private boolean checkDirectory(File path) {
        if (!path.exists()) {
            try {
                return path.mkdirs();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void addTab(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(Chunk.TABBING);
        }
    }
}