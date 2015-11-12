package controllers;

import DAO.GuestDAO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import models.Guest;
import views.AttendanceView;

import java.util.ArrayList;

/**
 * <P>Attendance Controller is responsible for keeping track of the presence per guest</P>
 *
 * @author Alex van der Wal
 * @version 0.1, november 2015
 */

public class AttendanceController {

    private AttendanceView attendanceView;
    private GuestDAO guestDAO;
    private ObservableList<Guest> data;
    private ArrayList<Guest> allGuests;
    private ScreensController screensController;

    /**
     * @param attendanceView    The view to change the attendance of guest
     * @param guestDAO          The Data Access Object that gets the guest data.
     * @param screensController Used to direct the different windows within the application.
     */
    public AttendanceController(AttendanceView attendanceView, GuestDAO guestDAO,
        ScreensController screensController) {
        this.attendanceView = attendanceView;
        this.guestDAO = guestDAO;
        this.screensController = screensController;
        generateHandlers();
    }

    /**
     * <p>Generates different event handlers.</p>
     */
    private void generateHandlers() {
        this.attendanceView.getUpdateBtn().setOnAction(e -> updateData());
        this.attendanceView.getResetBtn().setOnAction(e -> resetNoShow());
        makeTable();
        importGuests();
    }

    /**
     * <P>Imports all Guests to the TableView</P>
     */
    public void importGuests() {
        int j = 0;
        allGuests = guestDAO.getAllGuest();
        while (allGuests.size() > j) {
            data.add(allGuests.get(j));
            j++;
        }
    }


    /**
     * <P>create the TableView columns with editable fields so the user can change
     * the data to correct the data inside the database</P>
     */
    @SuppressWarnings("unchecked") public void makeTable() {
        data = FXCollections.observableArrayList();
        attendanceView.getTableView().setEditable(true);
        attendanceView.getTableView().getColumns().clear();
        TableColumn<Guest, String> surnameCol = createColumn("Achternaam", "surname");
        surnameCol.setMinWidth(130);
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setSurname(t.getNewValue()));
        TableColumn<Guest, String> infixCol = createColumn("Tussenvoegsel", "infix");
        infixCol.setMinWidth(110);
        infixCol.setCellFactory(TextFieldTableCell.forTableColumn());
        infixCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setInfix(t.getNewValue()));
        TableColumn<Guest, String> firstnameCol = createColumn("Voornaam", "firstname");
        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstnameCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setFirstname(t.getNewValue()));
        TableColumn<Guest, String> salutationCol = createColumn("Aanhef", "salutation");
        salutationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        salutationCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setSalutation(t.getNewValue()));
        TableColumn<Guest, String> streetCol = createColumn("Straat", "street");
        streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setStreet(t.getNewValue()));
        TableColumn<Guest, String> streetnrCol = createColumn("Huis nr", "streetnr");
        streetnrCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetnrCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setStreetnr(t.getNewValue()));
        TableColumn<Guest, String> zipcodeCol = createColumn("Postcode", "zipcode");
        zipcodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        zipcodeCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setZipcode(t.getNewValue()));
        TableColumn<Guest, String> cityCol = createColumn("Stad", "city");
        cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cityCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setCity(t.getNewValue()));
        TableColumn<Guest, String> emailCol = createColumn("Email", "email");
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setEmail(t.getNewValue()));
        TableColumn<Guest, String> phoneCol = createColumn("Telefoonnummer", "phone");
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setMinWidth(120);
        phoneCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setPhone(t.getNewValue()));
        TableColumn<Guest, String> referralCol = createColumn("Geattendeerd door", "referal");
        referralCol.setMinWidth(140);
        referralCol.setCellFactory(TextFieldTableCell.forTableColumn());
        referralCol.setOnEditCommit(
            t -> ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setReferal(t.getNewValue()));
        TableColumn<Guest, Boolean> activeCol = createColumn("Niet gekomen", "no_show");
        activeCol.setMinWidth(100);
        activeCol.setCellFactory(col -> {
            //checkbox that allows the user to change the "no_show" status of the targeted guest
            CheckBoxTableCell<Guest, Boolean> cell = new CheckBoxTableCell<>(index -> {
                BooleanProperty active = new SimpleBooleanProperty(
                    attendanceView.getTableView().getItems().get(index).getNo_show());
                active.addListener((obs, wasActive, isNowActive) -> {
                    Guest item = attendanceView.getTableView().getItems().get(index);
                    item.setNo_show(isNowActive);
                });
                return active;
            });
            return cell;
        });
        attendanceView.getTableView().getColumns().clear();
        attendanceView.getTableView().setItems(data);
        //add all columns to the table
        attendanceView.getTableView().getColumns()
            .addAll(activeCol, surnameCol, infixCol, firstnameCol, salutationCol, streetCol,
                streetnrCol, zipcodeCol, cityCol, emailCol, phoneCol, referralCol);

    }

    /**
     * @param title        name of the Column
     * @param propertyName name of the property that displays
     * @param <S>          which object the Column takes as input
     * @param <T>          which type the property is
     * @return TableColumn
     */
    private <S, T> TableColumn<S, T> createColumn(String title, String propertyName) {
        TableColumn<S, T> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return col;
    }

    /**
     * <P>Sets every Guest their NoShow value to FALSE</P>
     */
    private void resetNoShow() {
        int n = 0;
        while (data.size() > n) {
            this.data.get(n).setNo_show(false);
            this.guestDAO.updateGuest(data.get(n));
            n++;
        }
        data.clear();
        importGuests();
        attendanceView.getTableView().setItems(data);
    }

    /**
     * <P>Update the NoShow value for each selected {@link Guest}</P>
     */
    private void updateData() {
        int n = 0;
        while (data.size() > n) {
            this.guestDAO.updateGuest(data.get(n));
            n++;
        }
    }

    /**
     * <p>Resets all the fields in the {@link AttendanceView}.</p>
     */
    public void resetFields() {
        screensController.screenRemove(ControllersController.getATTENDANCEID());
        this.attendanceView = new AttendanceView();
        screensController.screenLoadSet(ControllersController.getATTENDANCEID(), attendanceView);
        generateHandlers();
    }
}
