package controllers;

import DAO.OrderDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by sander on 8-10-2015.
 */
public class PdfController {


//
//    public PdfController(OrderDAO orderDAO) {
//        this. orderDAO = orderDAO;
//    }
//
//    public void createOrderPdf() {
//        try {
//            this.document = new Document();
//            checkDirectory(this.out);
//            PdfWriter.getInstance(this.document, new FileOutputStream(out.toString() + "/test.pdf"));
//            Paragraph preface = new Paragraph();
//            document.open();
//            addBody(document, preface);
//
//
//
//
//
//            document.add(preface);
//            document.newPage();
//            document.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void addBody(Document document, Paragraph preface) {
//
//        // Titel toevoegen
//
//        Paragraph title = new Paragraph("Lionsclub Oegstgeest/Warmond", this.fontHelveticaHeader);
//        title.setAlignment(Element.ALIGN_CENTER);
//        addEmptyLine(preface, 1);
//        preface.add(title);
//
//        addEmptyLine(preface, 2);
//        preface.add(new Paragraph("S. de Jong"));
//        preface.add(new Paragraph("Burggravenambacht 13"));
//        preface.add(new Paragraph("1433NR Kudelstaart"));
//
//        addEmptyLine(preface, 2);
//
//        Paragraph orderDetails = new Paragraph("", this.fontHelveticaNormal);
//        orderDetails.add("Factuurdatum");
//        addTab(orderDetails, 2);
//        orderDetails.add(":");
//        addTab(orderDetails, 1);
//        orderDetails.add("23 Septebmer 2013");
//
//        addNewLine(orderDetails);
//
//        orderDetails.add("Factuurnummer");
//        addTab(orderDetails, 2);
//        orderDetails.add(":");
//        addTab(orderDetails, 1);
//        orderDetails.add("10001");
//
//        addNewLine(orderDetails);
//
//        orderDetails.add("Debiteurennummer");
//        addTab(orderDetails, 1);
//        orderDetails.add(":");
//        addTab(orderDetails, 1);
//        orderDetails.add("10001");
//        addEmptyLine(orderDetails, 2);
//
//        Paragraph orderLines = new Paragraph("", this.fontHelveticaNormalBold);
//        orderLines.add("Code");
//        addTab(orderLines, 1);
//        orderLines.add("Aantal");
//        addTab(orderLines, 1);
//        orderLines.add("Wijn");
//        addTab(orderLines, 6);
//        orderLines.add("Jaar");
//        addTab(orderLines, 2);
//        orderLines.add("Per fles");
//        addTab(orderLines, 2);
//        orderLines.add("Bedrag");
//        addTab(orderLines, 2);
//        LineSeparator ls = new LineSeparator();
//        orderLines.add(ls);
//
//        preface.add(orderDetails);
//        preface.add(orderLines);
//        addEmptyLine(orderDetails, 1);

    //}

    private void addTab(Paragraph paragraph, int number) {
        for(int i = 0; i < number; i++) {
            paragraph.add(Chunk.TABBING);
        }
    }

    private void addNewLine(Paragraph paragraph) {
        paragraph.add(Chunk.NEWLINE);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void checkDirectory(File path) {
        if (!path.exists()) {
            try {
                path.mkdirs();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}