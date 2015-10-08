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

	public void addHyperlinkEventHandling(){
		// Setting the directions for the hyperlinks on row 1
		homeView.orderlistLink.setOnAction(event -> {
			screensController.screenSet(ControllersController.getMAILID());
		});
		homeView.orderLink1.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.orderLink2.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.orderLink3.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.orderLink4.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.debtorsTitle.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.debtorLink1.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		
		// Setting the directions for the hyperlinks row 2
		homeView.mailTitleLink.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.mailLink1.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.mailLink2.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.mailLink3.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.customerTitleLink.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.customerLink1.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
		homeView.customerLink2.setOnAction(event -> {
			// no link defined yet, placeholder
			// screensController.screenSet(Applet.getMailid());
		});
	}
}
