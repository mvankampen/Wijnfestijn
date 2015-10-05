package DAO;

import models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 29-09-15.
 */
public class CustomerDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Customer> getAllCustomers() {
        this.preparedStatement = null;
        String sqlQuery = "SELECT * FROM ";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        try {
            this.preparedStatement = null;
            String sqlQuery = "INSERT INTO customer"
                + "(customer_lastname, customer_firstname, customer_insertion, customer_streetname, customer_streetnr, customer_zipcode, customer_city, customer_email, customer_salutation, customer_referral, customer_phone, customer_lionsmember) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?,?,?)";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            this.preparedStatement.setString(1, customer.getLastname());
            this.preparedStatement.setString(2, customer.getFirstname());
            this.preparedStatement.setString(3, customer.getInsertion());
            this.preparedStatement.setString(4, customer.getStreetname());
            this.preparedStatement.setInt(5, customer.getStreetnr());
            this.preparedStatement.setString(6, customer.getZipcode());
            this.preparedStatement.setString(7, customer.getCity());
            this.preparedStatement.setString(8, customer.getEmail());
            this.preparedStatement.setString(9, customer.getSalutation());
            this.preparedStatement.setString(10, customer.getReferral());
            this.preparedStatement.setString(11, customer.getPhone());
            this.preparedStatement.setString(12, customer.getLionsMember());
            this.preparedStatement.executeUpdate();
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

    public void updateCustomer(Customer customer) {
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "UPDATE  customer SET customer_lastname = ?, customer_firstname = ?, customer_insertion = ?, customer_streetname = ?, customer_streetnr = ?, customer_zipcode = ?, customer_city = ?, customer_email = ?, customer_salutation = ?, customer_referral = ?, customer_phone = ?, customer_lionsmember = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setString(1, customer.getLastname());
            this.preparedStatement.setString(2, customer.getFirstname());
            this.preparedStatement.setString(3, customer.getInsertion());
            this.preparedStatement.setString(4, customer.getStreetname());
            this.preparedStatement.setInt(5, customer.getStreetnr());
            this.preparedStatement.setString(6, customer.getZipcode());
            this.preparedStatement.setString(7, customer.getCity());
            this.preparedStatement.setString(8, customer.getEmail());
            this.preparedStatement.setString(9, customer.getSalutation());
            this.preparedStatement.setString(10, customer.getReferral());
            this.preparedStatement.setString(11, customer.getPhone());
            this.preparedStatement.setString(12, customer.getLionsMember());
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

    public List<Customer> findCustomerByLastname(String lastname) {
        List<Customer> customerList = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM customer WHERE customer_lastname LIKE %?%";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(2, lastname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer c = new Customer();
                c.setLastname(resultSet.getString("customer_lastname"));
                c.setFirstname(resultSet.getString("customer_firstname"));
                c.setInsertion(resultSet.getString("customer_insertion"));
                c.setStreetname(resultSet.getString("customer_streetname"));
                c.setStreetnr(resultSet.getInt("customer_streetnr"));
                c.setZipcode(resultSet.getString("customer_zipcode"));
                c.setCity(resultSet.getString("customer_city"));
                c.setEmail(resultSet.getString("customer_email"));
                c.setSalutation(resultSet.getString("customer_salutation"));
                c.setReferral(resultSet.getString("customer_referral"));
                c.setPhone(resultSet.getString("customer_phone"));
                c.setLionsMember(resultSet.getString("customer_lionsmember"));
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return customerList;
    }
}
