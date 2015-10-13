package services;

import DAO.OrderDAO;
import DAO.OrderLineDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import models.Guest;
import models.Order;
import models.OrderLine;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
            addEmptyLine(title, 2);
            preface.add(title);

            // Guest info
            Paragraph guestInfo = new Paragraph("", this.fontHelveticaNormal);
            guestInfo.add(getFullName(order.getGuest()));
            guestInfo.add(new Paragraph(order.getGuest().getStreet() + " " + order.getGuest().getStreetnr(), this.fontHelveticaNormal));
            guestInfo.add(new Paragraph(order.getGuest().getZipcode() + " " + order.getGuest().getCity(), this.fontHelveticaNormal));
            addEmptyLine(guestInfo, 2);
            preface.add(guestInfo);


            // Order details
            PdfPTable orderDetailsTable = new PdfPTable(3);
            orderDetailsTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            orderDetailsTable.setWidthPercentage(100);
            float[] columnWidthOrdertable = {2f, 0.5f, 2f};
            orderDetailsTable.setWidths(columnWidthOrdertable);
            orderDetailsTable.addCell(new Paragraph("Factuurdatum", this.fontHelveticaNormal));
            orderDetailsTable.addCell(new Paragraph(":", this.fontHelveticaNormal));
            orderDetailsTable.addCell(new Paragraph(new SimpleDateFormat("dd MM YYYY").format(order.getDate()), this.fontHelveticaNormal));

            orderDetailsTable.addCell(new Paragraph("Factuurnummer", this.fontHelveticaNormal));
            orderDetailsTable.addCell(new Paragraph(":", this.fontHelveticaNormal));
            orderDetailsTable.addCell(new Paragraph(Integer.toString(order.getId()), this.fontHelveticaNormal));

            orderDetailsTable.addCell(new Paragraph("Debiteurennummer", this.fontHelveticaNormal));
            orderDetailsTable.addCell(new Paragraph(":", this.fontHelveticaNormal));
            orderDetailsTable.addCell(new Paragraph(Integer.toString(order.getGuest().getId()), this.fontHelveticaNormal));
            preface.add(orderDetailsTable);

//            Paragraph orderDetails = new Paragraph("", this.fontHelveticaNormal);
//            orderDetails.add("Factuurdatum : " + new SimpleDateFormat("dd MM YYYY").format(order.getDate()));
//            orderDetails.add(new Paragraph("Factuurnummer : " + Integer.toString(order.getId()), this.fontHelveticaNormal));
//            orderDetails.add(new Paragraph("Debiteurennummer : " + Integer.toString(order.getGuest().getId()), this.fontHelveticaNormal));
//            addEmptyLine(orderDetails, 2);
//            preface.add(orderDetails);

            PdfPTable orderTable = new PdfPTable(6); // 6 col
            orderTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            orderTable.setWidthPercentage(100);
            float[] columnWidthOrderTable = {0.5f, 0.5f, 3f, 0.5f, 0.5f, 0.5f};
            orderTable.setWidths(columnWidthOrderTable);
            orderTable.addCell((new Paragraph("Code", this.fontHelveticaNormalBold)));
            orderTable.addCell((new Paragraph("Aantal", this.fontHelveticaNormalBold)));
            orderTable.addCell((new Paragraph("Wijn", this.fontHelveticaNormalBold)));
            orderTable.addCell((new Paragraph("Jaar", this.fontHelveticaNormalBold)));
            orderTable.addCell((new Paragraph("Per fles", this.fontHelveticaNormalBold)));
            orderTable.addCell((new Paragraph("Bedrag", this.fontHelveticaNormalBold)));

            ArrayList<OrderLine> tempOrderLines = orderDAO.findOrderlinesByOrder(order);
            for(int i = 0; i < tempOrderLines.size(); i++) {
                orderTable.addCell((new Paragraph(Integer.toString(tempOrderLines.get(i).getWine().getId()))));
                orderTable.addCell((new Paragraph(Integer.toString(tempOrderLines.get(i).getAmount()))));
                orderTable.addCell((new Paragraph(tempOrderLines.get(i).getWine().getName())));
                orderTable.addCell((new Paragraph(tempOrderLines.get(i).getWine().getYear())));
                orderTable.addCell((new Paragraph(tempOrderLines.get(i).getWine().getPrice().toString())));
                orderTable.addCell((new Paragraph(Integer.toString(tempOrderLines.get(i).getAmount()))));
            }
            preface.add(orderTable);

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
