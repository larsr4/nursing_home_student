package datastorage;

import model.Caregiver;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import utils.DateConverter;

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
        return String.format("INSERT INTO caregiver (firstname, surname, birth_date, phonenumber,block,entrydate) VALUES ('%s', '%s', '%s', '%s',default,'%s')",
                caregiver.getFirstName(), caregiver.getSurname() ,caregiver.getDateOfBirth(),caregiver.getTelNumber(),DateConverter.getDatenow());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE cid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Caregiver</code>
     * + checks ob diese Daten zualt sind oder ob diese gesperrt sind
     * @param result ResultSet with a single row. Columns will be mapped to a Caregiver-object.
     * @return Caregiver with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver p = null;
        p = new Caregiver(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4),result.getString(5),result.getBoolean(6),result.getString(7));
        if(DateConverter.add10(p.getEntryDate())>=DateConverter.getDatenowINT()){
            deleteById(p.getCid());
        }
        if(p.getBlock()==false) {
            return p;
        }else{
            return null;
        }


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
                    result.getString(3),result.getString(4),result.getString(5),result.getBoolean(6),result.getString(7));
            if(p.getBlock()==false){
                list.add(p);
            }
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
        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', birth_date = '%s', phonenumber = '%s',block = '%b'," +
                " WHERE cid = %d", Caregiver.getFirstName(), Caregiver.getSurname(), Caregiver.getDateOfBirth(),Caregiver.getTelNumber(), Caregiver.getBlock(),Caregiver.getCid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM caregiver WHERE cid=%d", key);
    }
    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getBlockStatementString(long key) {
        return String.format("UPDATE caregiver SET block = true WHERE cid=%d", key);
    }
}
