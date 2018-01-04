package twitterPages;

public class User {
    private int twitterID;
    private String userID;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String country;
    
    //getters ans setters

    public int getTwitterID() {
        return twitterID;
    }

    public void setTwitterID(int twitterID) {
        this.twitterID = twitterID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
