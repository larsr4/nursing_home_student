package datastorage;

import model.Caregiver;
import model.Caregiver;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific Caregiver-SQL-queries.
 */
public class CaregiverDAO extends DAOimp<Caregiver> {

    /**
     * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
     * @param conn
     */
    public CaregiverDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given Caregiver
     * @param caregiver for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO Caregiver (firstname, surname, dateOfBirth,telenumber) VALUES ('%s', '%s', '%d', '%d')",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getDateOfBirth(),caregiver.getTelNumber());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM Caregiver WHERE cid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Caregiver</code>
     * @param result ResultSet with a single row. Columns will be mapped to a Caregiver-object.
     * @return Caregiver with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver p = null;
        p = new Caregiver(result.getInt(1), result.getString(2),
                result.getString(3),result.getDate(4),result.getInt(5));
        return p;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all Caregivers.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM Caregiver";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Caregiver-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to Caregiver-object.
     * @return ArrayList with Caregivers from the resultSet.
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver p = null;
        while (result.next()) {
            p = new Caregiver(result.getInt(1), result.getString(2),
                    result.getString(3),result.getDate(4),result.getInt(5));
            list.add(p);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given Caregiver
     * @param Caregiver for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Caregiver Caregiver) {
        return String.format("UPDATE Caregiver SET firstname = '%s', surname = '%s', dateOfBirth = '%s' " +
                " WHERE pid = %d", Caregiver.getFirstName(), Caregiver.getSurname(), Caregiver.getDateOfBirth()
                , Caregiver.getCid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM Caregiver WHERE cid=%d", key);
    }
}