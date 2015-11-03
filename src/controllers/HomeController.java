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
		homeView.getOrderLink1().setOnAction(event -> {
			screensController.screenSet(ControllersController.getORDERLISTPRINTID());
		});
		homeView.getOrderLink2().setOnAction(event -> {
			screensController.screenSet(ControllersController.getORDERID());
		});
		homeView.getOrderLink3().setOnAction(event -> {
			screensController.screenSet(ControllersController.getIMPORTWINELISTID());
		});
		homeView.getOrderLink4().setOnAction(event -> {
		});
		homeView.getMailLink1().setOnAction(event -> {
			screensController.screenSet(ControllersController.getMAILID());
		});
		homeView.getCustomerLink1().setOnAction(event -> {
			screensController.screenSet(ControllersController.getGUESTID());
		});
		homeView.getCustomerLink2().setOnAction(event -> {
			screensController.screenSet(ControllersController.getREGISTRATIONID());
		});
		homeView.getCustomerLink3().setOnAction(event -> {
			screensController.screenSet(ControllersController.getDEBTORID());
		});
		homeView.getSettingsLink1().setOnAction(event -> {
			screensController.screenSet(ControllersController.getSETTINGSID());
		});
	}
}
