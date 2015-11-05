package controllers;

import DAO.Database;
import DAO.GuestDAO;
import DAO.WineDAO;
import models.Guest;
import models.Wine;
import services.PDFService;
import views.OrderListPrintView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sander de Jong on 4-11-2015.
 */
public class OrderListPrintController {
    private PDFService pdfService;
    private OrderListPrintView orderListPrintView;
    private ArrayList<Wine> wineList;
    private WineDAO wineDAO;
    private GuestDAO guestDAO;

    public OrderListPrintController(OrderListPrintView orderListPrintView, WineDAO wineDAO, GuestDAO guestDAO) {
        this.pdfService = new PDFService();
        this.orderListPrintView = orderListPrintView;
        this.wineDAO = wineDAO;
        this.guestDAO = guestDAO;

        generateHandlers();
    }

    private void generateHandlers() {
        orderListPrintView.getPrintButton().setOnAction(event -> createOrderListPDF());
    }

    private void fillWineList() {
        this.wineList = this.wineDAO.getAllActiveWine();
    }

    public void createOrderListPDF() {
        fillWineList();
        pdfService.createOrderList(wineList, orderListPrintView.getTxtFileName().getText(), guestDAO);
    }

    public void printOrderListPDF() {
        //printservice??
    }
}