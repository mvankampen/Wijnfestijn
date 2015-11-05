package DAO;

import models.Wine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael van Kampen, Sander de Jong
 * @version 0.1, november 2015
 *          Description:
 *          WineDAO is used to separate low level data accessing API or operations from high level business services.
 */
public class WineDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    /**
     * Constructor
     *
     * @param connection A connection (session) with a specific
     *                   database. SQL statements are executed and results are returned
     *                   within the context of a connection.
     */
    public WineDAO(Connection connection) {
        this.connection = connection;
    }


    /**
     * <P>inserts all wines in the List into the database</P>
     *
     * @param wines contains all order lines
     */
    public void insertAllWines(List<Wine> wines) {
        try {
            this.preparedStatement = null;
            String sqlQuery = "INSERT INTO wine"
                + "(wine_name, wine_category, wine_type, wine_publisher, wine_year, wine_price, wine_rank, wine_costprice, wine_margin) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);

            for (int i = 0; i < wines.size(); i++) {
                preparedStatement.setString(1, wines.get(i).getName());
                preparedStatement.setString(2, wines.get(i).getCategory());
                preparedStatement.setString(3, wines.get(i).getType());
                preparedStatement.setString(4, wines.get(i).getPublisher());
                preparedStatement.setString(5, wines.get(i).getYear());
                preparedStatement.setDouble(6, wines.get(i).getPrice());
                preparedStatement.setString(7, wines.get(i).getRank());
                preparedStatement.setDouble(8, wines.get(i).getCostprice());
                preparedStatement.setDouble(9, wines.get(i).getMargin());
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
        }
    }

    /**
     * <P>Update the fields of the Wine in the Database Management System with the new data</P>
     *
     * @param wine object to update the fields into the Database Management System
     */
    public void updateWine(Wine wine) {
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "UPDATE wine SET wine_name = ?, wine_category = ?, wine_type = ?, wine_publisher = ?, wine_year = ?, wine_price = ?, wine_rank = ?, wine_active = ? WHERE wine_name = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setString(1, wine.getName());
            this.preparedStatement.setString(2, wine.getCategory());
            this.preparedStatement.setString(3, wine.getType());
            this.preparedStatement.setString(4, wine.getPublisher());
            this.preparedStatement.setString(5, wine.getYear());
            this.preparedStatement.setDouble(6, wine.getPrice());
            this.preparedStatement.setString(7, wine.getRank());
            this.preparedStatement.setBoolean(8, wine.isActive());
            this.preparedStatement.setString(9, wine.getName());
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
        }
    }

    /**
     * @param wineId wants to search for a specific Wine
     * @return Wine object that fits with the given ID
     */
    //
    public Wine getWineById(int wineId) {

        Wine wine = null;
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM wine WHERE wine_id = ?";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, wineId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                wine = new Wine(resultSet.getInt("wine_id"), resultSet.getString("wine_name"),
                    resultSet.getString("wine_category"), resultSet.getString("wine_type"),
                    resultSet.getString("wine_publisher"), resultSet.getString("wine_year"),
                    resultSet.getDouble("wine_price"), resultSet.getString("wine_rank"),
                    resultSet.getDouble("wine_costprice"), resultSet.getDouble("wine_margin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wine;
    }
    public ArrayList<Wine> getAllActiveWine() {
		try {
			ArrayList<Wine> wineList = new ArrayList<>();
			Wine wine;
			this.preparedStatement = null;
			String sqlQuery = "SELECT * FROM wine WHERE wine_active = ?";
			this.preparedStatement = this.connection.prepareStatement(sqlQuery);
			this.preparedStatement.setBoolean(1, true);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				wine = new Wine(resultSet.getInt("wine_id"), resultSet.getString("wine_name"),
						resultSet.getString("wine_category"), resultSet.getString("wine_type"),
						resultSet.getString("wine_publisher"), resultSet.getString("wine_year"),
						resultSet.getDouble("wine_price"), resultSet.getString("wine_rank"),
						resultSet.getDouble("wine_costprice"), resultSet.getDouble("wine_margin"));
				wineList.add(wine);
			}
			return wineList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    public void setAllWinesFalse() {
		try {
			this.preparedStatement = null;
			String setAllToFalse = "UPDATE wine SET wine_active = ?";
			this.preparedStatement = this.connection.prepareStatement(setAllToFalse);
			preparedStatement.setBoolean(1, false);
			preparedStatement.executeUpdate();
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
		}

	}

}