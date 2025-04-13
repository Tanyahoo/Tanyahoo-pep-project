package Service;

import java.util.*;
import DAO.*;
import Model.*;

public class UserAccountService {

    private UserAccountDAO userDao;

    // no args constructor for creating a new UserAccountService with a new UserAccountDAO
    public UserAccountService(){
        userDao = new UserAccountDAO();
    }




    // method that uses UserAccountDAO to persist a new account to the database
    // the registration will be successful if and only if the 
    // username is not blank, 
    // the password is at least 4 characters long, and an Account with that username does not already exist
    
    // @param acc an Account object.
    // @return account if it was successfully persisted, null if it was not successfully persisted

    public Account createAccount( Account acc){
        String user = acc.getUsername();
        String pass = acc.getPassword();

        // check username and password for conditions
         if (user == null || user.trim().isEmpty()) {
             System.out.println("Username cannot be blank.");
                return null;
         }
         if (pass == null || pass.length() < 4) {
             System.out.println("Password must be at least 4 characters long.");
                 return null;
         }

         // check if there's an account created with the username
         Account existing = userDao.getAccountByUsername(user);

         // if this account exists then the username is in use
        if (existing != null ){
            System.out.println("Username already exists!");
            return null;
            }
        // return account created with username and password that meet the requirements
        return userDao.getAccountByUsernameAndPassword(user, pass);
    }



























}