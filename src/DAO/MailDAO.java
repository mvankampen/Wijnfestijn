package DAO;

import models.Guest;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
                Guest guest =
                    new Guest(resultSet.getInt("guest_id"), resultSet.getString("guest_lastname"),
                        resultSet.getString("guest_infix"), resultSet.getString("guest_firstname"),
                        resultSet.getString("guest_salutation"),
                        resultSet.getString("guest_street"), resultSet.getString("guest_streetnr"),
                        resultSet.getString("guest_zipcode"), resultSet.getString("guest_city"),
                        resultSet.getString("guest_email"), resultSet.getString("guest_phone"),
                        resultSet.getString("guest_referal"), resultSet.getString("guest_comment"),
                        resultSet.getBoolean("guest_noshow"));
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
    public ArrayList<InternetAddress> invitationMail() {
        ArrayList<InternetAddress> emailArraylist = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT * FROM guest";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emailArraylist.add(new InternetAddress(resultSet.getString("guest_email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AddressException e) {
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
        return emailArraylist;
    }

    // Bedankmail
    public List<Guest> thanksMail() {
        return null;
    }

    public ArrayList<InternetAddress> getOpenOrderGuests() {
        ArrayList<InternetAddress> emailArraylist = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "SELECT guest_email FROM guest g INNER JOIN orders o ON o.orders_guest_id = g.guest_id WHERE o.orders_completed = FALSE";

            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                emailArraylist.add(new InternetAddress(resultSet.getString("guest_email")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return emailArraylist;
    }
}
