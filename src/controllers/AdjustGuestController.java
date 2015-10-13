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
import splashscreens.GeneralSplash;
import splashscreens.RegistrationSetHead;
import splashscreens.SplashCityMessage;
import splashscreens.SplashDefault;
import splashscreens.SplashEmailMessage;
import splashscreens.SplashFirstnameMessage;
import splashscreens.SplashReferralMessage;
import splashscreens.SplashSalutationMessage;
import splashscreens.SplashStreetnameMessage;
import splashscreens.SplashStreetnrMessage;
import splashscreens.SplashSurnameMessage;
import splashscreens.SplashZipcodeMessage;
import validators.EmailValidator;
import validators.TextValidator;
import validators.ZipcodeValidator;
import views.AdjustGuestView;
import views.SplashscreenView;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class AdjustGuestController {
	private AdjustGuestView adjustGuestView;
	private GuestDAO guestDAO;
	private Guest currentGuest;
	EmailValidator emailValidator = new EmailValidator();
	TextValidator textValidator = new TextValidator();
	ZipcodeValidator zipcodeValidator = new ZipcodeValidator();
	SplashDefault registrationSplash = new GeneralSplash();
	private SplashscreenView splashscreenView;
	private String title, header, context;
	private int errorCounter;

	public AdjustGuestController(AdjustGuestView adjustGuestView, GuestDAO guestDAO) {
		this.adjustGuestView = adjustGuestView;
		this.guestDAO = guestDAO;
		
		createAutocomplete();
	}
	public void createAutocomplete() {
		AutoCompletionBinding<Guest> autoCompletionBinding = TextFields.bindAutoCompletion(

		adjustGuestView.getSurnameTextField(), t -> guestDAO.findGuestByLastname(t.getUserText()),
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
		adjustGuestView.getUpdateButton().setOnAction(e -> validateData());
		adjustGuestView.getSurnameTextField().setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					importCurrentGuest();
				}
			}

		});
	}

	@SuppressWarnings("unchecked")
	public void importCurrentGuest() {
	
		//First row : surName
		final ObservableList<Guest> data = FXCollections.observableArrayList(currentGuest);
		adjustGuestView.getEditableGuest().setEditable(true);
		adjustGuestView.getEditableGuest().getColumns().clear();
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
		TableColumn<Guest, String> salutationCol = new TableColumn<Guest, String>("Salutation");
		salutationCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("salutation"));
		salutationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		salutationCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalutation(t.getNewValue());
			}
		});
		TableColumn<Guest, String> streetCol = new TableColumn<Guest, String>("Streetname");
		System.out.println("In Streetname staat " + currentGuest.getStreet());
		streetCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("streetname"));
		streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
		streetCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStreet(t.getNewValue());
			}
		});
		TableColumn<Guest, String> streetnrCol = new TableColumn<Guest, String>("Streetnr");
		System.out.println("In Streetnr staat " + currentGuest.getStreetnr());
		streetnrCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("streetnr"));
		streetnrCol.setCellFactory(TextFieldTableCell.forTableColumn());
		streetnrCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStreetnr(t.getNewValue());
			}
		}); 
		TableColumn<Guest, String> zipcodeCol = new TableColumn<Guest, String>("Zipcode");
		System.out.println("In zipcode staat " + currentGuest.getZipcode());
		zipcodeCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("zipcode"));
		zipcodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		zipcodeCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
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
		TableColumn<Guest, String> referralCol = new TableColumn<Guest, String>("Referral");
		referralCol.setCellValueFactory(new PropertyValueFactory<Guest, String>("Referal"));
		referralCol.setCellFactory(TextFieldTableCell.forTableColumn());
		referralCol.setOnEditCommit(new EventHandler<CellEditEvent<Guest, String>>() {
			@Override
			public void handle(CellEditEvent<Guest, String> t) {
				((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow())).setReferal(t.getNewValue());
			}
		});
		adjustGuestView.getEditableGuest().getColumns().clear();
		adjustGuestView.getEditableGuest().setItems(data);
		adjustGuestView.getEditableGuest().getColumns().addAll(surnameCol,infixCol,firstnameCol,salutationCol,streetCol,streetnrCol,zipcodeCol,cityCol,emailCol,phoneCol,referralCol);

	}
	public void validateData() {
		context = "";
		errorCounter = 0;
		EmailValidator emailValidator = new EmailValidator();
		TextValidator textValidator = new TextValidator();
		ZipcodeValidator zipcodeValidator = new ZipcodeValidator();
		SplashDefault registrationSplash = new GeneralSplash();
		if (!textValidator.validate(currentGuest.getSurname().trim())) {
			registrationSplash = new SplashSurnameMessage(registrationSplash);
			errorCounter++;
		}
		if (!textValidator.validate(currentGuest.getFirstname().trim())) {
			registrationSplash = new SplashFirstnameMessage(registrationSplash);
			errorCounter++;
		}
		if (!textValidator.validate(currentGuest.getStreet().trim())) {
			registrationSplash = new SplashStreetnameMessage(registrationSplash);
			errorCounter++;
		}
		if (currentGuest.getStreetnr().trim().equals("")) {
			registrationSplash = new SplashStreetnrMessage(registrationSplash);
		}
		if (!zipcodeValidator.validate(currentGuest.getZipcode().trim())) {
			registrationSplash = new SplashZipcodeMessage(registrationSplash);
			errorCounter++;
		}
		if (!textValidator.validate(currentGuest.getCity().trim())) {
			registrationSplash = new SplashCityMessage(registrationSplash);
			errorCounter++;
		}
		if (!emailValidator.validate(currentGuest.getEmail().trim())) {
			registrationSplash = new SplashEmailMessage(registrationSplash);
			errorCounter++;
		}
		if (currentGuest.getSalutation() == null) {
			registrationSplash = new SplashSalutationMessage(registrationSplash);
			errorCounter++;
		}
		if (currentGuest.getReferal() == null) {
			registrationSplash = new SplashReferralMessage(registrationSplash);
		}
		title = registrationSplash.getTitleText();
		header = registrationSplash.getHeaderText();
		context = registrationSplash.getContextText();
		if(errorCounter > 0) {
			splashscreenView = new SplashscreenView(title, header, context);
		}
		else {
			submitChange();
		}
	}
	public void submitChange() {
		this.guestDAO.updateGuest(currentGuest);
	}
	public void fireAlert() {
		title = registrationSplash.getTitleText();
		header = registrationSplash.getHeaderText();
		context = registrationSplash.getContextText();
		
	}
}
