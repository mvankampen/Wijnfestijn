package controllers;

import DAO.WineDAO;
import com.opencsv.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Wine;
import splashscreens.CsvIncorrectFileMessage;
import splashscreens.CsvIncorrectRowsWineMessage;
import splashscreens.ImportSucceedMessage;
import splashscreens.SplashDefault;
import views.ImportWineListView;
import views.SplashscreenView;

import java.io.File;
import java.io.FileReader;

/**
 * @author Dennis Sloove
 * @version 0.1, November 2015
 *          Description:
 *          Class to import a .CSV file for wines and add the content to the database
 */
public class ImportWineListController {
    ImportWineListView importWineListView;
    private WineDAO wineDAO;

    File importFile = null;
    boolean csvSelected = false, firstRow = true;
    CSVReader csvReader;
    private ObservableList<Wine> data;
    private ScreensController screensController;
    private String title, header, context;
    private SplashscreenView splashScreenView;

    final FileChooser fileChooser = new FileChooser();

    /**
     * @param importWineListView Sets the importWineListView
     * @param screensController  Sets the screensController
     * @param wineDAO            Sets the wineDAO
     */
    public ImportWineListController(ImportWineListView importWineListView,
        ScreensController screensController, WineDAO wineDAO) {
        this.wineDAO = wineDAO;
        this.screensController = screensController;
        this.importWineListView = importWineListView;
        generateHandlers();
    }

    // sets the lambda's for the buttons

    /**
     * <p>Sets the Events for the buttons from importGuestListView</p>
     */
    public void generateHandlers() {
        createTable();
        importWineListView.getImportButton().setOnAction(e -> {
            fireCsv();
        });
        importWineListView.getUploadButton().setOnAction(e -> {
            submitWines();
        });
    }

    /**
     * <p>
     * puts all data in the tableview ( so altered data goes through)
     * in the database
     * </p>
     */
    public void submitWines() {
        wineDAO.insertAllWines(data);
        SplashDefault wineCsvSplash = new SplashDefault();
        wineCsvSplash = new ImportSucceedMessage(wineCsvSplash);
        setSplashScreenView(wineCsvSplash);
        resetFields();
    }

    /**
     * @param guestCsvSplash Sets SplashDefault
     *                       <p>
     *                       <p>
     *                       Gets the title/header/context properties from guestCsvSplash and creates a
     *                       splashscreenview with it, showing the message to the user
     *                       </p>
     */
    private void setSplashScreenView(SplashDefault wineCsvSplash) {
        context = "";
        title = "";
        header = "";
        title = wineCsvSplash.getTitleText();
        header = wineCsvSplash.getHeaderText();
        context = wineCsvSplash.getContextText();
        splashScreenView = new SplashscreenView(title, header, context);
    }

    /**
     * <p>
     * Reads the data from a .CSV file, checks the columns,
     * and adds it to the table.
     * </p>
     */
    public void fireCsv() {
        //a new splashScreen to be able to show messages to the user
        SplashDefault wineCsvSplash = new SplashDefault();

        importFile = null;
        // prompts the user to choose a CSV file
        importFile = fileChooser.showOpenDialog(new Stage());
        // runs the file through the filechecker "isCSV", continues if the file
        // is a csv
        if (isCSV(importFile)) {
            // reads the file, chops the files up in parts
            try {
                String[] parts = null;
                //to make sure the firstrow doesnt get read
                firstRow = true;
                csvReader = new CSVReader(new FileReader(importFile));
                String[] nextLine;
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
                        if (parts.length == 9) {
              /*
							 * creates a new wine object based on the values pulled
							 * from the csv file the user selected
							 */
                            Wine testWine =
                                new Wine(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]),
                                    parts[4], parts[5], parts[6], Double.parseDouble(parts[7]),
                                    Double.parseDouble(parts[8]));
                            // adds the wine object to the arraylist
                            data.add(testWine);
                            // reset parts and nextline to prevent errors
                            parts = null;
                            nextLine = null;
                        } else {
							/*informs the user that the input CSV 
							 * does not contain the rows needed to generate a Wine object
							 */
                            wineCsvSplash = new CsvIncorrectRowsWineMessage(wineCsvSplash);
                            setSplashScreenView(wineCsvSplash);
                        }
                    }
                }
                // sets the arraylist used by the tableview as data, which we
                // just filled
                importWineListView.getTable().setItems(data);


            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (csvSelected == false) {
            //informs the user that the input is not a CSV file
            wineCsvSplash = new CsvIncorrectFileMessage(wineCsvSplash);
            setSplashScreenView(wineCsvSplash);

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
            importWineListView.getTable().getItems().clear();
            if (importFile.toString()
                .substring(importFile.toString().length() - 4, importFile.toString().length())
                .equals(".csv")) {
                csvSelected = true;
            }
        }

        return csvSelected;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    /**
     * <p>
     * 		creates the tableview columns.
     * </p>
     */ public void createTable() {
        data = FXCollections.observableArrayList();
        TableColumn<Wine, String> nameCol = new TableColumn("Naam");
        nameCol.setCellValueFactory(new PropertyValueFactory<Wine, String>("name"));

        TableColumn<Wine, String> publisherCol = new TableColumn("Uitgever");
        publisherCol.setCellValueFactory(new PropertyValueFactory<Wine, String>("publisher"));

        TableColumn<Wine, String> yearCol = new TableColumn("Jaar");
        yearCol.setCellValueFactory(new PropertyValueFactory<Wine, String>("year"));

        TableColumn<Wine, Double> priceCol = new TableColumn("Prijs");
        priceCol.setCellValueFactory(new PropertyValueFactory<Wine, Double>("price"));

        TableColumn rankCol = new TableColumn("Rank");
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));

        TableColumn categoryCol = new TableColumn("Categorie");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn costpriceCol = new TableColumn("Kost prijs");
        costpriceCol.setCellValueFactory(new PropertyValueFactory<>("costprice"));
        // add all columns to the table

        TableColumn marginCol = new TableColumn("Margin");
        marginCol.setCellValueFactory(new PropertyValueFactory<>("margin"));

        importWineListView.getTable().getColumns()
            .addAll(nameCol, publisherCol, yearCol, priceCol, rankCol, categoryCol, typeCol,
                costpriceCol, marginCol);


    }

    /**
     * <p>
     * Removes a view and creates a new one to empty the content.
     * </p>
     */
    public void resetFields() {
        screensController.screenRemove(ControllersController.getATTENDANCEID());
        this.importWineListView = new ImportWineListView();
        screensController
            .screenLoadSet(ControllersController.getATTENDANCEID(), importWineListView);
        generateHandlers();
    }
}
