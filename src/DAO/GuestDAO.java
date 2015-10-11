package DAO;

import models.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by michael on 29-09-15.
 */
public class GuestDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public GuestDAO(Connection connection) {
        this.connection = connection;
    }


    public Guest addGuest(Guest guest) {
        Guest currentGuest = null;
        try {
            this.preparedStatement = null;
            String sqlQuery = "INSERT INTO guest"
                + "(guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?,?,?)";

            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            this.preparedStatement.setString(1, guest.getSurname());
            this.preparedStatement.setString(2, guest.getInfix());
            this.preparedStatement.setString(3, guest.getFirstname());
            this.preparedStatement.setString(4, guest.getSalutation());
            this.preparedStatement.setString(5, guest.getStreetname());
            this.preparedStatement.setString(6, guest.getStreetnr());
            this.preparedStatement.setString(7, guest.getZipcode());
            this.preparedStatement.setString(8, guest.getCity());
            this.preparedStatement.setString(9, guest.getEmail());
            this.preparedStatement.setString(10, guest.getPhone());
            this.preparedStatement.setString(11, guest.getReferal());
            this.preparedStatement.setString(12, guest.getComment());
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
        } 
        return currentGuest;
    }

    public void updateGuest(Guest guest) {
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "UPDATE guest SET guest_lastname = ?, guest_infix = ?, guest_firstname = ?, guest_salutation = ?, guest_street = ?, guest_streetnr = ?, guest_zipcode = ?, guest_city = ?, guest_email = ?, guest_phone = ?, guest_referal = ?, guest_comment = ?, guest_noshow = ? WHERE guest_id = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setString(1, guest.getSurname());
            this.preparedStatement.setString(2, guest.getInfix());
            this.preparedStatement.setString(3, guest.getFirstname());
            this.preparedStatement.setString(4, guest.getSalutation());
            this.preparedStatement.setString(5, guest.getStreetname());
            this.preparedStatement.setString(6, guest.getStreetnr());
            this.preparedStatement.setString(7, guest.getZipcode());
            this.preparedStatement.setString(8, guest.getCity());
            this.preparedStatement.setString(9, guest.getEmail());
            this.preparedStatement.setString(10, guest.getPhone());
            this.preparedStatement.setString(11, guest.getReferal());
            this.preparedStatement.setString(12, guest.getComment());
            this.preparedStatement.setBoolean(13, guest.getNo_show());
            this.preparedStatement.setInt(14, guest.getId());
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

    public ArrayList<Guest> findGuestByLastname(String lastname) {
        ArrayList<Guest> guestList = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM guest WHERE LOWER(guest_lastname)  LIKE ?";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, lastname + '%');
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Guest guest =
                    new Guest(resultSet.getInt("guest_id"), 
                    	resultSet.getString("guest_lastname"),
                        resultSet.getString("guest_infix"),
                        resultSet.getString("guest_firstname"),
                        resultSet.getString("guest_salutation"),
                        resultSet.getString("guest_street"), 
                        resultSet.getString("guest_streetnr"),
                        resultSet.getString("guest_zipcode"), 
                        resultSet.getString("guest_city"),
                        resultSet.getString("guest_email"), 
                        resultSet.getString("guest_phone"),
                        resultSet.getString("guest_referal"), 
                        resultSet.getString("guest_comment"),
                        resultSet.getBoolean("guest_noshow")
                        );
                guestList.add(guest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return guestList;
    }


}
