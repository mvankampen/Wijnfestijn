package DAO;

import models.Guest;
import models.Order;
import models.OrderLine;
import models.Wine;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class OrderDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatementWine;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // insertOrder
    public Order addOrder(Order order) {
        Order currentOrder = null;
        try {
            this.preparedStatement = null;
            String sqlQuery = "INSERT INTO orders"
                + "(orders_guest_id) VALUES (?) RETURNING orders_id, orders_timestamp, orders_completed";
            this.preparedStatement =
                this.connection.prepareStatement(sqlQuery);
            this.preparedStatement.setInt(1, order.getGuest().getId());
            ResultSet resultSet = this.preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                currentOrder = new Order(resultSet.getInt(1), order.getGuest(),
                    resultSet.getTimestamp("orders_timestamp"), resultSet.getBoolean("orders_completed"));
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
                Order order = new Order(resultSet.getInt("orders_id"), guest,
                    resultSet.getTimestamp("orders_timestamp"), resultSet.getBoolean("orders_completed"));
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
            this.preparedStatement.setBoolean(1, order.getCompleted());
            System.out.println(order.getCompleted());
            this.preparedStatement.setTimestamp(2, new Timestamp(order.getDate().getTime()));
            System.out.println(new Timestamp(order.getDate().getTime()));
            this.preparedStatement.setInt(3, order.getId());
            System.out.println(order.getId());
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

    public ArrayList<OrderLine> findOrderlinesByOrder(Order order) {
        ArrayList<OrderLine> orderLines = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM orderline WHERE orderline_order_id = ? ";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, order.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Wine wine = findWineByOrderLine(resultSet.getInt("orderline_wine_id"));
                OrderLine orderLine =
                        new OrderLine(resultSet.getInt("orderline_amount"), order, wine);
                orderLines.add(orderLine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return orderLines;
    }

    private Wine findWineByOrderLine(int orderline_wine_id) {
        Wine wine = null;
        try {
            this.preparedStatementWine = null;
            String sqlQuery = "SELECT * FROM wine WHERE wine_id = ?";
            this.preparedStatementWine = this.connection.prepareStatement(sqlQuery);
            preparedStatementWine.setInt(1, orderline_wine_id);
            ResultSet resultSet = preparedStatementWine.executeQuery();
            while (resultSet.next()) {
                wine = new Wine(resultSet.getInt("wine_id"), resultSet.getString("wine_name"),
                        resultSet.getString("wine_category"), resultSet.getString("wine_type"),
                        resultSet.getString("wine_publisher"), resultSet.getString("wine_year"),
                        resultSet.getDouble("wine_price"), resultSet.getString("wine_rank"), resultSet.getDouble("wine_costprice"), resultSet.getDouble("wine_margin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wine;
    }
}
