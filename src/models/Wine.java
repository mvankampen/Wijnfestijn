package models;


/**
 * Created by Sander de Jong on 21-9-2015.
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
    
    public Wine(String name, String publisher, String year, double price, String rank, String category, String type, double costprice){
    	this.name = name;
    	this.publisher = publisher;
    	this.year = year;
    	this.price = price;
    	this.rank = rank;
    	this.category = category;
    	this.type = type;
    	this.costprice = costprice;
    }
    public Wine(String name, String publisher, String year, double price, String rank, String category, String type, double costprice, double margin){
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
    
    public Wine(int id, String name, String category, String publisher, String type, String year, double price, String rank, Double costprice, Double margin) {
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getCategory() {
        return category;
    }

    public String getPublisher() {
        return publisher;
    }

    public Double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getYear() {
        return year;
    }


    public String getRank() {
        return rank;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

	public Double getCostprice() {
		return costprice;
	}

	public void setCostprice(Double costprice) {
		this.costprice = costprice;
	}

	public Double getMargin() {
		return margin;
	}

	public void setMargin(Double margin) {
		this.margin = margin;
	}
}