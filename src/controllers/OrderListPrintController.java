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
 * <p>
 * This class uses the {@link PDFService} class to create PDF files from the current orderlist.
 * The current orderlist is determined by the database records. For every guest it creates a custom
 * orderlist plus an additional orderlist with no guest information.
 * </p>
 *
 * @author Sander de Jong
 * @version 0.1, november 2015
 */
public class OrderListPrintController {
    private PDFService pdfService;
    private OrderListPrintView orderListPrintView;
    private ArrayList<Wine> wineList;
    private String context,title,header;
    private SplashscreenView splashScreenView;
    private WineDAO wineDAO;
    private GuestDAO guestDAO;

    /**
     * <p>Constructor</p>
     * @param orderListPrintView
     * @param wineDAO
     * @param guestDAO
     */
    public OrderListPrintController(OrderListPrintView orderListPrintView, WineDAO wineDAO, GuestDAO guestDAO) {
        this.pdfService = new PDFService();
        this.orderListPrintView = orderListPrintView;
        this.wineDAO = wineDAO;
        this.guestDAO = guestDAO;

        generateHandlers();
    }

    /**
     * <p>Generates the event handlers for the controls used by this class.</p>
     */
    private void generateHandlers() {
        orderListPrintView.getPrintButton().setOnAction(event -> createOrderListPDF());
    }

    /**
     * <p>Fills the winelist with all the current active wines from the database.</p>
     */
    private void fillWineList() {
        this.wineList = this.wineDAO.getAllActiveWine();
    }

    /**
     * <p>Calls upon the {@link PDFService} class to create the custom orderlists.</p>
     */
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
}