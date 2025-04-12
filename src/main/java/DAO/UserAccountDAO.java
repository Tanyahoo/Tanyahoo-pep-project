package DAO;
import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.*;



public class UserAccountDAO {




public Account createAccount (Account user){
        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the usename and password column, so that the database may
//          automatically generate a primary key.
            String sql = "INSERT into account(username, password) values (?, ?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setString(1, author.getName());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_author_id = (int) pkeyResultSet.getLong(1);
                return new Author(generated_author_id, author.getName());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }













    
    
}
