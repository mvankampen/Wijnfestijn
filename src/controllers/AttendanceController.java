package controllers;

import java.util.ArrayList;

import DAO.GuestDAO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import models.Guest;
import views.AttendanceView;

public class AttendanceController {

    private AttendanceView attendanceView;
    private GuestDAO guestDAO;
    private ObservableList<Guest> data;
    private ArrayList<Guest> allGuests;

    public AttendanceController(AttendanceView attendanceView, GuestDAO guestDAO) {
        this.attendanceView = attendanceView;
        this.guestDAO = guestDAO;
        generateHandlers();
        makeTable();
        importGuests();
    }

    private void generateHandlers() {
        this.attendanceView.getUpdateBtn().setOnAction(e -> validateData());
        this.attendanceView.getResetBtn().setOnAction(e -> resetNoShow());
    }
    public void importGuests() {
    	int j = 0;
    	allGuests = guestDAO.getAllGuest();
    	while(allGuests.size() > j) {
    		data.add(allGuests.get(j));
    		j++;
    	}
    }
    @SuppressWarnings("unchecked")
	public void makeTable() {
        data = FXCollections.observableArrayList();
        attendanceView.getTableView().setEditable(true);
        attendanceView.getTableView().getColumns().clear();
        TableColumn<Guest, String> surnameCol = createColumn("Surname", "surname");
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setSurname(t.getNewValue());
            }
        });
        TableColumn<Guest, String> infixCol = createColumn("Infix", "infix");
        infixCol.setCellFactory(TextFieldTableCell.forTableColumn());
        infixCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setInfix(t.getNewValue());
            }
        });
        TableColumn<Guest, String> firstnameCol = createColumn("Firstname", "firstname");
        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setFirstname(t.getNewValue());
            }
        });
        TableColumn<Guest, String> salutationCol = createColumn("Salutation", "salutation");
        salutationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        salutationCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setSalutation(t.getNewValue());
            }
        });
        TableColumn<Guest, String> streetCol = createColumn("Straatnaam", "street");
        streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setStreet(t.getNewValue());
            }
        });
        TableColumn<Guest, String> streetnrCol = createColumn("Streetnr", "streetnr");
        streetnrCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetnrCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setStreetnr(t.getNewValue());
            }
        });
        TableColumn<Guest, String> zipcodeCol = createColumn("Zipcode", "zipcode");
        zipcodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        zipcodeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setZipcode(t.getNewValue());
            }
        });
        TableColumn<Guest, String> cityCol = createColumn("City", "city");
        cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cityCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setCity(t.getNewValue());
            }
        });
        TableColumn<Guest, String> emailCol = createColumn("Email", "email");
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setEmail(t.getNewValue());
            }
        });
        TableColumn<Guest, String> phoneCol = createColumn("Phone", "phone");
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setPhone(t.getNewValue());
            }
        });
        TableColumn<Guest, String> referralCol = createColumn("Referral", "referal");
        referralCol.setCellFactory(TextFieldTableCell.forTableColumn());
        referralCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setReferal(t.getNewValue());
            }
        });
        TableColumn<Guest, Boolean> activeCol = createColumn("no Show", "no_show");
        activeCol.setCellFactory(col -> {
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
        attendanceView.getTableView().getColumns()
            .addAll(surnameCol, infixCol, firstnameCol, salutationCol, streetCol, streetnrCol,
                zipcodeCol, cityCol, emailCol, phoneCol, referralCol, activeCol);

    }

    private <S, T> TableColumn<S, T> createColumn(String title, String propertyName) {
        TableColumn<S, T> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return col;
    }

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

    private void validateData() {
    	int n = 0;
    	while(data.size() > n) {
    		this.guestDAO.updateGuest(data.get(n));
    		n++;
    	}
    }
}
