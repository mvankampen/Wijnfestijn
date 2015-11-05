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
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import services.MailService;
import services.PDFService;
import splashscreens.*;
import views.AttendanceView;
import views.OrderView;
import views.SplashscreenView;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

/**
 * <p>This class is used to direct the different {@link Order} objects and their {@link OrderLine} objects to the
 * database, so that they can be turned into a PDF file using {@link PDFService}.</p>
 *
 * @author Michael van Kampen
 * @version 0.1, november 2015
 */
public class OrderController {
    private PDFService pdfService;
    private MailService mailService;
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

    /**
     * @param orderView         The view where the order is created.
     * @param guestDAO          The Data Access Object that will be used to retrieve the guest that will be linked to the order.
     * @param wineDAO           The Data Access Object that will be used to retrieve the different wines.
     * @param orderLineDAO      The Data Access Object that will be used to insert the different {@link OrderLine} objects.
     * @param orderDAO          The Data Access Object that will be used to insert the {@link Order} object into the database.
     * @param screensController Used to direct the different windows within the application.
     * @param mailService       The service that is used to send the {@link Mail} to the {@link Guest}.
     */
    public OrderController(OrderView orderView, GuestDAO guestDAO, WineDAO wineDAO,
                           OrderLineDAO orderLineDAO, OrderDAO orderDAO, ScreensController screensController,
                           MailService mailService) {
        this.orderView = orderView;
        this.guestDAO = guestDAO;
        this.wineDAO = wineDAO;
        this.orderLineDAO = orderLineDAO;
        this.orderDAO = orderDAO;
        this.mailService = mailService;
        this.screensController = screensController;
        this.pdfService = new PDFService();
        createAutoComplete();
    }

    /**
     * <p>This method is used to autocomplete the users input in the guest textfield.</p>
     */
    public void createAutoComplete() {
        AutoCompletionBinding<Guest> autoCompletionBinding = TextFields
                .bindAutoCompletion(orderView.getSurnameTextField(),
                        t -> guestDAO.findGuestBySurname(t.getUserText()), new StringConverter<Guest>() {
                            @Override
                            public String toString(Guest object) {
                                return object.getSurname() + ", " + object.getInfix() + " " + object
                                        .getFirstname();
                            }

                            @Override
                            public Guest fromString(String string) {
                                return null;
                            }
                        });
        autoCompletionBinding
                .setOnAutoCompleted(event -> this.currentGuest = event.getCompletion());
        generateHandlers();

    }

    /**
     * <p>Generates different event handlers.</p>
     */
    private void generateHandlers() {
        this.orderView.getOrderBtn().setOnAction(event -> addOrder());
        this.orderView.getRemoveBtn().setOnAction(event -> removeOrder());
        this.orderView.getMakeOrderBtn().setOnAction(event -> sendOrder());
        makeTable();
    }

    /**
     * <p>Sets the splashscreen view to the new splash screen and returns this splashscreen so that it can be used.</p>
     *
     * @param orderSplash
     */
    private void setSplashScreenView(SplashDefault orderSplash) {
        title = orderSplash.getTitleText();
        header = orderSplash.getHeaderText();
        context = orderSplash.getContextText();
        splashScreenView = new SplashscreenView(title, header, context);
    }

    /**
     * <p>This method creates the {@link OrderLine} objects and the {@link Order} object and sends them to the
     * database. After that the method creates a new {@link Mail} object and a new PDF. The PDF gets attached to the
     * mail and is sent to the {@link Guest} which the order belongs to.</p>
     */
    private void sendOrder() {
        SplashDefault orderSplash = new GeneralSplash();
        if (allWines.isEmpty()) {
            orderSplash = new OrderEmptyMessage(orderSplash);
            setSplashScreenView(orderSplash);

        } else {
            orderSplash = new OrderCompleteMessage(orderSplash);
            setSplashScreenView(orderSplash);
            Order order = new Order(this.currentGuest);
            order = this.orderDAO.addOrder(order);
            this.orderLineDAO.addOrderLines(data, order);
            Mail mail = new Mail("Factuur Wijnfestijn",
                    "<html><head></head><body>Beste " + this.pdfService.getFullName(order.getGuest())
                            + "<p>Hierbij mailen wij u een factuur van uw gemaakte order op ons Wijnfestijn.</p><p>Met vriendelijke groet,<br />Lions-Club Oegstgeest/Warmond</p></body></html>");
            try {
                ArrayList<InternetAddress> arrayList = new ArrayList<>();
                arrayList.add(new InternetAddress(order.getGuest().getEmail()));
                mail.setRecipients(arrayList);
            } catch (AddressException e) {
                e.printStackTrace();
            }
            this.mailService.setMail(mail);
            this.mailService.addAttachment(this.pdfService.createOrderPdf(order, this.orderDAO));
            this.mailService.sendMail();


            resetFields();
        }
    }

    /**
     * <p>Displays the table that contains all the {@link Wine} objects.</p>
     */
    private void makeTable() {
        allWines = new ArrayList<String>();
        data = FXCollections.observableArrayList();
        winenameCol = new TableColumn<OrderLine, Wine>("Wijn");
        winenameCol.setMinWidth(100);
        winenameCol.setCellValueFactory(new PropertyValueFactory<>("wine"));
        winenameCol.setCellFactory(e -> {
            return new TableCell<OrderLine, Wine>() {
                @Override
                protected void updateItem(Wine item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item.getName());
                    }
                }
            };
        });

        amountCol = new TableColumn<OrderLine, Integer>("Aantal");
        amountCol.setMinWidth(100);
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        this.orderView.getTableView().getColumns().clear();
        this.orderView.getTableView().getColumns().addAll(winenameCol, amountCol);
        this.orderView.getTableView().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.orderView.getTableView().setMinHeight(250);

    }

    /**
     * <p>Removes a {@link Wine} from the current order</p>
     */
    private void removeOrder() {
        ObservableList<OrderLine> orderlineSelected, allOrderlines;
        allOrderlines = this.orderView.getTableView().getItems();
        orderlineSelected = this.orderView.getTableView().getSelectionModel().getSelectedItems();
        String helptool =
                this.orderView.getTableView().getSelectionModel().getSelectedItem().getWine().getName();
        allWines.remove(helptool);
        orderlineSelected.forEach(allOrderlines::remove);
    }

    /**
     * <p>Adds a {@link Wine} to the current order</p>
     */
    private void addOrder() {
        SplashDefault orderSplash = new SplashDefault();
        Wine wine = wineDAO.getWineById(orderView.getWinenumberInt());
        int amount = orderView.getAmountInt();
        String helptool = wine.getName();
        this.orderView.getAmountTextField().clear();
        this.orderView.getWinenumberTextField().clear();
        if (allWines.contains(helptool)) {
            orderSplash = new OrderDuplicateMessage(orderSplash);
            setSplashScreenView(orderSplash);

        } else {
            allWines.add(helptool);
            data.add(new OrderLine(amount, wine));
            this.orderView.getTableView().getColumns().clear();
            this.orderView.getTableView().setItems(data);
            this.orderView.getTableView().getColumns().addAll(winenameCol, amountCol);
        }
    }

    /**
     * <p>Resets all the fields in the {@link OrderView}.</p>
     */
    void resetFields() {
        screensController.screenRemove(ControllersController.getATTENDANCEID());
        this.orderView = new OrderView();
        screensController.screenLoadSet(ControllersController.getATTENDANCEID(), orderView);
        createAutoComplete();
        generateHandlers();
        allWines.clear();
    }


}