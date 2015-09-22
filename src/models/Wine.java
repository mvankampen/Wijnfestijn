package models;

/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class Wine {
    private String name;
    private String sort;
    private String publisher;
    private int year;
    private String category;
    private double price;
    private int rank;

    public Wine(String name, String sort, String publisher, int year, String category, double price, int rank) {
        this.name = name;
        this.sort = sort;
        this.publisher = publisher;
        this.year = year;
        this.category = category;
        this.price = price;
        this.rank = rank;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
}
