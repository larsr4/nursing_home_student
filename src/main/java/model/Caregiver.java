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
    private String dateOfBirth;
    private String phoneNumber;

    /**
     * constructs a patient from the given params.
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param telenumber
     */
    public Caregiver(String firstName, String surname, String dateOfBirth, String telenumber) {
        super(firstName, surname);
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = telenumber;

    }
    /**
     * constructs a patient from the given params.
     * @param cid
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param telenumber
     */
    public Caregiver(long cid, String firstName, String surname, String dateOfBirth, String telenumber) {
        super(firstName, surname);
        this.cid = cid;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = telenumber;
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
        return dateOfBirth;
    }

    /**
     * convert given param to a localDate and store as new <code>birthOfDate</code>
     * @param dateOfBirth as string in the following format: YYYY-MM-DD
     */
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    /**
     *
     * @return string-representation of the Caregiver
     */
    public String toString() {
        return "Caregiver" + "\nCID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nBirthday: " + this.dateOfBirth +
                "\nPhonenumber: " + this.phoneNumber+
                "\n";
    }
    public String getTelNumber() {
        return this.phoneNumber;
    }
    public void setTelephone(String Telephone) {
        this.phoneNumber=Telephone;
    }
}