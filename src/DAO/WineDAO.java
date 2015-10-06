package DAO;

import models.Wine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            String sqlQuery =
                "SELECT * FROM wine";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Wine wine = new Wine();
                wine.setName(resultSet.getString("wine_name"));
                wine.setCategory(resultSet.getString("wine_category"));
                wine.setType(resultSet.getString("wine_type"));
                wine.setPublisher(resultSet.getString("wine_publisher"));
                wine.setYear(resultSet.getString("wine_year"));
                wine.setPrice(resultSet.getDouble("wine_price"));
                wine.setRank(resultSet.getString("wine_rank"));
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

    public Wine getWineByName(String wineName) {
        Wine wine = null;
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM wine WHERE wine_name = ?";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, wineName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                wine = new Wine();
                wine.setName(resultSet.getString("wine_name"));
                wine.setCategory(resultSet.getString("wine_category"));
                wine.setType(resultSet.getString("wine_type"));
                wine.setPublisher(resultSet.getString("wine_publisher"));
                wine.setYear(resultSet.getString("wine_year"));
                wine.setPrice(resultSet.getDouble("wine_price"));
                wine.setRank(resultSet.getString("wine_rank"));
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

    public void insertAllWines(List<Wine> wines) {
        try {
            this.preparedStatement = null;
            String sqlQuery = "INSERT INTO wine"
                + "(wine_id, wine_timstamp, wine_name, wine_category, wine_type, wine_publisher, wine_year, wine_price, wine_rank) VALUES "
                + "(DEFAULT, TIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);

            for (int i = 0; i < wines.size(); i++) {
                preparedStatement.setString(1, wines.get(i).getName());
                preparedStatement.setString(2, wines.get(i).getCategory());
                preparedStatement.setString(3, wines.get(i).getType());
                preparedStatement.setString(4, wines.get(i).getPublisher());
                preparedStatement.setString(5, wines.get(i).getYear());
                preparedStatement.setDouble(6, wines.get(i).getPrice());
                preparedStatement.setString(7, wines.get(i).getRank());
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
                "UPDATE wine SET wine_name = ?, wine_category = ?, wine_type = ?, wine_publisher = ?, wine_year = ?, wine_price = ?, wine_rank = ?, wine_active = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setString(1, wine.getName());
            this.preparedStatement.setString(2, wine.getCategory());
            this.preparedStatement.setString(3, wine.getType());
            this.preparedStatement.setString(4, wine.getPublisher());
            this.preparedStatement.setString(5, wine.getYear());
            this.preparedStatement.setDouble(6, wine.getPrice());
            this.preparedStatement.setString(7, wine.getRank());
            this.preparedStatement.setBoolean(8, wine.isActive());
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
