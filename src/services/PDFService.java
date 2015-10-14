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
import java.math.BigDecimal;
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

    public File createOrderPdf(Order order, OrderDAO orderDAO) {
        Document document = new Document();
        try {
            checkDirectory(this.out);
            PdfWriter.getInstance(document, new FileOutputStream(out.toString() + "/" + order.getId() + ".pdf"));
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
            float[] columnWidthOrdertable = {0.6f, 0.2f, 1.5f};
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

            orderDetailsTable.addCell(new Paragraph("Betreft", this.fontHelveticaNormal));
            orderDetailsTable.addCell(new Paragraph(":", this.fontHelveticaNormal));
            orderDetailsTable.addCell(new Paragraph("Wijnfestijn", this.fontHelveticaNormal));
            preface.add(orderDetailsTable);
            addEmptyLine(preface, 2);

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

            double total = 0;
            ArrayList<OrderLine> tempOrderLines = orderDAO.findOrderlinesByOrder(order);
            for(int i = 0; i < tempOrderLines.size(); i++) {
                orderTable.addCell(new Paragraph(Integer.toString(tempOrderLines.get(i).getWine().getId()), this.fontHelveticaNormal));
                orderTable.addCell(new Paragraph(Integer.toString(tempOrderLines.get(i).getAmount()), this.fontHelveticaNormal));
                orderTable.addCell(new Paragraph(tempOrderLines.get(i).getWine().getName(), this.fontHelveticaNormal));
                orderTable.addCell(new Paragraph(tempOrderLines.get(i).getWine().getYear(), this.fontHelveticaNormal));
                orderTable.addCell(new Paragraph(tempOrderLines.get(i).getWine().getPrice().toString(), this.fontHelveticaNormal));
                double a = (tempOrderLines.get(i).getAmount() * tempOrderLines.get(i).getWine().getPrice());
                total += a;
                orderTable.addCell(new Paragraph(Double.toString(a), this.fontHelveticaNormal));
            }
            orderTable.addCell(new Paragraph("Totaal", this.fontHelveticaNormalBold));
            orderTable.addCell(new Paragraph(""));
            orderTable.addCell(new Paragraph(""));
            orderTable.addCell(new Paragraph(""));
            orderTable.addCell(new Paragraph(""));
            PdfPCell c = new PdfPCell(new Paragraph(Double.toString(total), this.fontHelveticaNormalBold));
            c.setBorder(Rectangle.TOP);
            orderTable.addCell(c);
            preface.add(orderTable);

            addEmptyLine(preface, 4);
            Paragraph requestMessage = new Paragraph("Wij verzoeken u vriendelijk het totaalbedrag binnen 7 dagenna " +
                    "factuurdatum over te maken op bankrekening 123456 t.n.v. Lionsclub Oegstgeest/Warmond onder " +
                    "vermelding van het factuurnummer.", this.fontHelveticaNormal);
            addEmptyLine(requestMessage, 4);
            preface.add(requestMessage);

            Paragraph timeMessage = new Paragraph("U kunt uw bestelde wijnen ophalen op zaterdag 28 september tussen " +
                    "12:00 en 16:00.", this.fontHelveticaNormal);
            addEmptyLine(timeMessage, 1);
            preface.add(timeMessage);

            Paragraph address = new Paragraph("", this.fontHelveticaNormal);
            address.add(new Paragraph("Adres:", this.fontHelveticaNormal));
            address.add(new Paragraph("Noordman Wijnimport", this.fontHelveticaNormal));
            address.add(new Paragraph("Flevoweg 17", this.fontHelveticaNormal));
            address.add(new Paragraph("2318 BZ Leidenn", this.fontHelveticaNormal));
            addEmptyLine(address, 3);
            address.add(new LineSeparator());
            addEmptyLine(address, 1);
            address.add(new Paragraph("Lionsclub Oegstgeest/Warmond", this.fontHelveticaNormal));
            //address.add(new Chunk(Chunk.NEWLINE));
            preface.add(address);

            PdfPTable bankTable = new PdfPTable(3);
            bankTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            bankTable.setWidthPercentage(101);
            bankTable.setWidths(columnWidthOrdertable);
            bankTable.addCell(new Paragraph("Bankrekening", this.fontHelveticaNormal));
            bankTable.addCell(new Paragraph(":", this.fontHelveticaNormal));
            bankTable.addCell(new Paragraph("123456", this.fontHelveticaNormal));

            bankTable.addCell(new Paragraph("Inschrijfnummer KvK Rijnland", this.fontHelveticaNormal));
            bankTable.addCell(new Paragraph(":", this.fontHelveticaNormal));
            bankTable.addCell(new Paragraph("987654321", this.fontHelveticaNormal));
            preface.add(bankTable);


            // Add every paragraph to document
            document.add(preface);
            document.newPage();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(out.toString() + "/" + order.getId() + ".pdf");
    }

    public String getFullName(Guest guest) {
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

    private String calcTotal() {
        return null;
    }
}