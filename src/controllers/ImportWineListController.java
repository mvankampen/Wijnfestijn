package controllers;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVReader;

import DAO.WineDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.OrderLine;
import models.Wine;
import views.ImportWineListView;

public class ImportWineListController {
	private ScreensController screensController;
	ImportWineListView importWineListView;
	private WineDAO wineDAO;
	File importFile = null;
	boolean csvSelected = false, firstRow = true;
	CSVReader csvReader;
    private ObservableList<Wine> data;
	ArrayList<Wine> wineList;
	
	final FileChooser fileChooser = new FileChooser();
	
	public ImportWineListController(ImportWineListView importWineListView, ScreensController screensController, WineDAO wineDAO){
		this.wineDAO = wineDAO;
		this.screensController = screensController;
		this.importWineListView = importWineListView;
		generateHandlers();
		createTable();
	}

	public void generateHandlers(){
		importWineListView.getImportButton().setOnAction(e -> {
			fireCsv();
			});
	}
	public void fireCsv(){
		importFile = fileChooser.showOpenDialog(new Stage());
		if(isCSV(importFile)){
			try {
				wineList = new ArrayList<Wine>();
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
						Wine testWine = new Wine(
								parts[0], parts[1], parts[2],
								Double.parseDouble(parts[3]), parts[4], parts[5],
								parts[6], Double.parseDouble(parts[7]));
						wineList.add(testWine);
						data.add(testWine);
						
						System.out.println(data);
					parts = null;
					nextLine = null;
					}
				}
				importWineListView.getTable().setItems(data);
				wineDAO.insertAllWines(data);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createTable(){
        data = FXCollections.observableArrayList();
		TableColumn<Wine, String> nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Wine, String>("name"));
		
		TableColumn<Wine, String> publisherCol = new TableColumn("Publisher");
		publisherCol.setCellValueFactory(new PropertyValueFactory<Wine, String>("publisher"));
		
		TableColumn<Wine, String> yearCol = new TableColumn("Year");
		yearCol.setCellValueFactory(new PropertyValueFactory<Wine, String>("year"));
		
		TableColumn<Wine, Double> priceCol = new TableColumn("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<Wine, Double>("price"));
		
		TableColumn rankCol = new TableColumn("Rank");
		rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
		
		TableColumn categoryCol = new TableColumn("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		
		TableColumn typeCol = new TableColumn("Type");
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		TableColumn costpriceCol = new TableColumn("Cost price");
		costpriceCol.setCellValueFactory(new PropertyValueFactory<>("costprice"));
		importWineListView.getTable().getColumns().addAll(
													nameCol, publisherCol, yearCol, priceCol,
													rankCol, categoryCol, typeCol, costpriceCol
												);
	}
}
