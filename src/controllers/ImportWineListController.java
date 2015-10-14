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

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Wine;
import views.ImportWineListView;

public class ImportWineListController {
	private ScreensController screensController;
	ImportWineListView importWineListView;
	private boolean csvSelected = false, lineDone = false;
	File importFile;
	String CSVTextPath = "src/templates/WINELIST.txt";
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
				readCSV(importFile);
			}
		});
	}
	
	public boolean isCSV(File importFile){
		if(importFile.toString().substring(importFile.toString().length() -4, importFile.toString().length()).equals(".csv")){
			csvSelected = true;
		}
		return csvSelected;
	}
	
	public void readCSV(File importFile){
		try {
			wineList = new ArrayList<Wine>();
			ArrayList<Object> list = new ArrayList<Object>();
			
			int count = 0, count2 = 0;
			boolean firstLine = true;
			String line = null;
			Scanner scanner = new Scanner(importFile);
			scanner.useDelimiter(";");
			String temp = null;
			
			while(scanner.hasNext()){
				line = null;
				count++;
				line = scanner.next();
				if(count >= 8){
					count2++;
					if(!line.contains("\n")){
						list.add(line);
					}
					else{
						Object[] parts = line.split("\n");
						if(parts.length == 2){
							if(firstLine){
								firstLine = false;
								list.add(parts[1].toString());
							}
							else{
								list.add(parts[0].toString());
							}
						}	
						else{
							list.add(parts[0].toString());
						}
					}
				}
				if(count2 >= 8){
					for(Object o : list){
						System.out.println(count2 + " " + o);
					}
					count2 = 0;
					
					wineList.add(new Wine(
							list.get(0).toString(), list.get(1).toString(), list.get(2).toString(),
							Double.parseDouble(list.get(3).toString()), list.get(4).toString(), list.get(5).toString(),
							list.get(6).toString(), Double.parseDouble(list.get(7).toString())
					));
					list = new ArrayList<Object>();
				}
			}
			for(Wine w : wineList){
				System.out.println(w.getName());
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
