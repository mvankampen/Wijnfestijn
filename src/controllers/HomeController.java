package controllers;

import views.HomeView;

/**
 * <p>Provides an easy interface to navigate to the  many different functions the application has.</p>
 *
 * @author Dennis Sloove
 * @version 0.1, november 2015
 */
public class HomeController {
    private HomeView homeView;
    private ScreensController screensController;
    private ControllersController CC;

    /**
     * @param homeView              The view with all the different links to the different functions within the application.
     * @param screensController     Used to direct the different windows within the application.
     * @param controllersController Controller that controls all the controllers.
     */
    public HomeController(HomeView homeView, ScreensController screensController,
        ControllersController controllersController) {
        this.screensController = screensController;
        this.CC = controllersController;
        this.homeView = homeView;
        addHyperlinkEventHandling();
    }

    /**
     * <p>Sets all the correct links to the corresponding hyperlinks.</p>
     */
    public void addHyperlinkEventHandling() {
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
