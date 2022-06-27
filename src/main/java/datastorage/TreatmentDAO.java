package datastorage;

import model.Treatment;
import utils.DateConverter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific patient-SQL-queries.
 */
public class TreatmentDAO extends DAOimp<Treatment> {

    /**
     * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
     * @param conn
     */
    public TreatmentDAO(Connection conn) {
        super(conn);
    }
    /**
     * generates a <code>INSERT INTO</code>-Statement for a given patient
     * @param treatment for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Treatment treatment) {
        return String.format("INSERT INTO treatment (pid, treatment_date, begin, end, description, remarks,block,entrydate) VALUES " +
                "(%d, '%s', '%s', '%s', '%s', '%s',default,'%s')", treatment.getPid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(),
                treatment.getRemarks(),DateConverter.getDatenow());
    }
    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM treatment WHERE tid = %d", key);
    }
    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected Treatment getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
        LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
        LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
        Treatment m = new Treatment(result.getLong(1), result.getLong(2),
                date, begin, end, result.getString(6), result.getString(7),result.getBoolean(8),result.getString(9));
        if(DateConverter.add10(m.getEntryDate())>=DateConverter.getDatenowINT()){
            deleteById(m.getTid());
        }
        if(m.getBlock()==false) {
            return m;
        }else{
            return null;
        }
    }
    /**
     * generates a <code>SELECT</code>-Statement for all patients.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM treatment";
    }
    /**
     * maps a <code>ResultSet</code> to a <code>Patient-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to patient-object.
     * @return ArrayList with patients from the resultSet.
     */
    @Override
    protected ArrayList<Treatment> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment t = null;
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
            LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
            LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
            t = new Treatment(result.getLong(1), result.getLong(2),
                    date, begin, end, result.getString(6), result.getString(7),result.getBoolean(8),result.getString(9));
            list.add(t);
        }
        return list;
    }
    /**
     * generates a <code>UPDATE</code>-Statement for a given patient
     * @param treatment for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Treatment treatment) {
        return String.format("UPDATE treatment SET pid = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                "description = '%s', remarks = '%s' WHERE tid = %d", treatment.getPid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(), treatment.getRemarks(),
                treatment.getTid());
    }
    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM treatment WHERE tid= %d", key);
    }

    public List<Treatment> readTreatmentsByPid(long pid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOnePatientByPid(pid));
        list = getListFromResultSet(result);
        return list;
    }

    private String getReadAllTreatmentsOfOnePatientByPid(long pid){
        return String.format("SELECT * FROM treatment WHERE pid = %d", pid);
    }

    public void deleteByPid(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Delete FROM treatment WHERE pid= %d", key));
    }
    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getBlockStatementString(long key) {
        return String.format("Update treatment SET block = true WHERE tid=%d", key);
    }
}