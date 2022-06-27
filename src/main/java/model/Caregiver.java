package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A Caregiver who treats the patients
 */
public class Caregiver extends Person {
    private long cid;
    private String dateOfBirth;
    private String phoneNumber;
    private boolean block;
    private String entryDate;

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
    public Caregiver(long cid, String firstName, String surname, String dateOfBirth, String telenumber,Boolean block ,String entryDate) {
        super(firstName, surname);
        this.cid = cid;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = telenumber;
        this.block=block;
        this.entryDate= entryDate;
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
    public boolean getBlock(){
        return this.block;
    }
    public String getEntryDate(){
        return this.entryDate;
    }
}