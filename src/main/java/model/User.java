package model;

public class User {
        public String Name;
        public String Password;
        private Boolean Admin;
        private Boolean firstLogin;
        private long uid;

        public User(String Name, String Password, Boolean Admin, Boolean firstLogin){
            this.Name = Name;
            this.Password = Password;
            this.Admin = Admin;
            this.firstLogin = firstLogin;
        }

    public User(long uid, String Name, String Password, Boolean Admin, Boolean firstLogin){
        this.uid = uid;
        this.Name = Name;
        this.Password = Password;
        this.Admin = Admin;
        this.firstLogin = firstLogin;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Boolean getAdmin() {
        return Admin;
    }

    public long getUid() {
        return uid;
    }
}
