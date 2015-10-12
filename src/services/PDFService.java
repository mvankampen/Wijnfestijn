package services;

import DAO.OrderDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import models.Guest;
import models.Order;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
            document.open();

            // Main paragraph
            Paragraph preface = new Paragraph();

            // Title
            Paragraph title = new Paragraph("Lionsclub Oegstgeest/Warmond", this.fontHelveticaHeader);
            title.setAlignment(Element.ALIGN_CENTER);
            preface.add(title);

            // Guest info
            Paragraph guestInfo = new Paragraph("", this.fontHelveticaNormal);
            guestInfo.add(getFullName(order.getGuest()));
            guestInfo.add(new Paragraph(order.getGuest().getStreetname() + " " + order.getGuest().getStreetnr(), this.fontHelveticaNormal));
            guestInfo.add(new Paragraph(order.getGuest().getZipcode() + " " + order.getGuest().getCity(), this.fontHelveticaNormal));
            addEmptyLine(preface, 4);
            preface.add(guestInfo);

            // Order details
            Paragraph orderDetails = new Paragraph("", this.fontHelveticaNormal);
            orderDetails.add("Factuurdatum");
            addTab(orderDetails, 2);
            orderDetails.add(":");
            addTab(orderDetails, 1);
            orderDetails.add(new SimpleDateFormat("dd MM YYYY").format(order.getDate()));
            orderDetails.add(new Paragraph("Factuurnummer", this.fontHelveticaNormal));
            addTab(orderDetails, 2);
            orderDetails.add(":");
            addTab(orderDetails, 1);
            orderDetails.add(Integer.toString(order.getId()));
            orderDetails.add(new Paragraph("Debiteurennummer", this.fontHelveticaNormal));
            addTab(orderDetails, 1);
            orderDetails.add(":");
            addTab(orderDetails, 1);
            orderDetails.add(Integer.toString(order.getGuest().getId()));
            addEmptyLine(orderDetails, 2);
            preface.add(orderDetails);

            // Order lines
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
            preface.add(orderLines);

            // Add every paragraph to document
            document.add(preface);
            document.newPage();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFullName(Guest guest) {
        String firstNameShort = guest.getFirstname().substring(0,1).toUpperCase() + ". ";
        String result = firstNameShort;
        if (!guest.getInfix().equals(null)) {
         result += guest.getInfix() + " " + guest.getSurname();
        } else {
            result += guest.getSurname();
        }
        return result;
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