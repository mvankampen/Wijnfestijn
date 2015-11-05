package DAO;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>  MailDAO is used to separate low level data accessing API or operations from high level business services. </p>
 * @author Michael van Kampen
 * @version 0.1, november 2015
 *         
 */

public class MailDAO {


    private Connection connection;
    private PreparedStatement preparedStatement;

    /**
     * Constructor
     *
     * @param connection A connection (session) with a specific
     *                   database. SQL statements are executed and results are returned
     *                   within the context of a connection.
     */
    public MailDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Reminder Mail
     *
     * @return ArrayList of InternetAddress
     */
    public ArrayList<InternetAddress> reminderMail() {
        ArrayList<InternetAddress> emailArraylist = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT DISTINCT guest_email FROM guest";
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

    /**
     * Invitation Mail
     *
     * @return ArrayList of InternetAddress
     */
    public ArrayList<InternetAddress> invitationMail() {
        ArrayList<InternetAddress> emailArraylist = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery = "SELECT DISTINCT guest_email FROM guest";
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

    /**
     * Thanks Mail function
     *
     * @return ArrayList of InternetAddress
     */
    public ArrayList<InternetAddress> thanksMail() {
        ArrayList<InternetAddress> emailArraylist = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "SELECT DISTINCT guest_email FROM guest g INNER JOIN orders o ON g.guest_id = o.orders_guest_id";
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

    /**
     * <P>Selects all guest data that have outstanding orders.</P>
     *
     * @return ArrayList of InternetAddress
     */
    public ArrayList<InternetAddress> getOpenOrderGuests() {
        ArrayList<InternetAddress> emailArraylist = new ArrayList<>();
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "SELECT DISTINCT guest_email FROM guest g INNER JOIN orders o ON o.orders_guest_id = g.guest_id WHERE o.orders_completed = ?";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);
            this.preparedStatement.setBoolean(1, false);
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
