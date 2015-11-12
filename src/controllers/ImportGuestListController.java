package controllers;

import DAO.GuestDAO;
import com.opencsv.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Guest;
import splashscreens.CsvIncorrectFileMessage;
import splashscreens.CsvIncorrectRowsGuestMessage;
import splashscreens.ImportSucceedMessage;
import splashscreens.SplashDefault;
import views.ImportGuestListView;
import views.SplashscreenView;

import java.io.File;
import java.io.FileReader;

/**
 *   <p>Class to import a .CSV file for guests and add the content to the database<p>
 * @author Dennis Sloove
 * @version 0.1, November 2015
 *        
 */
public class ImportGuestListController {
    ImportGuestListView importGuestListView;
    private GuestDAO guestDAO;
    File importFile = null;
    boolean csvSelected = false, firstRow = true;
    CSVReader csvReader;
    private String title, context, header;
    private SplashscreenView splashScreenView;
    private ObservableList<Guest> data;
    private ScreensController screensController;

    final FileChooser fileChooser = new FileChooser();

    /**
     * Constructor
     *
     * @param importGuestListView Sets the importGuestListView
     * @param screensController   Sets the screenController
     * @param guestDAO            Sets the guestDAO
     */
    public ImportGuestListController(ImportGuestListView importGuestListView,
        ScreensController screensController, GuestDAO guestDAO) {
        this.guestDAO = guestDAO;
        this.screensController = screensController;
        this.importGuestListView = importGuestListView;
        generateHandlers();
    }

    // sets the lambda's for the buttons and triggers createTable

    /**
     * <p>Sets the Events for the buttons from importGuestListView</p>
     */
    public void generateHandlers() {
        createTable();
        importGuestListView.getImportButton().setOnAction(e -> {
            fireCsv();
        });
        importGuestListView.getUploadButton().setOnAction(e -> {
            submitGuests();
        });
    }

    /**
     * @param guestCsvSplash Sets SplashDefault
     *                       <p>
     *                       <p>
     *                       Gets the title/header/context properties from guestCsvSplash and creates a
     *                       splashscreenview with it, showing the message to the user
     *                       </p>
     */
    private void setSplashScreenView(SplashDefault guestCsvSplash) {
        context = "";
        title = "";
        header = "";
        title = guestCsvSplash.getTitleText();
        header = guestCsvSplash.getHeaderText();
        context = guestCsvSplash.getContextText();
        splashScreenView = new SplashscreenView(title, header, context);
    }

    /**
     * <p>
     * puts all data in the tableview ( so altered data goes through)
     * in the database
     * </p>
     */
    public void submitGuests() {
        int n = 0;
        while (data.size() > n) {
            this.guestDAO.addGuest(data.get(n));
            n++;
        }
        //informs the user the input to the database went succesfull
        SplashDefault guestCsvSplash = new SplashDefault();
        guestCsvSplash = new ImportSucceedMessage(guestCsvSplash);
        setSplashScreenView(guestCsvSplash);
        resetFields();
    }

    /**
     * <p>
     * Reads the data from a .CSV file, checks the columns,
     * and adds it to the table.
     * </p>
     */
    public void fireCsv() {
        //a new splashScreen to be able to show messages to the user
        SplashDefault guestCsvSplash = new SplashDefault();

        importFile = null;
        // prompts the user to choose a CSV file
        importFile = fileChooser.showOpenDialog(new Stage());
        // runs the file through the filechecker "isCSV"
        if (isCSV(importFile)) {
            try {
                // reads the file, chops the files up in parts
                String[] parts = null;
                String[] nextLine = null;
                //to make sure the firstrow doesnt get read
                firstRow = true;
                csvReader = new CSVReader(new FileReader(importFile));

                while ((nextLine = csvReader.readNext()) != null) {
                    // if function makes sure that the definition row isn't used
                    if (!firstRow) {
                        for (String s : nextLine) {
                            parts = s.split(";");
                        }
                    } else {
                        firstRow = false;
                    }
                    if (parts != null) {
                        //Checks if the rows are equal to the size of the object we want to make
                        if (parts.length == 12) {
              /*
							 * creates a new wine object based on the values pulled
							 * from the csv file the user selected
							 */
                            Guest selectedGuest =
                                new Guest(parts[0], parts[1], parts[2], parts[3], parts[4],
                                    parts[5], parts[6], parts[7], parts[8], parts[9], parts[10],
                                    parts[11]);
                            // puts the guest object in the arraylist
                            data.add(selectedGuest);
                            // reset parts and nextline to prevent errors
                            parts = null;
                            nextLine = null;
                        } else {
							/* informs the user that the input CSV does
							 * not contain the needed rows to generate a Guest Object
							 */
                            guestCsvSplash = new CsvIncorrectRowsGuestMessage(guestCsvSplash);
                            setSplashScreenView(guestCsvSplash);
                        }
                    }
                }
                // sets the arraylist used by the tableview as data, which we
                // just filled
                importGuestListView.getTable().setItems(data);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            //informs the user that the selected file is not a CSV file
            guestCsvSplash = new CsvIncorrectFileMessage(guestCsvSplash);
            setSplashScreenView(guestCsvSplash);
        }
    }

    ;

    /**
     * @param importFile File to check if it's a .CSV file
     * @return Returns true if the file is a .CSV, else returns false
     * <p>
     * <p>
     * checks if the file that is selected by the user is actually a .csv file
     * </p>
     */
    private boolean isCSV(File importFile) {
        csvSelected = false;
        if (importFile != null) {
            importGuestListView.getTable().getItems().clear();
            if (importFile.toString()
                .substring(importFile.toString().length() - 4, importFile.toString().length())
                .equals(".csv")) {
                csvSelected = true;
            }
        }

        return csvSelected;
    }

    @SuppressWarnings({"unchecked"})
    /**
     * <p>
     * 		creates the tableview columns.
     * </p>
     */ public void createTable() {
        data = FXCollections.observableArrayList();
        TableColumn<Guest, String> surnameCol = createColumn("Achternaam", "surname");
        surnameCol.setMinWidth(130);
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setSurname(t.getNewValue());
            }
        });
        TableColumn<Guest, String> infixCol = createColumn("Tussenvoegsel", "infix");
        infixCol.setMinWidth(110);
        infixCol.setCellFactory(TextFieldTableCell.forTableColumn());
        infixCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setInfix(t.getNewValue());
            }
        });
        TableColumn<Guest, String> firstnameCol = createColumn("Voornaam", "firstname");
        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setFirstname(t.getNewValue());
            }
        });
        TableColumn<Guest, String> salutationCol = createColumn("Aanhef", "salutation");
        salutationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        salutationCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setSalutation(t.getNewValue());
            }
        });
        TableColumn<Guest, String> streetCol = createColumn("Straat", "street");
        streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setStreet(t.getNewValue());
            }
        });
        TableColumn<Guest, String> streetnrCol = createColumn("Huis nr", "streetnr");
        streetnrCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetnrCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setStreetnr(t.getNewValue());
            }
        });
        TableColumn<Guest, String> zipcodeCol = createColumn("Postcode", "zipcode");
        zipcodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        zipcodeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setZipcode(t.getNewValue());
            }
        });
        TableColumn<Guest, String> cityCol = createColumn("Stad", "city");
        cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cityCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setCity(t.getNewValue());
            }
        });
        TableColumn<Guest, String> emailCol = createColumn("Email", "email");
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setEmail(t.getNewValue());
            }
        });
        TableColumn<Guest, String> phoneCol = createColumn("Telefoonnummer", "phone");
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setMinWidth(120);
        phoneCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setPhone(t.getNewValue());
            }
        });
        TableColumn<Guest, String> referralCol = createColumn("Geattendeerd door", "referal");
        referralCol.setMinWidth(140);
        referralCol.setCellFactory(TextFieldTableCell.forTableColumn());
        referralCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Guest, String>>() {
            // allows the user to edit the data in the column
            @Override public void handle(TableColumn.CellEditEvent<Guest, String> t) {
                ((Guest) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                    .setReferal(t.getNewValue());
            }
        });
        // prevents having duplicated columns in the table
        importGuestListView.getTable().getColumns().clear();
        importGuestListView.getTable().setItems(data);
        // add all columns to the table
        importGuestListView.getTable().getColumns()
            .addAll(surnameCol, infixCol, firstnameCol, salutationCol, streetCol, streetnrCol,
                zipcodeCol, cityCol, emailCol, phoneCol, referralCol);

    }

    /**
     * @param title        Column title
     * @param propertyName Column property name
     * @return Returns a table column
     * <p>
     * <p>
     * Used to create tablecolumns.
     * </p>
     */
    private <S, T> TableColumn<S, T> createColumn(String title, String propertyName) {
        TableColumn<S, T> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return col;
    }

    /**
     * <p>
     * Removes a view and creates a new one to empty the content.
     * </p>
     */
    public void resetFields() {
        screensController.screenRemove(ControllersController.getATTENDANCEID());
        this.importGuestListView = new ImportGuestListView();
        screensController
            .screenLoadSet(ControllersController.getATTENDANCEID(), importGuestListView);
        generateHandlers();
    }
}
