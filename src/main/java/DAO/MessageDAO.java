package DAO;
import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.*;
 

public class MessageDAO {


    // adding a message into the database which matches the values contained in the message object.
    // @param msg an object modelling a new message.

    public Message createMessage (Message msg){
        Connection connection = ConnectionUtil.getConnection();
        try {
         // inserting sql logic into 3 of the 4 columns, so that the database may
         //automatically generate a primary key.
            String sql = "insert into message (posted_by, message_text,time_posted_epoch) values (?,?,?);";
            // make connection with sql query and when ready, return the ID generated automatically in the database
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //setting sql param for user id via getter method in Message class
            preparedStatement.setInt(1, msg.getPosted_by());
            // setting param for message text by using Message getter method
            preparedStatement.setString(2, msg.getMessage_text());
            // setting long with time posted by using getter method in Message class
            preparedStatement.setLong(3, msg.getTime_posted_epoch());
            

            preparedStatement.executeUpdate();
            // returning the message primary key
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                // return the newly inserted message ID number - primary key
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                // new Message object with filled params 
                return new Message(generated_message_id, msg.getPosted_by(), msg.getMessage_text(), msg.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    // retrieve all messages from the message table.
    //@return all messages.

    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> msgs = new ArrayList<>();
        try {
            // sql query to retrieve all messages from message table
            String sql = "SELECT * FROM message;";
            // pass in sql statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message mess = new Message(
                    rs.getInt("message_id"), 
                    rs.getInt("posted_by"),
                    rs.getString("message_text"), 
                    rs.getInt("time_posted_epoch"));
                msgs.add(mess);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return msgs;
    }













 }
