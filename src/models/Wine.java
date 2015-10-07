package models;


import java.util.Date;

/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class Wine {
    private String name;
    private Date insertDate;
    private String sort;
    private String publisher;
    private int year;
    private String category;
    private double price;
    private int rank;
    private boolean active = false;

    public Wine() {
        this.insertDate = new Date();
        this.active = true;
    }

    public String getName() {
        return name;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public String getSort() {
        return sort;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getRank() {
        return rank;
    }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
