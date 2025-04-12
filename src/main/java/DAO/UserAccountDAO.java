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
            // make connection with sql query and when ready, return the ID generated automatically in the database
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //setting sql param for username by accessing the Account class's getter method for username
            preparedStatement.setString(1, user.getUsername());
            // setting param for password by using Account class's getter method for accessing password
            preparedStatement.setString(2, user.getPassword());
            
            // run query
            preparedStatement.executeUpdate();
            // returning the account object information via primary key
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                // return the newly inserted int ID number - primary key
                int generated_author_id = (int) pkeyResultSet.getLong(1);
                // new Account object with filled params 
                return new Account(generated_author_id, user.getUsername(), user.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }





    // verify user login by using both username and password
    // @param username and password to check the login verification
    public Account getAccountByUsernameAndPassword(String username, String password){
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

             // setting the 1st sql query param for username
             preparedStatement.setString(1, username);
             // setting the 2nd sql query param for password
             preparedStatement.setString(2, password);
             // run our query and return account object matching username and password
             ResultSet rs = preparedStatement.executeQuery();
            // if the query returns a matching record from the databse table...
             if (rs.next()){
                // ..it populates a new Account object with the data and returns the Account object
                 Account acc = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"), 
                    rs.getString("password"));
                 return acc;
             }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // otherwise it returns no object if no matching params are found in table
        return null;
    }




























    
    
}
