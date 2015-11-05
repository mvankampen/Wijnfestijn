package controllers;

import java.util.ArrayList;

import DAO.GuestDAO;
import DAO.OrderDAO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Guest;
import models.Order;
import splashscreens.DebtorsEmptyMessage;
import splashscreens.ImportSucceedMessage;
import splashscreens.SplashDefault;
import views.AttendanceView;
import views.DebtorsView;
import views.SplashscreenView;

/**
 * <p>Is used to generate a list of all the debtors.</p>
 *
 * @author Alex van der Wal
 * @version 0.1, november 2015
 */
public class DebtorsController {
    private DebtorsView debtorsView;
    private OrderDAO orderDAO;
    private ArrayList<Order> debtorsArrayList;
    private ObservableList<Order> data;
    private TableColumn<Order, Guest> emailCol;
    private TableColumn<Order, Boolean> activeCol;
    private TableColumn<Order, Integer> idCol;
    private String context, title, header;
    private SplashscreenView splashScreenView;
    private ScreensController screensController;

    /**
     * @param debtorsView       The view that is used to show all the debtors on the screen.
     * @param orderDAO          The Data Access Object that is used to get the open {@link Order} objects from a {@link Guest}.
     * @param guestDAO          The Data Access Object that is used to get the {@link Guest} object.
     * @param screensController Used to direct the different windows within the application.
     */
    public DebtorsController(DebtorsView debtorsView, OrderDAO orderDAO, GuestDAO guestDAO, ScreensController screensController) {
        this.debtorsView = debtorsView;
        this.orderDAO = orderDAO;
        this.screensController = screensController;
        generateHandlers();
    }

    /**
     * <p>Generates different event handlers.</p>
     */
    private void generateHandlers() {
        makeTable();
        this.debtorsView.getGenerateButton().setOnAction(e -> generateDebtors());
        this.debtorsView.getSaveButton().setOnAction(e -> submitChanges());
    }

    /**
     * Gets the title, header, context properties from debtorsCsvSplash and creates a {@link SplashscreenView} with it,
     * showing the message to the user.
     *
     * @param debtorCsvSplash
     */
    private void setSplashScreenView(SplashDefault debtorCsvSplash) {
        context = "";
        title = "";
        header = "";
        title = debtorCsvSplash.getTitleText();
        header = debtorCsvSplash.getHeaderText();
        context = debtorCsvSplash.getContextText();
        splashScreenView = new SplashscreenView(title, header, context);
    }

    /**
     * <p>Creats the columns for the tableview.</p>
     */
    @SuppressWarnings("unchecked")
    private void makeTable() {
        data = FXCollections.observableArrayList();
        this.debtorsView.getTableView().setMaxHeight(400);
        emailCol = new TableColumn<Order, Guest>("Gast email");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("guest"));
        emailCol.setCellFactory(e -> {
            return new TableCell<Order, Guest>() {
                //allows showing the email of the guest instead of the modelnumber
                @Override
                protected void updateItem(Guest item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item.getEmail());
                    }
                }
            };
        });
        activeCol = new TableColumn<Order, Boolean>("Order voltooid");
        activeCol.setCellValueFactory(new PropertyValueFactory<>("completed"));
        activeCol.setMinWidth(150);
        activeCol.setCellFactory(col -> {
            //checkbox that changes the boolean value according to if it's checked or not
            CheckBoxTableCell<Order, Boolean> cell = new CheckBoxTableCell<>(index -> {
                BooleanProperty active = new SimpleBooleanProperty(debtorsView.getTableView().getItems().get(index).getCompleted());
                active.addListener((obs, wasActive, isNowActive) -> {
                    Order item = debtorsView.getTableView().getItems().get(index);
                    item.setCompleted(isNowActive);
                });
                return active;
            });
            return cell;
        });
        idCol = new TableColumn<Order, Integer>("Order nummer");
        idCol.setMinWidth(150);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //clearing the columns to prevent double columns
        debtorsView.getTableView().getColumns().clear();
        debtorsView.getTableView().setItems(data);
        columnAdder();

    }

    /**
     * <p>Reads out all the debtors and adds them to the tableview array.</p>
     */
    private void columnAdder() {
        debtorsView.getTableView().getColumns().addAll(idCol, emailCol, activeCol);
    }

    /**
     * Adds the debtors to the tableview.
     */
    @SuppressWarnings("unchecked")
    private void generateDebtors() {
        debtorsArrayList = orderDAO.getAllNativeOrders();
        if (debtorsArrayList.size() == 0) {
            SplashDefault guestCsvSplash = new SplashDefault();
            guestCsvSplash = new DebtorsEmptyMessage(guestCsvSplash);
            setSplashScreenView(guestCsvSplash);

        }
        data.clear();
        int j = 0;
        while (debtorsArrayList.size() > j) {
            data.add(debtorsArrayList.get(j));
            j++;
        }
        debtorsArrayList.clear();
        //updates the tableview so that the new data is shown
        debtorsView.getTableView().getColumns().clear();
        debtorsView.getTableView().setItems(data);
        columnAdder();
    }

    /**
     * <p>Updates the changed data in the database.</p>
     */
    private void submitChanges() {
        int n = 0;
        while (data.size() > n) {
            orderDAO.updateOrder(data.get(n));
            n++;
        }
        SplashDefault debtorCsvSplash = new SplashDefault();
        debtorCsvSplash = new ImportSucceedMessage(debtorCsvSplash);
        setSplashScreenView(debtorCsvSplash);
        generateHandlers();
        debtorsArrayList.clear();
    }

    //clearing all content in the controller

    /**
     * <p>Clears all the content in the controller.</p>
     */
    public void resetFields() {
        screensController.screenRemove(ControllersController.getATTENDANCEID());
        this.debtorsView = new DebtorsView();
        screensController.screenLoadSet(ControllersController.getATTENDANCEID(), debtorsView);
        generateHandlers();
    }

}
