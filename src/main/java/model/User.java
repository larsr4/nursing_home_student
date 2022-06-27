package model;

public class User {
        public String Name;
        public String Password;
        private Boolean Admin;
        private Boolean firstLogin;
        private long uid;

    /**
     * Klassenkonstruktor. Dieser setzt die privaten und publiken Variablen der Klasse auf die Übergabeparameter
     * @param Name Name des Users
     * @param Password Passwort des Users
     * @param Admin flag ob der User ein Admin ist
     * @param firstLogin flag ob der User schon mal eingeloggt wurde.(bislang nutzlos)
     */
        public User(String Name, String Password, Boolean Admin, Boolean firstLogin){
            this.Name = Name;
            this.Password = Password;
            this.Admin = Admin;
            this.firstLogin = firstLogin;
        }

    /**
     * Klassenkonstruktor. Dieser setzt die privaten und publiken Variablen der Klasse auf die Übergabeparameter
     * @param uid User id des Benutzers
     * @param Name Name des Users
     * @param Password Passwort des Users
     * @param Admin flag ob der User ein Admin ist
     * @param firstLogin flag ob der User schon mal eingeloggt wurde.(bislang nutzlos)
     */
    public User(long uid, String Name, String Password, Boolean Admin, Boolean firstLogin){
        this.uid = uid;
        this.Name = Name;
        this.Password = Password;
        this.Admin = Admin;
        this.firstLogin = firstLogin;
    }

    /**
     *
     * @return Name als String
     */
    public String getName() {
        return Name;
    }

    /**
     * setzt name auf Übergabeparameter
     * @param name neuer Name des Benutzers
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     *
     * @return Password als Sting
     */
    public String getPassword() {
        return Password;
    }

    /**
     * setzt Passwort auf Übergabeparameter
     * @param password neues Passwort
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     *
     * @return firstLogin als Boolean
     */
    public Boolean getFirstLogin() {
        return firstLogin;
    }

    /**
     * setzt firstlogin auf Üebergabeparameter
     * @param firstLogin neue flag
     */
    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    /**
     *
     * @return Admin als Boolean
     */
    public Boolean getAdmin() {
        return Admin;
    }

    /**
     *
     * @return uid als long
     */
    public long getUid() {
        return uid;
    }
}
