package controllers;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import DAO.GuestDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Guest;
import views.ImportGuestListView;

public class ImportGuestListController {
	ImportGuestListView importGuestListView;
	private GuestDAO guestDAO;
	
	File importFile = null;
	boolean csvSelected = false, firstRow = true;
	CSVReader csvReader;
    private ObservableList<Guest> data;
	ArrayList<Guest> guestList;
	
	final FileChooser fileChooser = new FileChooser();
	
	public ImportGuestListController(ImportGuestListView importGuestListView, ScreensController screensController, GuestDAO guestDAO){
		this.guestDAO = guestDAO;
		this.importGuestListView = importGuestListView;
		generateHandlers();
		createTable();
	}

	public void generateHandlers(){
		importGuestListView.getImportButton().setOnAction(e -> {
			fireCsv();
			});
		importGuestListView.getUploadButton().setOnAction(e -> {
			submitGuests();
		});
	}
	public void submitGuests(){
		int n = 0;
    	while (data.size() > n) {
    		this.guestDAO.addGuest(data.get(n));
    		n++;
    	}
	}
	public void fireCsv(){
		importFile = fileChooser.showOpenDialog(new Stage());
		if(isCSV(importFile)){
			try {
				guestList = new ArrayList<Guest>();
				String[] parts = null;
				csvReader = new CSVReader(new FileReader(importFile));
				String[] nextLine;
				while((nextLine = csvReader.readNext()) != null){
					if(!firstRow){
						for(String s : nextLine){
							parts = s.split(";");
						}
					}
					else{
						//importWineListView.wineListArea.appendText(nextLine[0].replace(";", " | ") + "\n");
						firstRow = false;
					}
					
					if(parts != null){
						Guest selectedGuest = new Guest(
								parts[0], parts[1], parts[2],
								parts[3], parts[4], parts[5],
								parts[6], parts[7], parts[8], parts[9], parts[10], parts[11]);
						guestList.add(selectedGuest);
						data.add(selectedGuest);
						
						System.out.println(data);
					parts = null;
					nextLine = null;
					}
				}
				importGuestListView.getTable().setItems(data);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else{
			System.out.println("Dit is geen .CSV bestand");
		}
	};
	
	
	public boolean isCSV(File importFile){
		if(importFile.toString().substring(importFile.toString().length() -4, importFile.toString().length()).equals(".csv")){
			csvSelected = true;
		}
		return csvSelected;
	}
	
	@SuppressWarnings({ "unchecked" })
	public void createTable(){
        data = FXCollections.observableArrayList();
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
        importGuestListView.getTable().getColumns().clear();
        importGuestListView.getTable().setItems(data);
        importGuestListView.getTable().getColumns()
            .addAll(surnameCol, infixCol, firstnameCol, salutationCol, streetCol, streetnrCol,
                zipcodeCol, cityCol, emailCol, phoneCol, referralCol);

    }
	 private <S, T> TableColumn<S, T> createColumn(String title, String propertyName) {
	        TableColumn<S, T> col = new TableColumn<>(title);
	        col.setCellValueFactory(new PropertyValueFactory<>(propertyName));
	        return col;
	    }
}

