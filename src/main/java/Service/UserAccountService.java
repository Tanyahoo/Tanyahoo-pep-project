package Service;

//import java.util.*;
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

        // check username and password for conditions
         if (user != null || user.trim().isEmpty()) {
            return null;
            //throw new IllegalArgumentException("Username cannot be empty!");
         }
         if (pass == null || pass.length() < 4) {
            return null;
             //throw new IllegalArgumentException("Password must be at least 4 characters long!");
         }

         // check if there's an account created with the username
         Account existing = accDao.getAccountByUsername(user);

         // if this account exists then the username is in use
        if (existing != null ){
            return null;
            //throw new IllegalArgumentException("Username already in use!");
            }
        
        // call the UserAccountDAO class method to create account
        Account newAccount = accDao.createAccount(acc);
        // return account created with username and password that meet the requirements
        return newAccount;
    }




    /*method checks if login is valid by checking if there's an account with matching login details
     *it calls method from UserAccountDAO that checks in database table if matching account object exists
     *@return an account object if exists otherwise null
     */

    public Account login(String username, String password) {
        // check that neither params are null
       // if (username == null || password == null) {
          //return null;
           // throw new IllegalArgumentException("Username and password must not be null.");
       // }
    
        // call UserAccountDAO class method to check if account exists with matching username and password
        Account acc = accDao.getAccountByUsernameAndPassword(username, password);
        // if account is null, return null
        if (acc == null) {
            return null;
        }
        // if successful return Account object matching username and password
        return acc; 
    }


}
























