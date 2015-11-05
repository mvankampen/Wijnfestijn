package controllers;

import DAO.Database;
import DAO.GuestDAO;
import DAO.WineDAO;
import models.Guest;
import models.Wine;
import services.PDFService;
import splashscreens.ListprintSuccesMessage;
import splashscreens.MailEmptyMessage;
import splashscreens.SplashDefault;
import views.OrderListPrintView;
import views.SplashscreenView;

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
    private String context,title,header;
    private SplashscreenView splashScreenView;
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
        SplashDefault listprintSplash = new SplashDefault();
		listprintSplash = new ListprintSuccesMessage(listprintSplash);
		setSplashScreenView(listprintSplash);
    }

    private void setSplashScreenView(SplashDefault listPrintSplash) {
	 	context = "";
	 	title = "";
	 	header = "";
        title = listPrintSplash.getTitleText();
        header = listPrintSplash.getHeaderText();
        context = listPrintSplash.getContextText();
        splashScreenView = new SplashscreenView(title, header, context);
    }
    public void printOrderListPDF() {
        //printservice??
    }
}