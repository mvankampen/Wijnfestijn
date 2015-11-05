package controllers;

import DAO.Database;
import DAO.WineDAO;
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

    public OrderListPrintController(OrderListPrintView orderListPrintView, WineDAO wineDAO) {
        this.pdfService = new PDFService();
        this.orderListPrintView = orderListPrintView;
        this.wineDAO = wineDAO;

        orderListPrintView.getPrintButton().setOnAction(event -> createOrderListPDF());
    }

    private void fillWineList() {
        this.wineList = this.wineDAO.getAllActiveWine();
    }

    public void createOrderListPDF() {
        fillWineList();
        pdfService.createOrderList(wineList, orderListPrintView.getTxtFileName().getText());
    }

    public void printOrderListPDF() {
        //printservice??
    }
}