package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

/**
 * <P>Home view generates an output presentation to the user based on changes in the model. This view is a quick crossing to all pages within the system </P>
 *
 * @author Dennis de Sloove
 * @version 0.1, november 2015
 */

public class HomeView extends AnchorPane implements ControlledScreen {

    private Label orderlistTitle, mailTitle;
    private Hyperlink orderLink1, orderLink2, orderLink3, orderLink4;
    private Label customerTitle, settingsTitle;
    private Hyperlink mailLink1, customerLink1, customerLink2, customerLink3, settingsLink1;

    public void setScreenController(ScreensController screensController) {
    /*
     * Used for registering itself in the hashMap of the ScreensController
		 * to enable navigation
		 */
    }

    /**
     * <P>Default Constructor</P>
     */
    public HomeView() {
        // calling the methods that initialize different aspects of the view
        createView();
        setUpContentPane();
    }

    /**
     * <P>Setting up the display, add style class and sets the fixed height to the screen</P>
     */
    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);

    }

    /**
     * <P>Setting up the Content Pane</P>
     */
    public void setUpContentPane() {
        //making both the hbox and options pane
        HBox introBox = new HBox();
        introBox.setLayoutY(200);
        introBox.setLayoutX(100);
        GridPane optionsPane = new GridPane();
        optionsPane.setLayoutY(200);
        optionsPane.setHgap(100);
        optionsPane.setVgap(10);
    /*
		 * creating all Labels, used to instruct the user towards what actions
		 * he can perform within the view
		 */
        Label introLabel = new Label("Dit menu is voorzien van directe links naar alle functies."
            + " Klik op de actie die u wilt ondernemen:");
        orderlistTitle = new Label("Bestellijst opties");
		/*
		 * creating all hyperlinks which are used for the textflow objects, this
		 * is so we put a eventhandler on the desired hyper link (used for the
		 * navigation)
		 */
        orderlistTitle.getStyleClass().add("settings_title");
        setOrderLink1(new Hyperlink("Printen persoonlijke bestellijsten"));
        getOrderLink1().getStyleClass().add("default_hyperlink");
        setOrderLink2(
            new Hyperlink("Invoeren van de persoonlijke bestellijsten (maakt orders en facturen)"));
        getOrderLink2().getStyleClass().add("default_hyperlink");
        setOrderLink3(
            new Hyperlink("Maak een nieuwe bestellijst aan (doormiddel van een .CSV bestand)"));
        getOrderLink3().getStyleClass().add("default_hyperlink");
        setOrderLink4(new Hyperlink("Bewerk of verwijder een bestaande bestellijst"));
        getOrderLink4().getStyleClass().add("default_hyperlink");
        mailTitle = new Label("Mail opties");
        mailTitle.getStyleClass().add("settings_title");
        setMailLink1(new Hyperlink("Maak en verstuur een e-mail"));
        getMailLink1().getStyleClass().add("default_hyperlink");
        customerTitle = new Label("Klanten opties");
        customerTitle.getStyleClass().add("settings_title");
        setCustomerLink1(new Hyperlink("Bewerk of verwijder bestaande klanten"));
        getCustomerLink1().getStyleClass().add("default_hyperlink");
        setCustomerLink2(new Hyperlink("Open het klanten registreren scherm"));
        getCustomerLink2().getStyleClass().add("default_hyperlink");
        setCustomerLink3(new Hyperlink("Onbetaalde rekeningen"));
        getCustomerLink3().getStyleClass().add("default_hyperlink");
        settingsTitle = new Label("Instellingen");
        settingsTitle.getStyleClass().add("settings_title");
        setSettingsLink1(new Hyperlink("Wijzig uw instellingen"));
        getSettingsLink1().getStyleClass().add("default_hyperlink");

		/*
		 * creating all textFlow objects, these are used instead of labels
		 * because labels dont allow lambda event handling, this is a work
		 * around
		 */
        TextFlow orderFlow1 = new TextFlow(getOrderLink1());
        TextFlow orderFlow2 = new TextFlow(getOrderLink2());
        TextFlow orderFlow3 = new TextFlow(getOrderLink3());
        TextFlow orderFlow4 = new TextFlow(getOrderLink4());
        TextFlow mailFlow1 = new TextFlow(getMailLink1());
        TextFlow customerFlow1 = new TextFlow(getCustomerLink1());
        TextFlow customerFlow2 = new TextFlow(getCustomerLink2());
        TextFlow customerFlow3 = new TextFlow(getCustomerLink3());
        TextFlow settingsFlow1 = new TextFlow(getSettingsLink1());
        //adding all textflows and titles to the options pane
        optionsPane.add(new Label(), 1, 1);
        optionsPane.add(orderlistTitle, 1, 2);
        optionsPane.add(orderFlow1, 1, 3);
        optionsPane.add(orderFlow2, 1, 4);
        optionsPane.add(orderFlow3, 1, 5);
        optionsPane.add(orderFlow4, 1, 6);
        optionsPane.add(mailTitle, 1, 7);
        optionsPane.add(mailFlow1, 1, 8);
        optionsPane.add(customerTitle, 2, 2);
        optionsPane.add(customerFlow1, 2, 3);
        optionsPane.add(customerFlow2, 2, 4);
        optionsPane.add(customerFlow3, 2, 5);
        optionsPane.add(settingsTitle, 2, 7);
        optionsPane.add(settingsFlow1, 2, 8);
        //adding the introduction label
        introBox.getChildren().add(introLabel);
        getChildren().addAll(introBox, optionsPane);
    }

    /**
     * @return Hyperlink object OrderLink1
     */
    public Hyperlink getOrderLink1() {
        return orderLink1;
    }

    /**
     * @param orderLink1
     */
    public void setOrderLink1(Hyperlink orderLink1) {
        this.orderLink1 = orderLink1;
    }

    /**
     * @return Hyperlink object OrderLink2
     */
    public Hyperlink getOrderLink2() {
        return orderLink2;
    }

    /**
     * @param orderLink2
     */
    public void setOrderLink2(Hyperlink orderLink2) {
        this.orderLink2 = orderLink2;
    }

    /**
     * @return Hyperlink object OrderLink3
     */
    public Hyperlink getOrderLink3() {
        return orderLink3;
    }

    /**
     * @param orderLink3
     */
    public void setOrderLink3(Hyperlink orderLink3) {
        this.orderLink3 = orderLink3;
    }

    /**
     * @return Hyperlink object OrderLink4
     */
    public Hyperlink getOrderLink4() {
        return orderLink4;
    }

    /**
     * @param orderLink4
     */
    public void setOrderLink4(Hyperlink orderLink4) {
        this.orderLink4 = orderLink4;
    }

    /**
     * @return Hyperlink object MailLink1
     */
    public Hyperlink getMailLink1() {
        return mailLink1;
    }

    /**
     * @param mailLink1
     */
    public void setMailLink1(Hyperlink mailLink1) {
        this.mailLink1 = mailLink1;
    }

    /**
     * @return Hyperlink object CustomerLink1
     */
    public Hyperlink getCustomerLink1() {
        return customerLink1;
    }

    /**
     * @param customerLink1
     */
    public void setCustomerLink1(Hyperlink customerLink1) {
        this.customerLink1 = customerLink1;
    }

    /**
     * @return Hyperlink object CustomerLink2
     */
    public Hyperlink getCustomerLink2() {
        return customerLink2;
    }

    /**
     * @param customerLink2
     */
    public void setCustomerLink2(Hyperlink customerLink2) {
        this.customerLink2 = customerLink2;
    }

    /**
     * @return Hyperlink object CustomerLink3
     */
    public Hyperlink getCustomerLink3() {
        return customerLink3;
    }

    /**
     * @param customerLink3
     */
    public void setCustomerLink3(Hyperlink customerLink3) {
        this.customerLink3 = customerLink3;
    }

    /**
     * @return Hyperlink object SettingsLink1
     */
    public Hyperlink getSettingsLink1() {
        return settingsLink1;
    }

    /**
     * @param settingsLink1
     */
    public void setSettingsLink1(Hyperlink settingsLink1) {
        this.settingsLink1 = settingsLink1;
    }
}
