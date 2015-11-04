package controllers;

import views.HomeView;

public class HomeController {
	HomeView homeView;
	private ScreensController screensController;
	private ControllersController CC;
	
	public HomeController(HomeView homeView, ScreensController screensController, ControllersController controllersController){
		this.screensController = screensController;
		this.CC = controllersController;
		this.homeView = homeView;
		addHyperlinkEventHandling();
	}
	//sets all the correct links to the corresponding hyperlinks
	public void addHyperlinkEventHandling(){
		homeView.getOrderLink1().setOnAction(event -> {
			
		});
		homeView.getOrderLink2().setOnAction(event -> {
			CC.getOrderController().resetFields();
		});
		homeView.getOrderLink3().setOnAction(event -> {
			CC.getImportWineListController().resetFields();
		});
		homeView.getOrderLink4().setOnAction(event -> {
		});
		homeView.getMailLink1().setOnAction(event -> {
			CC.getMailController().resetFields();
		});
		homeView.getCustomerLink1().setOnAction(event -> {
			CC.getAdjustGuestControler().resetFields();
		});
		homeView.getCustomerLink2().setOnAction(event -> {
			CC.getRegistrationController().resetFields();
		});
		homeView.getCustomerLink3().setOnAction(event -> {
			CC.getDebtorsController().resetFields();
		});
		homeView.getSettingsLink1().setOnAction(event -> {
			screensController.screenSet(ControllersController.getSETTINGSID());
		});
	}
}
