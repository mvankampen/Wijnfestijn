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
import views.OrderView;
import views.RegistrationView;
import views.SplashscreenView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class OrderController {
    private PDFService pdfService;
    private OrderView orderView;
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
    private ArrayList<String> allWines;


    public OrderController(OrderView orderView, GuestDAO guestDAO, WineDAO wineDAO,
        OrderLineDAO orderLineDAO, OrderDAO orderDAO,ScreensController screensController) {
        this.orderView = orderView;
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
            .bindAutoCompletion(orderView.getSurnameTextField(),
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
        this.orderView.getOrderBtn().setOnAction(event -> addOrder());
        this.orderView.getMakeOrderBtn().setOnAction(event -> sendOrder());
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
		this.orderView = new OrderView();
		screensController.screenLoadSet(ControllersController.getORDERLISTID(), orderView);
		createAutoComplete();
		generateHandlers();
		allWines.clear();
	}

    private void makeTable() {
    	allWines = new ArrayList();
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


        this.orderView.getTableView().getColumns().addAll(winenameCol, amountCol);
        this.orderView.getTableView()
            .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    private void addOrder() {
    	
        Wine wine = wineDAO.getWineById(orderView.getWinenumberInt());
        String helptool = wine.getName();
        if(allWines.contains(helptool)) {
        	System.out.println("DONT FUCKING ADD THE SAME THING TWICE");
        }
        else {
        allWines.add(helptool);
        int amount = orderView.getAmountInt();
        data.add(new OrderLine(amount, wine));
        this.orderView.getTableView().getColumns().clear();
        this.orderView.getTableView().setItems(data);
        this.orderView.getTableView().getColumns().addAll(winenameCol, amountCol);
        }
    }


}