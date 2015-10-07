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
    private Double price;
    private String rank;
    private boolean active;


    public Wine(int id, String name, String category, String publisher, String type, String year, double price, String rank) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.publisher = publisher;
        this.type = type;
        this.year = year;
        this.price = price;
        this.rank = rank;
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
}