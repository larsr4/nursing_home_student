package datastorage;

import model.NormalUser;

import java.sql.*;
import java.util.ArrayList;

public class JdbcDAO extends DAOimp<NormalUser> {
    private static final String SELECT_QUERY = "SELECT * FROM USER WHERE USER.USER= ? AND USER.PASSWORD =?";

    public JdbcDAO(Connection conn) {
        super(conn);
    }

    public boolean validate(String user, String password) throws SQLException {
        try (
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

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
    protected String getCreateStatementString(NormalUser user) {
        return String.format("INSERT INTO user (name, Password, Admin, firstLogin) VALUES ('%s', '%s', '%s', '%s')",
                user.getName(), user.getPassword(), user.getAdmin(), user.getFirstLogin());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM user WHERE pid = %d", key);
    }

    @Override
    protected NormalUser getInstanceFromResultSet(ResultSet result) throws SQLException {
        NormalUser user = null;
        user = new NormalUser(result.getInt(1), result.getString(2),
                result.getString(3), result.getBoolean(4), result.getBoolean(5));
        return user;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM user";
    }

    @Override
    protected ArrayList<NormalUser> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<NormalUser> list = new ArrayList<>();
        NormalUser user = null;
        while (result.next()) {
            user = new NormalUser(result.getInt(1), result.getString(2),
                    result.getString(3), result.getBoolean(4), result.getBoolean(5));
            list.add(user);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(NormalUser normalUser) {
        return String.format("UPDATE user SET user = '%s', password = '%s', admin = '%s', firstlogin = '%s' " + "WHERE pid = %d", normalUser.getName(), normalUser.getPassword(), normalUser.getAdmin(),
                normalUser.getFirstLogin());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM user WHERE pid=%d", key);
    }
}
