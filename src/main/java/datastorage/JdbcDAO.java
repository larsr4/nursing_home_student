package datastorage;

import model.User;

import java.sql.*;
import java.util.ArrayList;

public class JdbcDAO extends DAOimp<User> {
    //Querry für die Auswahl des Users ohne Adminabfrage
    private static final String SELECT_QUERY = "SELECT * FROM USER WHERE USER.USER= ? AND USER.PASSWORD =?";
    //Querry für die Auswahl eines Users mit Abfrage des Admins
    private static final String SELECT_ADMIN = "SELECT * FROM USER WHERE USER.USER= ? AND USER.PASSWORD =? AND USER.ADMIN=TRUE";

    /**
     * Klassenkonstruktor. Stellt die Verbindung zur Datenbank her.
     * @param conn Connection der Datenbank
     */
    public JdbcDAO(Connection conn) {
        super(conn);
    }

    /**
     * schaut noch ob User und/oder Passwort richtig sind. Falls nein -> return false.
     * Falls ja -> return true.
     * @param user eingegebener Username
     * @param password eingegebenes Passwort
     * @return true oder false
     * @throws SQLException gibt die SQLException auf der Konsole aus.
     */
    public boolean validate(String user, String password) throws SQLException {
        try (
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY)) {
            //erstezt das ? in User mit dem Übergabestring user
            preparedStatement.setString(1, user);
            //erstezt das ? in Passwort mit dem Übergabestring passwort
            preparedStatement.setString(2, password);
            //resultat der Abfrage
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

    /**
     *  schaut nach, ob die übergebenen Werte zu einem Admin gehören.
     * @param user eingegebener Username
     * @param password eingegebenes Passwort
     * @return true oder false
     * @throws SQLException gibt die SQLException auf der Konsole aus.
     */
    public boolean Admin(String user, String password) throws SQLException {
        try (
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ADMIN)) {
            //erstezt das ? in User mit dem Übergabestring user
            preparedStatement.setString(1, user);
            //erstezt das ? in Passwort mit dem Übergabestring passwort
            preparedStatement.setString(2, password);
            //resultat der Abfrage
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

    /**
     * gibt eine SQLException aus.
     * @param ex SQLException
     */
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @Override
    protected String getCreateStatementString(User user) {
        return String.format("INSERT INTO user (name, Password, Admin, firstLogin) VALUES ('%s', '%s', '%s', '%s')",
                user.getName(), user.getPassword(), user.getAdmin(), user.getFirstLogin());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM user WHERE pid = %d", key);
    }

    @Override
    protected User getInstanceFromResultSet(ResultSet result) throws SQLException {
        User user = null;
        user = new User(result.getInt(1), result.getString(2),
                result.getString(3), result.getBoolean(4), result.getBoolean(5));
        return user;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM user";
    }

    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<User> list = new ArrayList<>();
        User user = null;
        while (result.next()) {
            user = new User(result.getInt(1), result.getString(2),
                    result.getString(3), result.getBoolean(4), result.getBoolean(5));
            list.add(user);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(User normalUser) {
        return String.format("UPDATE user SET user = '%s', password = '%s', admin = '%s', firstlogin = '%s' " + "WHERE pid = %d", normalUser.getName(), normalUser.getPassword(), normalUser.getAdmin(),
                normalUser.getFirstLogin());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM user WHERE pid=%d", key);
    }
    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getBlockStatementString(long key) {
        return String.format("Delete FROM patient WHERE pid=%d", key);
    }
}
