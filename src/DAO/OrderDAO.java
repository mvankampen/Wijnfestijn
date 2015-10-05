package DAO;

import models.Order;
import models.OrderLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class OrderDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // insertOrder


    //updateOrder
    public void updateOrder(Order order) {
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "UPDATE  guest SET order_completed = ? WHERE order_timestamp = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setBoolean(1, order.isCompleted());
            //this.preparedStatement.setDate(2, order.getDate());
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

    //getAllOrders
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<Order>();
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM order";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderLine orderLine = new OrderLine();
                Order order = new Order();
                guest.setLastname(resultSet.getString("guest_lastname"));
                guest.setInfix(resultSet.getString("guest_infix"));
                guest.setFirstname(resultSet.getString("guest_firstname"));
                guest.setSalutation(resultSet.getString("guest_salutation"));
                guest.setStreet(resultSet.getString("guest_street"));
                guest.setStreetnr(resultSet.getString("guest_streetnr"));
                guest.setZipcode(resultSet.getString("guest_zipcode"));
                guest.setCity(resultSet.getString("guest_city"));
                guest.setEmail(resultSet.getString("guest_email"));
                guest.setPhone(resultSet.getString("guest_phone"));
                guest.setComment(resultSet.getString("guest_comment"));
                guest.setReferral(resultSet.getString("guest_referral"));
                guest.setNo_show(resultSet.getBoolean("guest_noshow"));

                orderList.add(guest);
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
        return guestList;
    }

}
