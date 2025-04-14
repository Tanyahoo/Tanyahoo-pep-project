package Service;

import DAO.*;
import Model.*;


public class UserAccountService {

    // instance variable of type UserAccountDAO
    private UserAccountDAO accDao;


    // no args constructor for creating a new UserAccountService with a new UserAccountDAO
    public UserAccountService(){
        accDao = new UserAccountDAO();
    }




    /* method that uses UserAccountDAO to persist a new account to the database
     *the registration will be successful if and only if the 
     *username is not blank, 
     *the password is at least 4 characters long, and an Account with that username does not already exist

     @param acc an Account object.
     @return account if it was successfully persisted, null if it was not successfully persisted
     */
    public Account createAccount( Account acc){
        String user = acc.getUsername();
        String pass = acc.getPassword();
       // check conditions for password and username
       if (user == null || user.trim().isEmpty() || pass == null || pass.length() < 4) {
        return null;
        // throw new IllegalArgumentException("Invalid username or password.");
        }
        // Check for duplicate username
        Account existing = accDao.getAccountByUsername(user);
        if (existing != null) {
         return null;
        // throw new IllegalArgumentException("Username already in use!");
        } 
        // call the UserAccountDAO class method to create account
        Account newAccount = accDao.createAccount(acc);
        // return account created with username and password that meet the requirements
        return newAccount;
    }

     



    // method to verify login details of account
    //@return account is username and password are correct
    public Account login(String username, String password) {
        // Check that parameters meet conditions
        if (username == null || username.trim().isEmpty() || password == null || password.length() < 4) {
        return null;
        }
        // Call DAO to find account matching username and password
        return accDao.getAccountByUsernameAndPassword(username, password);
    }


}
























