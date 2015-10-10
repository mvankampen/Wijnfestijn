package controllers;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import DAO.GuestDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import models.Guest;
import views.CustomersView;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class CustomerController {
	private CustomersView customersView;
	private GuestDAO guestDAO;
	private Guest currentGuest;

	public CustomerController(CustomersView customersView, GuestDAO guestDAO) {
		this.customersView = customersView;
		this.guestDAO = guestDAO;
		System.out.println(" hahaha");
		createAutocomplete();

		/*
		 * customersView.getSurnameTextField().setOnKeyPressed(new
		 * EventHandler<KeyEvent>() {
		 * 
		 * @Override public void handle(KeyEvent ke) { if
		 * (ke.getCode().equals(KeyCode.ENTER)) { fillEditableGuest(); } } });
		 * 
		 * 
		 * 
		 * } public void fillEditableGuest() { ObservableList<String> text =
		 * FXCollections.observableArrayList(currentGuest.getEmail());
		 * customersView.getEditableGuest().setItems(text);
		 * customersView.getEditableGuest().refresh(); }
		 */
	}

	public void createAutocomplete() {
		AutoCompletionBinding<Guest> autoCompletionBinding = TextFields.bindAutoCompletion(

		customersView.getSurnameTextField(), t -> guestDAO.findGuestByLastname(t.getUserText()),
				new StringConverter<Guest>() {
					@Override
					public String toString(Guest object) {
						return object.getSurname() + ", " + object.getInfix() + " " + object.getFirstname();
					}

					@Override
					public Guest fromString(String string) {
						return null;
					}
				});
		autoCompletionBinding.setOnAutoCompleted(event -> this.currentGuest = event.getCompletion());
		customersView.getSurnameTextField().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					importCurrentGuest();
				}
			}

		});
	}

	public void importCurrentGuest() {
		//First row : surName
		final ObservableList<Guest> data = FXCollections.observableArrayList(currentGuest);
		customersView.getEditableGuest().setEditable(true);
		TableColumn<Guest, String> surnameCol = new TableColumn<Guest, String>("Surname");
		surnameCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("surname"));
		surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		surnameCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSurname(t.getNewValue());
			}
		});
		TableColumn<Guest, String> infixCol = new TableColumn<Guest, String>("infix");
		infixCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("infix"));
		infixCol.setCellFactory(TextFieldTableCell.forTableColumn());
		infixCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setInfix(t.getNewValue());
			}
		});
		TableColumn<Guest, String> firstnameCol = new TableColumn<Guest, String>("Firstname");
		firstnameCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstname"));
		firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		firstnameCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstname(t.getNewValue());
			}
		});
		TableColumn<Guest, String> salutationCol = new TableColumn<Guest, String>("Surname");
		salutationCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("salutation"));
		salutationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		salutationCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalutation(t.getNewValue());
			}
		});
		TableColumn<Guest, String> streetCol = new TableColumn<Guest, String>("Streetname");
		streetCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("streetname"));
		streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
		streetCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStreetname(t.getNewValue());
			}
		});
		TableColumn<Guest, String> streetnrCol = new TableColumn<Guest, String>("Streetnr");
		streetnrCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("streetnr"));
		streetnrCol.setCellFactory(TextFieldTableCell.forTableColumn());
		streetnrCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStreetnr(t.getNewValue());
			}
		});
		TableColumn<Guest, String> zipcodeCol = new TableColumn<Guest, String>("Zipcode");
		streetnrCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("zipcode"));
		streetnrCol.setCellFactory(TextFieldTableCell.forTableColumn());
		streetnrCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setZipcode(t.getNewValue());
			}
		});
		TableColumn<Guest, String> cityCol = new TableColumn<Guest, String>("City");
		cityCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("city"));
		cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
		cityCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCity(t.getNewValue());
			}
		});
		TableColumn<Guest, String> emailCol = new TableColumn<Guest, String>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("email"));
		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			}
		});
		TableColumn<Guest, String> phoneCol = new TableColumn<Guest, String>("Phone");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("phone"));
		phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
		phoneCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhone(t.getNewValue());
			}
		});
		customersView.getEditableGuest().setItems(data);
		System.out.println("root");
		customersView.getEditableGuest().getColumns().addAll(surnameCol,infixCol,firstnameCol,salutationCol,streetCol,streetnrCol,zipcodeCol,cityCol,emailCol,phoneCol);
		customersView.getEditableGuest().refresh();	
		customersView.getUpdateButton().setOnAction(e -> submitChange());
	}
	public void submitChange() {
		this.guestDAO.updateGuest(currentGuest);
	}
}
