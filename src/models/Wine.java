package models;


/**
 * @author Michael van Kampen
 * @author Sander de Jong
 * @version 0.1, november 2015
 *          Description:
 *          Wine class holds all the information about the Wine.
 */
public class Wine {
    private int id;
    private String name;
    private String category;
    private String publisher;
    private String type;
    private String year;
    private String rank;
    private Double price;
    private Double costprice;
    private Double margin;
    private boolean active;

    /**
     * <P>Constructor</P>
     *
     * @param name      of the wine
     * @param publisher of the wine
     * @param year      of the wine
     * @param price     of the wine
     * @param rank      of the wine
     * @param category  of the wine
     * @param type      of the wine
     * @param costprice of the wine
     * @param margin    of the wine
     */
    public Wine(String name, String publisher, String year, double price, String rank,
        String category, String type, double costprice, double margin) {
        this.name = name;
        this.publisher = publisher;
        this.year = year;
        this.price = price;
        this.rank = rank;
        this.category = category;
        this.type = type;
        this.costprice = costprice;
        this.margin = margin;
    }

    /**
     * <P>Constructor</P>
     *
     * @param id        of the wine
     * @param name      of the wine
     * @param category  of the wine
     * @param publisher of the wine
     * @param type      of the wine
     * @param year      of the wine
     * @param price     of the wine
     * @param rank      of the wine
     * @param costprice of the wine
     * @param margin    of the wine
     */
    public Wine(int id, String name, String category, String publisher, String type, String year,
        double price, String rank, Double costprice, Double margin) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.publisher = publisher;
        this.type = type;
        this.year = year;
        this.price = price;
        this.rank = rank;
        this.setCostprice(costprice);
        this.setMargin(margin);
    }

    /**
     * @return ID of the wine
     */
    public int getId() {
        return id;
    }

    /**
     * @return Name of the wine
     */
    public String getName() {
        return name;
    }

    /**
     * @return Category of the wine
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return Publisher of the wine
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @return Price of the wine
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @return Type of the wine
     */
    public String getType() {
        return type;
    }

    /**
     * @return Year of the wine
     */
    public String getYear() {
        return year;
    }

    /**
     * @return Rank of the wine
     */
    public String getRank() {
        return rank;
    }

    /**
     * @return Active boolean of the wine
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @return Cost price of the wine
     */
    public Double getCostprice() {
        return costprice;
    }

    /**
     * @param costprice of the wine
     */
    public void setCostprice(Double costprice) {
        this.costprice = costprice;
    }

    /**
     * @return Margin of the wine
     */
    public Double getMargin() {
        return margin;
    }

    /**
     * @param margin of the wine
     */
    public void setMargin(Double margin) {
        this.margin = margin;
    }
}
