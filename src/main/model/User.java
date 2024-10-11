package model;


public class User {

    // Represents a user that has a username, password, email 
    // and role (patient or healthcare provider)

    public User(String username, String password, String email, String role) {
      
    }


    // REQUIRES: username and password must be valid and not null
    // MODIFIES: None
    // EFFECTS: Returns true if the login is successful; false otherwise.
    
    public boolean login() {
        return false;

    }

    // REQUIRES: The user must be currently logged in.
    // MODIFIES: None
    // EFFECTS: Logs the user out and returns nothing.

    public void logout() {

    }
    
    // REQUIRES: None
    // MODIFIES: Email, password
    // EFFECTS: Updates the email or password (if provided). Returns true if the update is 
    // successful, false otherwise.

    
    public boolean updateProfile() {
        return false;

    }


  

}

