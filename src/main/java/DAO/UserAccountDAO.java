package DAO;
import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.*;



public class UserAccountDAO {


// creating a new Account, not containing an account_id
// @param user an object modelling a new account.

public Account createAccount (Account user){
        Connection connection = ConnectionUtil.getConnection();
        try {
         // inserting sql logic with the username and password column, so that the database may
         //automatically generate a primary key.
            String sql = "INSERT into account(username, password) values (?, ?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //setting param for username by accessing the Account class's getter method for username
            preparedStatement.setString(1, user.getUsername());
            // setting param for password by using Account class's getter method for accessing password
            preparedStatement.setString(2, user.getPassword());
            

            preparedStatement.executeUpdate();
            // returning the account object information via primary key
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                // return the newly inserted account by accessing it via primary key ID
                int generated_author_id = (int) pkeyResultSet.getLong(1);
                // new Account object with filled params
                return new Account(generated_author_id, user.getUsername(), user.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }































    
    
}
