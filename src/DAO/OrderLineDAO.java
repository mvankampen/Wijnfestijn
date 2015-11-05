package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import models.Order;
import models.OrderLine;

/**
 * @author Michael van Kampen
 * @version 0.1, november 2015
 *          Description:
 *          OrderlineDAO is used to separate low level data accessing API or operations from high level business services.
 */

public class OrderLineDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    /**
     * Constructor
     *
     * @param connection A connection (session) with a specific
     *                   database. SQL statements are executed and results are returned
     *                   within the context of a connection.
     */
    public OrderLineDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * <P>Adds all given orderlines to the database</P>
     * @param orderLines contains all order lines
     * @param order object to add order id into Database Management System
     */
    public void addOrderLines(ObservableList<OrderLine> orderLines, Order order) {
        try {
            this.preparedStatement = null;
            String sqlQuery = "INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES " + "(?, ?, ?)";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);

            for (int i = 0; i < orderLines.size(); i++) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, orderLines.get(i).getWine().getId());
                preparedStatement.setInt(3, orderLines.get(i).getAmount());
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
     * <P>Update the fields of the Orderline in the Database Management System with the new data</P>
     *
     * @param orderLine object to update the fields into the Database Management System
     * @param order object to update the fields into the Database Management System
     */
    public void updateOrderLine(OrderLine orderLine, Order order) {
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "UPDATE  orderline SET orderline_order_id = ?, orderline_wine_id = ?, orderline_amount = ? WHERE orderline_order_id = ? AND orderline_wine_id = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setInt(1, order.getId());
            this.preparedStatement.setInt(2, orderLine.getWine().getId());
            this.preparedStatement.setInt(3, orderLine.getAmount());
            this.preparedStatement.setInt(4, order.getId());
            this.preparedStatement.setInt(5, orderLine.getWine().getId());
            this.preparedStatement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            try {
                connection.rollback();
                if (connection != null) {
                    System.err.print("Transaction is being rolled back ");
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }
}