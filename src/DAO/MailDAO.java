package DAO;

import models.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 05-10-15.
 */
public class MailDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public MailDAO(Connection connection) {
        this.connection = connection;
    }

    // ReminderMail function
    public List<Guest> reminderMail() {
        List<Guest> guestList = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM guest";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Guest guest = new Guest();
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

                guestList.add(guest);
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

    //Uitnodiging
    // Bedankmail
    public List<Guest> thanksMail() {

    }
    // Herindering Factuur
}