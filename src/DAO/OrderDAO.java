package DAO;

import models.Guest;
import models.Order;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class OrderDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatementGuest;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // insertOrder
    public Order addOrder(Order order) {
        Order currentOrder = null;
        try {
            this.preparedStatement = null;
            String sqlQuery = "INSERT INTO orders"
                + "(orders_guest_id) VALUES (?) RETURNING orders_id, orders_timestamp";
            this.preparedStatement =
                this.connection.prepareStatement(sqlQuery);
            this.preparedStatement.setInt(1, order.getGuest().getId());
            ResultSet resultSet = this.preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                currentOrder = new Order(resultSet.getInt(1), order.getGuest(),
                    resultSet.getTimestamp("orders_timestamp"));
            }
            this.connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
                if (connection != null) {
                    System.err.print("Transaction is being rolled back ");
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
        return currentOrder;
    }

    public ArrayList<Order> getAllNativeOrders() {
        ArrayList<Order> orderArrayList = new ArrayList<Order>();
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "SELECT * FROM guest g INNER JOIN orders o ON o.orders_guest_id = g.guest_id WHERE o.orders_completed = false;";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Guest guest =
                    new Guest(resultSet.getInt("guest_id"), resultSet.getString("guest_lastname"),
                        resultSet.getString("guest_infix"), resultSet.getString("guest_firstname"),
                        resultSet.getString("guest_salutation"),
                        resultSet.getString("guest_street"), resultSet.getString("guest_streetnr"),
                        resultSet.getString("guest_zipcode"), resultSet.getString("guest_city"),
                        resultSet.getString("guest_email"), resultSet.getString("guest_phone"),
                        resultSet.getString("guest_referal"), resultSet.getString("guest_comment"),
                        resultSet.getBoolean("guest_noshow"));
                Order order = new Order(resultSet.getInt(1), guest,
                    resultSet.getTimestamp("orders_timestamp"));
                orderArrayList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderArrayList;
    }

    //updateOrder
    public void updateOrder(Order order) {
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "UPDATE orders SET orders_completed = ? WHERE orders_timestamp = ? AND orders_id = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setBoolean(1, order.isCompleted());
            this.preparedStatement.setTimestamp(2, new Timestamp(order.getDate().getTime()));
            this.preparedStatement.setInt(3, order.getId());
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