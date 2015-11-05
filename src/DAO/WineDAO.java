package DAO;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Wine;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class WineDAO {

	private Connection connection;
	private PreparedStatement preparedStatement;

	public WineDAO(Connection connection) {
		this.connection = connection;
	}

	// inserts all wines in the arraylist into the database
	public void insertAllWines(List<Wine> wines) {
		try {
			setAllWinesFalse();
			this.preparedStatement = null;

			String sqlQuery = "INSERT INTO wine"
					+ "(wine_name, wine_category, wine_type, wine_publisher, wine_year, wine_price, wine_rank, wine_costprice, wine_margin) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)";


			this.preparedStatement = this.connection.prepareStatement(sqlQuery);

			for (Wine wine : wines) {
				preparedStatement.setString(1, wine.getName());
				preparedStatement.setString(2, wine.getCategory());
				preparedStatement.setString(3, wine.getType());
				preparedStatement.setString(4, wine.getPublisher());
				preparedStatement.setString(5, wine.getYear());
				preparedStatement.setDouble(6, wine.getPrice());
				preparedStatement.setString(7, wine.getRank());
				preparedStatement.setDouble(8, wine.getCostprice());
				preparedStatement.setDouble(9, wine.getMargin());
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

	// updates the selected wine with the new values
	public void updateWine(Wine wine) {
		try {
			this.preparedStatement = null;
			String sqlQuery = "UPDATE wine SET wine_name = ?, wine_category = ?, wine_type = ?, wine_publisher = ?, wine_year = ?, wine_price = ?, wine_rank = ?, wine_active = ? WHERE wine_name = ?";
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

	// gives the wine object that fits with the given ID
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
}