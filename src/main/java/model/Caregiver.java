package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Caregiver extends Person {
    private long cid;
    private Date dateOfBirth;
    private int telnumber;

    /**
     * constructs a patient from the given params.
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param telenumber
     */
    public Caregiver(String firstName, String surname, Date dateOfBirth, int telenumber) {
        super(firstName, surname);
        this.dateOfBirth = dateOfBirth;
        this.telnumber = telenumber;

    }
    /**
     * constructs a patient from the given params.
     * @param cid
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param telenumber
     */
    public Caregiver(long cid, String firstName, String surname, Date dateOfBirth, int telenumber) {
        super(firstName, surname);
        this.cid = cid;
        this.dateOfBirth = dateOfBirth;
        this.telnumber = telenumber;
    }

    /**
     *
     * @return Caregiver id
     */
    public long getCid() {
        return cid;
    }

    /**
     *
     * @return date of birth as a string
     */
    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    /**
     * convert given param to a localDate and store as new <code>birthOfDate</code>
     * @param dateOfBirth as string in the following format: YYYY-MM-DD
     */
    public void setDateOfBirth(String dateOfBirth) {
        Date birthday = java.sql.Date.valueOf(dateOfBirth);
        this.dateOfBirth = birthday;
    }
    /**
     *
     * @return string-representation of the patient
     */
    public String toString() {
        return "Patient" + "\nMNID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nBirthday: " + this.dateOfBirth +
                "\nPhone number" + this.telnumber+
                "\n";
    }
    public int getTelNumber() {
        return telnumber;
    }
}