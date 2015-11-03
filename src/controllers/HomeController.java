package controllers;

import views.HomeView;

public class HomeController {
	HomeView homeView;
	private ScreensController screensController;
	
	public HomeController(HomeView homeView, ScreensController screensController){
		this.screensController = screensController;
		this.homeView = homeView;
		addHyperlinkEventHandling();
	}
	//sets all the correct links to the corresponding hyperlinks
	public void addHyperlinkEventHandling(){
		homeView.orderLink1.setOnAction(event -> {
			screensController.screenSet(ControllersController.getORDERLISTPRINTID());
		});
		homeView.orderLink2.setOnAction(event -> {
			screensController.screenSet(ControllersController.getORDERID());
		});
		homeView.orderLink3.setOnAction(event -> {
			screensController.screenSet(ControllersController.getIMPORTWINELISTID());
		});
		homeView.orderLink4.setOnAction(event -> {
		});
		homeView.mailLink1.setOnAction(event -> {
			screensController.screenSet(ControllersController.getMAILID());
		});
		homeView.customerLink1.setOnAction(event -> {
			screensController.screenSet(ControllersController.getGUESTID());
		});
		homeView.customerLink2.setOnAction(event -> {
			screensController.screenSet(ControllersController.getREGISTRATIONID());
		});
		homeView.customerLink3.setOnAction(event -> {
			screensController.screenSet(ControllersController.getDEBTORID());
		});
		homeView.settingsLink1.setOnAction(event -> {
			screensController.screenSet(ControllersController.getSETTINGSID());
		});
	}
}
