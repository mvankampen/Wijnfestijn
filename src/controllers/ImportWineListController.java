package controllers;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import com.opencsv.CSVReader;

import DAO.WineDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Wine;
import views.ImportWineListView;

public class ImportWineListController {
	ImportWineListView importWineListView;
	private WineDAO wineDAO;

	File importFile = null;
	boolean csvSelected = false, firstRow = true;
	CSVReader csvReader;
	private ObservableList<Wine> data;

	final FileChooser fileChooser = new FileChooser();

	public ImportWineListController(ImportWineListView importWineListView, ScreensController screensController,
			WineDAO wineDAO) {
		this.wineDAO = wineDAO;
		this.importWineListView = importWineListView;
		generateHandlers();
		createTable();
	}

	public void generateHandlers() {
		importWineListView.getImportButton().setOnAction(e -> {
			fireCsv();
		});
		importWineListView.getUploadButton().setOnAction(e -> {
			submitWines();
		});
	}

	public void submitWines() {
		wineDAO.insertAllWines(data);
	}

	public void fireCsv() {
		// prompts the user to choose a CSV file
		importFile = fileChooser.showOpenDialog(new Stage());
		// runs the file through the filechecker "isCSV", continues if the file
		// is a csv
		if (isCSV(importFile)) {
			// reads the file, chops the files up in parts
			try {
				String[] parts = null;
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
						/*
						 * creates a new wine object based on the values pulled
						 * from the csv file the user selected
						 */
						Wine testWine = new Wine(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4],
								parts[5], parts[6], Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
						// adds the wine object to the arraylist
						data.add(testWine);
						// reset parts and nextline to prevent errors
						parts = null;
						nextLine = null;
					}
				}
				// sets the arraylist used by the tableview as data, which we
				// just filled
				importWineListView.getTable().setItems(data);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		//error handling incase the file is not a CSV file
		else {
			System.out.println("Dit is geen .CSV bestand");
		}
	};
	// checks if the file that is selected by the user is actually a .csv file
	public boolean isCSV(File importFile) {
		if (importFile.toString().substring(importFile.toString().length() - 4, importFile.toString().length())
				.equals(".csv")) {
			csvSelected = true;
		}
		return csvSelected;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	//creates the tableview columns
	public void createTable() {
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

		TableColumn costpriceCol = new TableColumn("Kost price");
		costpriceCol.setCellValueFactory(new PropertyValueFactory<>("costprice"));
		//add all columns to the table
		importWineListView.getTable().getColumns().addAll(nameCol, publisherCol, yearCol, priceCol, rankCol,
				categoryCol, typeCol, costpriceCol);
	}
}
