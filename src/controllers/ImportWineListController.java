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

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Wine;
import views.ImportWineListView;

public class ImportWineListController {
	private ScreensController screensController;
	ImportWineListView importWineListView;
	File importFile = null;
	boolean csvSelected = false, firstRow = true;
	CSVReader csvReader;
	ArrayList<Wine> wineList;
	
	final FileChooser fileChooser = new FileChooser();
	
	public ImportWineListController(ImportWineListView importWineListView, ScreensController screensController){
		this.screensController = screensController;
		this.importWineListView = importWineListView;
		generateHandlers();
	}

	public void generateHandlers(){
		importWineListView.importButton.setOnAction(e -> {
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
							wineList.add(new Wine(
									parts[0], parts[1], parts[2],
									Double.parseDouble(parts[3]), parts[4], parts[5],
									parts[6], Double.parseDouble(parts[7])
							));
						parts = null;
						nextLine = null;
						}
					}
					for(Wine w : wineList){
						//importWineListView.wineListArea.appendText((w.getName() + " | " + w.getPublisher() + " | " + w.getYear() + " | " + w.getPrice() + " | " + w.getRank() + " | " + w.getCategory() + " | " + w.getType() + " | " + w.getCostprice()) + "\n");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else{
				System.out.println("Dit is geen .CSV bestand");
			}
		});
	}
	
	public boolean isCSV(File importFile){
		if(importFile.toString().substring(importFile.toString().length() -4, importFile.toString().length()).equals(".csv")){
			csvSelected = true;
		}
		return csvSelected;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createTableHeaders(){
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn publisherCol = new TableColumn("Publisher");
		publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		
		TableColumn yearCol = new TableColumn("Year");
		yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
		
		TableColumn priceCol = new TableColumn("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn rankCol = new TableColumn("Rank");
		rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
		
		TableColumn categoryCol = new TableColumn("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		
		TableColumn typeCol = new TableColumn("Type");
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		TableColumn costpriceCol = new TableColumn("Cost price");
		costpriceCol.setCellValueFactory(new PropertyValueFactory<>("cost price"));
		
		importWineListView.table.getColumns().addAll(
													nameCol, publisherCol, yearCol, priceCol,
													rankCol, categoryCol, typeCol, costpriceCol
												);
	}
}
