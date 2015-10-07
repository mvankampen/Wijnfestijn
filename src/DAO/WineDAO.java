package DAO;

import models.Wine;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class WineDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public WineDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Wine> getAllWine() {
        List<Wine> wineList = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM wine";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Wine wine = new Wine();
                wine.setName(resultSet.getString("wine_name"));
                wine.setInsertDate(resultSet.getDate("wine_insert_date"));
                wine.setSort(resultSet.getString("wine_sort"));
                wine.setPublisher(resultSet.getString("wine_publisher"));
                wine.setYear(resultSet.getInt("wine_year"));
                wine.setCategory(resultSet.getString("wine_category"));
                wine.setPrice(resultSet.getDouble("wine_price"));
                wine.setRank(resultSet.getInt("wine_rank"));
                wine.setActive(resultSet.getBoolean("wine_active"));
                wineList.add(wine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.preparedStatement != null) {
                    preparedStatement.close();
                }
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return wineList;
    }

    public Wine getWineById(int wineID) {
        Wine wine = null;
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM wine WHERE wine_id = ?";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, wineID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                wine = new Wine();
                wine.setName(resultSet.getString("wine_name"));
                wine.setInsertDate(resultSet.getDate("wine_insert_date"));
                wine.setSort(resultSet.getString("wine_sort"));
                wine.setPublisher(resultSet.getString("wine_publisher"));
                wine.setYear(resultSet.getInt("wine_year"));
                wine.setCategory(resultSet.getString("wine_category"));
                wine.setPrice(resultSet.getDouble("wine_price"));
                wine.setRank(resultSet.getInt("wine_rank"));
                wine.setActive(resultSet.getBoolean("wine_active"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.preparedStatement != null) {
                    preparedStatement.close();
                }
                if (!this.connection.isClosed()) {
                    this.connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return wine;
    }

    public void insertAllWine(List<Wine> wines) {
        try {
            this.preparedStatement = null;
            String sqlQuery = "INSERT INTO wine"
                + "(wine_name, wine_insert_date, wine_sort, wine_publisher, wine_year, wine_category, wine_price, wine_rank, wine_active) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ? ,?)";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);

            for (int i = 0; i < wines.size(); i++) {
                preparedStatement.setString(1, wines.get(i).getName());
                java.util.Date date =  wines.get(i).getInsertDate();
                Date sqlDate = new Date(date.getTime());
                preparedStatement.setDate(2,  sqlDate);
                preparedStatement.setString(3, wines.get(i).getSort());
                preparedStatement.setString(4, wines.get(i).getPublisher());
                preparedStatement.setInt(5, wines.get(i).getYear());
                preparedStatement.setString(6, wines.get(i).getCategory());
                preparedStatement.setDouble(7, wines.get(i).getPrice());
                preparedStatement.setInt(8, wines.get(i).getRank());
                preparedStatement.setBoolean(9, wines.get(i).isActive());
                preparedStatement.executeUpdate();
            }
            this.connection.commit();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            try {
                connection.rollback();
                if (connection != null) {
                    System.err.print("Transaction is being rolled back");
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (this.preparedStatement != null) {
                    preparedStatement.close();
                }
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateWine(Wine wine) {
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "UPDATE wine SET wine_name = ?, wine_sort = ?, wine_publisher = ?, wine_year = ?, wine_category = ?, wine_price = ?, wine_rank = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setString(1, wine.getName());
            java.util.Date date =  wine.getInsertDate();
            Date sqlDate = new Date(date.getTime());
            this.preparedStatement.setDate(2, sqlDate);
            this.preparedStatement.setString(3, wine.getSort());
            this.preparedStatement.setString(4, wine.getPublisher());
            this.preparedStatement.setInt(5, wine.getYear());
            this.preparedStatement.setString(6, wine.getCategory());
            this.preparedStatement.setDouble(7, wine.getPrice());
            this.preparedStatement.setInt(8, wine.getRank());
            this.preparedStatement.setBoolean(9, wine.isActive());
            this.preparedStatement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            try {
                connection.rollback();
                if (connection != null) {
                    System.err.print("Transaction is being rolled back");
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (this.preparedStatement != null) {
                    preparedStatement.close();
                }
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
