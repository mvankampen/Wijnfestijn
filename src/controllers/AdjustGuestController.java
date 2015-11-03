package controllers;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import models.Guest;
import splashscreens.AdjustCompleteMessage;
import splashscreens.GeneralSplash;
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
	SplashDefault adjustSplash = new GeneralSplash();
	private String title, header, context;
	private int errorCounter;

	public AdjustGuestController(AdjustGuestView adjustGuestView, GuestDAO guestDAO) {
		this.adjustGuestView = adjustGuestView;
		this.guestDAO = guestDAO;
		
		createAutocomplete();
	}
	public void createAutocomplete() {
		AutoCompletionBinding<Guest> autoCompletionBinding = TextFields.bindAutoCompletion(

		adjustGuestView.getSurnameTextField(), t -> guestDAO.findGuestBySurname(t.getUserText()),
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
		ObservableList<Guest> data = FXCollections.observableArrayList(currentGuest);
		adjustGuestView.getEditableGuest().setEditable(true);
		adjustGuestView.getEditableGuest().getColumns().clear();
	        TableColumn<Guest, String> surnameCol = createColumn("Achternaam", "surname");
	        surnameCol.setMinWidth(130);
	        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        surnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
	            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
	                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	                    .setSurname(t.getNewValue());
	            }
	        });
	        TableColumn<Guest, String> infixCol = createColumn("Tussenvoegsel", "infix");
	        infixCol.setMinWidth(110);
	        infixCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        infixCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
	            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
	                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	                    .setInfix(t.getNewValue());
	            }
	        });
	        TableColumn<Guest, String> firstnameCol = createColumn("Voornaam", "firstname");
	        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        firstnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
	            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
	                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	                    .setFirstname(t.getNewValue());
	            }
	        });
	        TableColumn<Guest, String> salutationCol = createColumn("Aanhef", "salutation");
	        salutationCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        salutationCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
	            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
	                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	                    .setSalutation(t.getNewValue());
	            }
	        });
	        TableColumn<Guest, String> streetCol = createColumn("Straat", "street");
	        streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        streetCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
	            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
	                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	                    .setStreet(t.getNewValue());
	            }
	        });
	        TableColumn<Guest, String> streetnrCol = createColumn("Huis nr", "streetnr");
	        streetnrCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        streetnrCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
	            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
	                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	                    .setStreetnr(t.getNewValue());
	            }
	        });
	        TableColumn<Guest, String> zipcodeCol = createColumn("Postcode", "zipcode");
	        zipcodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        zipcodeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
	            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
	                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	                    .setZipcode(t.getNewValue());
	            }
	        });
	        TableColumn<Guest, String> cityCol = createColumn("Stad", "city");
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
	        TableColumn<Guest, String> phoneCol = createColumn("Telefoonnummer", "phone");
	        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        phoneCol.setMinWidth(120);
	        phoneCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
	            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
	                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	                    .setPhone(t.getNewValue());
	            }
	        });
	        TableColumn<Guest, String> referralCol = createColumn("Geattendeerd door", "referal");
	        referralCol.setMinWidth(140);
	        referralCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        referralCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
	            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
	                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
	                    .setReferal(t.getNewValue());
	            }
	        });
	        TableColumn<Guest, Boolean> activeCol = createColumn("Niet gekomen", "no_show");
	        activeCol.setMinWidth(100);
	        activeCol.setCellFactory(col -> {
	            CheckBoxTableCell<Guest, Boolean> cell = new CheckBoxTableCell<>(index -> {
	                BooleanProperty active = new SimpleBooleanProperty(
	                    adjustGuestView.getEditableGuest().getItems().get(index).getNo_show());
	                active.addListener((obs, wasActive, isNowActive) -> {
	                    Guest item = adjustGuestView.getEditableGuest().getItems().get(index);
	                    item.setNo_show(isNowActive);
	                });
	                return active;
	            });
	            return cell;
	        });
		adjustGuestView.getEditableGuest().getColumns().clear();
		adjustGuestView.getEditableGuest().setItems(data);
		adjustGuestView.getEditableGuest().getColumns().addAll(surnameCol,infixCol,firstnameCol,salutationCol,streetCol,streetnrCol,zipcodeCol,cityCol,emailCol,phoneCol,referralCol, activeCol);

	}
	 private <S,T> TableColumn<S,T> createColumn(String title, String propertyName) {
	        TableColumn<S,T> col = new TableColumn<>(title);
	        col.setCellValueFactory(new PropertyValueFactory<>(propertyName));
	        return col;
	    }
	public void validateData() {
		context = "";
		errorCounter = 0;
		EmailValidator emailValidator = new EmailValidator();
		TextValidator textValidator = new TextValidator();
		ZipcodeValidator zipcodeValidator = new ZipcodeValidator();
		SplashDefault adjustSplash = new GeneralSplash();
		if (!textValidator.validate(currentGuest.getSurname().trim())) {
			adjustSplash = new SplashSurnameMessage(adjustSplash);
			errorCounter++;
		}
		if (!textValidator.validate(currentGuest.getFirstname().trim())) {
			adjustSplash = new SplashFirstnameMessage(adjustSplash);
			errorCounter++;
		}
		if (!textValidator.validate(currentGuest.getStreet().trim())) {
			adjustSplash = new SplashStreetnameMessage(adjustSplash);
			errorCounter++;
		}
		if (currentGuest.getStreetnr().trim().equals("")) {
			adjustSplash = new SplashStreetnrMessage(adjustSplash);
		}
		if (!zipcodeValidator.validate(currentGuest.getZipcode().trim())) {
			adjustSplash = new SplashZipcodeMessage(adjustSplash);
			errorCounter++;
		}
		if (!textValidator.validate(currentGuest.getCity().trim())) {
			adjustSplash = new SplashCityMessage(adjustSplash);
			errorCounter++;
		}
		if (!emailValidator.validate(currentGuest.getEmail().trim())) {
			adjustSplash = new SplashEmailMessage(adjustSplash);
			errorCounter++;
		}
		if (currentGuest.getSalutation() == null) {
			adjustSplash = new SplashSalutationMessage(adjustSplash);
			errorCounter++;
		}
		if (currentGuest.getReferal() == null) {
			adjustSplash = new SplashReferralMessage(adjustSplash);
			errorCounter++;
		}
		adjustSplash = new AdjustCompleteMessage(adjustSplash);
		setSplashScreenView(adjustSplash);
		if(errorCounter > 0) {
			new SplashscreenView(title, header, context);
		}
		else {
			submitChange();
		}
	}
	public void submitChange() {
		SplashDefault adjustSplash = new GeneralSplash();
		adjustSplash = new AdjustCompleteMessage(adjustSplash);
		setSplashScreenView(adjustSplash);
		new SplashscreenView(title, header, context);
		this.guestDAO.updateGuest(currentGuest);
		adjustGuestView.getEditableGuest().getColumns().clear();
		adjustGuestView.getSurnameTextField().clear();
	}
	public void setSplashScreenView(SplashDefault adjustSplash) {
		title = adjustSplash.getTitleText();
		header = adjustSplash.getHeaderText();
		context = adjustSplash.getContextText();
		
	}
}
