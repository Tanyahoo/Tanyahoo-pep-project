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
    // method checks if the account Id already exists before it attempts to persist it
    // @param acc an Account object.
    //@return account if it was successfully persisted, null if it was not successfully persisted (eg if the book primary
    //key was already in use.)
    public Account createAccount( Account acc){
        int account_id = acc.getAccount_id();

        Account existingAccount = userDao.













        return null;
    }



























}