package services;

import DAO.GuestDAO;
import DAO.OrderDAO;
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
import models.Wine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>This class is used for different PDF tasks. The main goal of this class is to generate PDF files based on
 * given input. This class used an external jar called itextpdf-5.5.7.</p>
 *
 * @author Sander de Jong
 * @author Patrick van der Plas
 * @version 0.1, november 2015
 */
public class PDFService {
    private File factuurOut = new File(System.getProperty("user.home") + "/Wijnfestijn/factuur/");
    private Font fontHelveticaHeader = new Font(Font.FontFamily.HELVETICA, 17, Font.NORMAL);
    private Font fontHelveticaNormalBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private Font fontHelveticaNormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

    /**
     * <p>Creates a PDF based on a given order. The order is being looked up in the database to get all the orderlines.
     * For each {@link OrderLine} the PDF API inserts it into the document along with other information about the order.</p>
     *
     * @param order    An order object of which the PDF will be created.
     * @param orderDAO The order Data Access Object that will be used to look up the orderlines of a specific order.
     * @return Returns a file object that can be used to write the PDF data to a disk.
     */
    public File createOrderPdf(Order order, OrderDAO orderDAO) {
        Document document = new Document();
        try {
            checkDirectory(this.factuurOut);
            PdfWriter.getInstance(document, new FileOutputStream(factuurOut.toString() + "/" + order.getId() + ".pdf"));
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
            orderDetailsTable.addCell(new Paragraph(new SimpleDateFormat("dd-MM-YYYY").format(order.getDate()), this.fontHelveticaNormal));

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
            String euro = "\u20ac";
            ArrayList<OrderLine> tempOrderLines = orderDAO.findOrderlinesByOrder(order);
            for (int i = 0; i < tempOrderLines.size(); i++) {
                orderTable.addCell(new Paragraph(Integer.toString(tempOrderLines.get(i).getWine().getId()), this.fontHelveticaNormal));
                orderTable.addCell(new Paragraph(Integer.toString(tempOrderLines.get(i).getAmount()), this.fontHelveticaNormal));
                orderTable.addCell(new Paragraph(tempOrderLines.get(i).getWine().getName(), this.fontHelveticaNormal));
                orderTable.addCell(new Paragraph(tempOrderLines.get(i).getWine().getYear(), this.fontHelveticaNormal));
                double price = tempOrderLines.get(i).getWine().getPrice();
                String formattedPrice = String.format("%.02f", price);
                orderTable.addCell(new Paragraph(euro + formattedPrice, this.fontHelveticaNormal));
                double a = (tempOrderLines.get(i).getAmount() * tempOrderLines.get(i).getWine().getPrice());
                total += a;
                BigDecimal roundedBigDecimalPrice = BigDecimal.valueOf(a).setScale(2, RoundingMode.HALF_UP);
                double roundedDoublePrice = roundedBigDecimalPrice.doubleValue();
                String formattedTotalPrice = String.format("%.02f", roundedDoublePrice);
                orderTable.addCell(new Paragraph(euro + formattedTotalPrice, this.fontHelveticaNormal));
            }
            orderTable.addCell(new Paragraph("Totaal", this.fontHelveticaNormalBold));
            orderTable.addCell(new Paragraph(""));
            orderTable.addCell(new Paragraph(""));
            orderTable.addCell(new Paragraph(""));
            orderTable.addCell(new Paragraph(""));
            BigDecimal roundedBigDecimalTotalPrice = BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP);
            double roundedDoubleTotalPrice = roundedBigDecimalTotalPrice.doubleValue();
            String formattedTotalPrice = String.format("%.02f", roundedDoubleTotalPrice);
            PdfPCell c = new PdfPCell(new Paragraph(euro + formattedTotalPrice, this.fontHelveticaNormalBold));
            c.setBorder(Rectangle.TOP);
            orderTable.addCell(c);
            preface.add(orderTable);

            addEmptyLine(preface, 4);
            Paragraph requestMessage = new Paragraph("Wij verzoeken u vriendelijk het totaalbedrag binnen 7 dagen na " +
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
            address.add(new Paragraph("2318BZ Leiden", this.fontHelveticaNormal));
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
        return new File(factuurOut.toString() + "/" + order.getId() + ".pdf");
    }

    /**
     * <p>Gets the full name as a String from a {@link Guest} object. This method is needed because some geusts
     * have infixes in their name and some don't.</p>
     *
     * @param guest A guest object that represents a guest.
     * @return Returns a String that can be used as the full name of a guest.
     */
    public String getFullName(Guest guest) {
        String firstNameShort = guest.getFirstname().substring(0, 1).toUpperCase() + ". ";
        String result = firstNameShort;
        if (!guest.getInfix().equals(null)) {
            result += guest.getInfix() + " " + guest.getSurname();
        } else {
            result += guest.getSurname();
        }
        return result;
    }

    /**
     * <p></p>Creates an orderlist PDF file based on a {@link Guest}. In this orderlist there will be guest information
     * displayed</p>
     *
     * @param file     This is the file that the PDF will be written to.
     * @param guestDAO The Data Acces Object that will be used to retrieve the guest information from the database.
     * @param wineList The winelist that will be used in the orderlist.
     */
    private void createCustomOrderList(File file, GuestDAO guestDAO, ArrayList<Wine> wineList) {
        try {
            ArrayList<Guest> guests = guestDAO.getAllGuest();
            for (Guest guest : guests) {
                Document document = new Document();
                String newFileName = "";
                String guestName = "";
                if (guest.getInfix().isEmpty()) {
                    newFileName = String.valueOf(guest.getId() + "_" + guest.getSurname());
                    guestName = guest.getFirstname() + " " + guest.getSurname();
                } else {
                    newFileName = String.valueOf(guest.getId() + "_" + guest.getInfix() + " " + guest.getSurname());
                    guestName = guest.getFirstname() + " " + guest.getInfix() + " " + guest.getSurname();
                }
                PdfWriter.getInstance(document, new FileOutputStream(file.toString() + "/" + newFileName + ".pdf"));
                document.open();

                // Main paragraph
                Paragraph preface = new Paragraph();

                // Title
                Paragraph title = new Paragraph("Lionsclub Oegstgeest/Warmond", this.fontHelveticaNormalBold);
                addEmptyLine(title, 2);
                preface.add(title);

                // Main table
                PdfPTable guestInfo = new PdfPTable(2);
                guestInfo.setHorizontalAlignment(Element.ALIGN_LEFT);
                guestInfo.setWidthPercentage(100);
                float[] columnWidthGuestTable = {0.5f, 1.5f};
                guestInfo.setWidths(columnWidthGuestTable);
                guestInfo.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                guestInfo.addCell(new Paragraph("Naam: ", this.fontHelveticaNormal));
                guestInfo.addCell(new Paragraph(guestName, this.fontHelveticaNormal));
                guestInfo.addCell(new Paragraph("Adres:", this.fontHelveticaNormal));
                guestInfo.addCell(new Paragraph(guest.getStreet() + " " + guest.getStreetnr() + ", " + guest.getZipcode() + " " + guest.getCity(), this.fontHelveticaNormal));
                guestInfo.addCell(new Paragraph("Email:", this.fontHelveticaNormal));
                guestInfo.addCell(new Paragraph(guest.getEmail(), this.fontHelveticaNormal));
                preface.add(guestInfo);
                addEmptyLine(preface, 2);

                // Main table
                PdfPTable orderListTable = new PdfPTable(9);
                orderListTable.setWidthPercentage(100);
                float[] columnWidthOrdertable = {0.4f, 0.5f, 0.6f, 1.3f, 0.6f, 0.4f, 0.4f, 0.4f, 0.4f};
                orderListTable.setWidths(columnWidthOrdertable);

                orderListTable.addCell(new Paragraph("Nr.", this.fontHelveticaNormalBold));
                orderListTable.addCell(new Paragraph("Aantal dozen", this.fontHelveticaNormalBold));
                orderListTable.addCell(new Paragraph("", this.fontHelveticaNormalBold));
                orderListTable.addCell(new Paragraph("", this.fontHelveticaNormalBold));
                orderListTable.addCell(new Paragraph("", this.fontHelveticaNormalBold));
                orderListTable.addCell(new Paragraph("Jaar", this.fontHelveticaNormalBold));
                orderListTable.addCell(new Paragraph("Prijs fles", this.fontHelveticaNormalBold));
                orderListTable.addCell(new Paragraph("Prijs doos", this.fontHelveticaNormalBold));
                orderListTable.addCell(new Paragraph("Rang", this.fontHelveticaNormalBold));

                for (Wine w : wineList) {
                    orderListTable.addCell(new Paragraph(String.valueOf(w.getId()), this.fontHelveticaNormal));
                    orderListTable.addCell(new Paragraph("", this.fontHelveticaNormal));
                    orderListTable.addCell(new Paragraph(w.getType(), this.fontHelveticaNormal));
                    orderListTable.addCell(new Paragraph(w.getName(), this.fontHelveticaNormal));
                    orderListTable.addCell(new Paragraph(w.getCategory(), this.fontHelveticaNormal));
                    orderListTable.addCell(new Paragraph(w.getYear(), this.fontHelveticaNormal));
                    orderListTable.addCell(new Paragraph(w.getCostprice().toString(), this.fontHelveticaNormal));
                    orderListTable.addCell(new Paragraph(w.getPrice().toString(), this.fontHelveticaNormal));
                    orderListTable.addCell(new Paragraph(w.getRank(), this.fontHelveticaNormal));
                }

                preface.add(orderListTable);

                // Add pages to document
                document.add(preface);
                document.newPage();
                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Creates an blank orderlist file with no specific information about a guest. </p>
     *
     * @param wineList The winelist that will be used in the orderlist
     * @param fileName The filename that will be used as a directory name for all the orderlists.
     * @param guestDAO The Data Acces Object that will provide the guest information where needed.
     */
    public void createOrderList(ArrayList<Wine> wineList, String fileName, GuestDAO guestDAO) {
        Document document = new Document();
        try {
            String newFileName;
            if (fileName.isEmpty()) {
                newFileName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            } else {
                newFileName = fileName;
            }
            File orderListOut = new File(System.getProperty("user.home") + "/Wijnfestijn/bestellijst/" + newFileName);
            checkDirectory(orderListOut);
            PdfWriter.getInstance(document, new FileOutputStream(orderListOut.toString() + "/" + "_AlgemeneBestellijst.pdf"));
            document.open();

            // Main paragraph
            Paragraph preface = new Paragraph();

            // Main table
            PdfPTable orderListTable = new PdfPTable(9);
            orderListTable.setWidthPercentage(100);
            float[] columnWidthOrdertable = {0.4f, 0.5f, 0.6f, 1.3f, 0.6f, 0.4f, 0.4f, 0.4f, 0.4f};
            orderListTable.setWidths(columnWidthOrdertable);

            orderListTable.addCell(new Paragraph("Nr.", this.fontHelveticaNormalBold));
            orderListTable.addCell(new Paragraph("Aantal dozen", this.fontHelveticaNormalBold));
            orderListTable.addCell(new Paragraph("", this.fontHelveticaNormalBold));
            orderListTable.addCell(new Paragraph("", this.fontHelveticaNormalBold));
            orderListTable.addCell(new Paragraph("", this.fontHelveticaNormalBold));
            orderListTable.addCell(new Paragraph("Jaar", this.fontHelveticaNormalBold));
            orderListTable.addCell(new Paragraph("Prijs fles", this.fontHelveticaNormalBold));
            orderListTable.addCell(new Paragraph("Prijs doos", this.fontHelveticaNormalBold));
            orderListTable.addCell(new Paragraph("Rang", this.fontHelveticaNormalBold));

            for (Wine w : wineList) {
                orderListTable.addCell(new Paragraph(String.valueOf(w.getId()), this.fontHelveticaNormal));
                orderListTable.addCell(new Paragraph("", this.fontHelveticaNormal));
                orderListTable.addCell(new Paragraph(w.getType(), this.fontHelveticaNormal));
                orderListTable.addCell(new Paragraph(w.getName(), this.fontHelveticaNormal));
                orderListTable.addCell(new Paragraph(w.getCategory(), this.fontHelveticaNormal));
                orderListTable.addCell(new Paragraph(w.getYear(), this.fontHelveticaNormal));
                orderListTable.addCell(new Paragraph(w.getCostprice().toString(), this.fontHelveticaNormal));
                orderListTable.addCell(new Paragraph(w.getPrice().toString(), this.fontHelveticaNormal));
                orderListTable.addCell(new Paragraph(w.getRank(), this.fontHelveticaNormal));
            }

            preface.add(orderListTable);


            // Add pages to document
            document.add(preface);
            document.newPage();
            document.close();
            createCustomOrderList(orderListOut, guestDAO, wineList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Checks the file path and creates the path if it doesnt already exists.</p>
     *
     * @param path A specific path to a location on a disk.
     * @return returns a boolean which will tell if the path has been made or not.
     */
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

    /**
     * <p>Adds an empty line to the given paragraph object</p>
     *
     * @param paragraph The paragraph object that will get new lines.
     * @param number    The amount of new lines that needs to be inserted.
     */
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}