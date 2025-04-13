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






    // using UserAccountDAO to persist a new account to the database
    // method checks if the account Id already exists, the registration will be successful if and only if the 
    // username is not blank, 
    // the password is at least 4 characters long, and an Account with that username does not already exist
    // before it attempts to persist it
    // @param acc an Account object.
    // @return account if it was successfully persisted, null if it was not successfully persisted
    public Account createAccount( Account acc){
        int accountId = acc.getAccount_id();
        

        
        Account existingAccount = userDao.getAccountByAccountId(accountId);
        if (existingAccount == null && ){


        }













        return null;
    }



























}