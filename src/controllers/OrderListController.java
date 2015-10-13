package controllers;

import DAO.GuestDAO;
import DAO.OrderDAO;
import DAO.OrderLineDAO;
import DAO.WineDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import models.*;
import services.PDFService;
import splashscreens.GeneralSplash;
import splashscreens.RegistrationCompleteMessage;
import splashscreens.SplashDefault;

import java.util.ArrayList;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import views.OrderListView;
import views.RegistrationView;
import views.SplashscreenView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class OrderListController {
    private PDFService pdfService;
    private OrderListView orderListView;
    private GuestDAO guestDAO;
    private Guest currentGuest;
    private SplashscreenView splashScreenView;
    private String title, header, context;
    private ObservableList<OrderLine> data;
    private WineDAO wineDAO;
    private OrderLineDAO orderLineDAO;
    private OrderDAO orderDAO;
    private ScreensController screensController;
    private TableColumn<OrderLine, Wine> winenameCol;
    private TableColumn<OrderLine, Integer> amountCol;
    private ArrayList<String> help;


    public OrderListController(OrderListView orderListView, GuestDAO guestDAO, WineDAO wineDAO,
        OrderLineDAO orderLineDAO, OrderDAO orderDAO,ScreensController screensController) {
        this.orderListView = orderListView;
        this.guestDAO = guestDAO;
        this.wineDAO = wineDAO;
        this.orderLineDAO = orderLineDAO;
        this.orderDAO = orderDAO;
        this.screensController = screensController;
        this.pdfService = new PDFService();
        createAutoComplete();
    }
        public void createAutoComplete() {
        AutoCompletionBinding<Guest> autoCompletionBinding = TextFields
            .bindAutoCompletion(orderListView.getSurnameTextField(),
                t -> guestDAO.findGuestByLastname(t.getUserText()), new StringConverter<Guest>() {
                    @Override public String toString(Guest object) {
                        return object.getSurname() + ", " + object.getInfix() + " " + object
                            .getFirstname();
                    }

                    @Override public Guest fromString(String string) {
                        return null;
                    }
                });
        autoCompletionBinding
            .setOnAutoCompleted(event -> this.currentGuest = event.getCompletion());
        generateHandlers();

    }

    private void generateHandlers() {
        this.orderListView.getOrderBtn().setOnAction(event -> addOrder());
        this.orderListView.getMakeOrderBtn().setOnAction(event -> sendOrder());
        makeTable();
    }

    private void sendOrder() {
    	SplashDefault registrationSplash = new GeneralSplash();
		registrationSplash = new RegistrationCompleteMessage(registrationSplash);
		title = registrationSplash.getTitleText();
		header = registrationSplash.getHeaderText();
		context = registrationSplash.getContextText();
		Order order = new Order(this.currentGuest);
        order = this.orderDAO.addOrder(order);
        this.orderLineDAO.addOrderLines(data, order);
        this.pdfService.createOrderPdf(order, this.orderDAO);
		splashScreenView = new SplashscreenView(title, header, context);
        resetController();
    }
	public void resetController() {
		screensController.screenRemove(ControllersController.getORDERLISTID());
		this.orderListView = new OrderListView();
		screensController.screenLoadSet(ControllersController.getORDERLISTID(), orderListView);
		createAutoComplete();
		generateHandlers();
		help.clear();
	}

    private void makeTable() {
    	help = new ArrayList();
        data = FXCollections.observableArrayList();
        winenameCol = new TableColumn("Wijn");
        winenameCol.setMinWidth(100);
        winenameCol.setCellValueFactory(new PropertyValueFactory<>("wine"));
        winenameCol.setCellFactory(e -> {
            return new TableCell<OrderLine, Wine>() {
                @Override protected void updateItem(Wine item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item.getName());
                    }
                }
            };
        });

        amountCol = new TableColumn("Amount");
        amountCol.setMinWidth(100);
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));


        this.orderListView.getTableView().getColumns().addAll(winenameCol, amountCol);
        this.orderListView.getTableView()
            .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    private void addOrder() {
    	
        Wine wine = wineDAO.getWineById(orderListView.getWinenumberInt());
        String helptool = wine.getName();
        if(help.contains(helptool)) {
        	System.out.println("DONT FUCKING ADD THE SAME THING TWICE");
        }
        else {
        help.add(helptool);
        int amount = orderListView.getAmountInt();
        data.add(new OrderLine(amount, wine));
        this.orderListView.getTableView().getColumns().clear();
        this.orderListView.getTableView().setItems(data);
        this.orderListView.getTableView().getColumns().addAll(winenameCol, amountCol);
        }
    }


}