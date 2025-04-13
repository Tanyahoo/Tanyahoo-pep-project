package DAO;
import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.*;
 

public class MessageDAO {


    // this method adds a message into the database which matches the values contained in the message object.
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




    // this method retrieves all messages from the message table.
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






    // ths method retrieves a specific message using its message ID
    //@param id a message ID

    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            // sql query selecting all messages using their message id
            String sql = "SELECT * FROM message WHERE message_id = ?;";
            // pass sql to preparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            // passing sql param of id
            preparedStatement.setInt(1, id);

            // run query, assign response to ResultSet
            ResultSet rs = preparedStatement.executeQuery();
            // if there is a message to return from query
            if(rs.next()){
                // create new message object and assign values retrieved from table
                Message mess = new Message(
                    rs.getInt("message_id"), 
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
                // return the message object
                return mess;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        // if no returned object, return null
        return null;
    }




    // this method deletes a message by identifying the message via its ID
    // @param id a message ID
    public int deleteMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            // sql query selecting all messages using their message id
            String sql = "DELETE FROM message WHERE message_id = ?;";
            // pass sql to preparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            // passing sql param of id
            preparedStatement.setInt(1, id);

            // run query, assign response int to check number of rows affected
            int rowsAffected = preparedStatement.executeUpdate();

            // return number of rows affected ie messages deleted
            return rowsAffected;
            
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        // if no message deleted, return zero
        return 0;
    }





    // this method updates a message by identifying the message via its ID
    // @param id a message ID, m message object
    public int updateMessageById(int id, Message m){
        Connection connection = ConnectionUtil.getConnection();
       
        try {
            // sql query selecting all messages using their message id
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";
            // pass sql to preparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set our sql params with text via getter in Message class
            preparedStatement.setString(1, m.getMessage_text());
            // passing sql param of id 
            preparedStatement.setInt(2, id);

            // run query, assign response to an int to check number of rows affected
            int rowsAffected = preparedStatement.executeUpdate();

            // return number of rows affected ie messages updated
            return rowsAffected;
            
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        // if no message rows updated, return zero
        return 0;
    }





    // this method retrieves all messages from the message table by a particular user
    //@return all messages from a user's ID

    public List<Message> getAllMessagesByUserId(int userId){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> msgs = new ArrayList<>();
        try {
            // sql query to retrieve all messages from message table
            String sql = "SELECT * FROM message WHERE posted_by = ?;";
            // pass in sql statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // pass in param to sql for user id
            preparedStatement.setInt(1, userId);

            // run query and get values, assign to ResultSet
            ResultSet rs = preparedStatement.executeQuery();
            // wheil there are values...
            while(rs.next()){
                //.. create new message object and populate with retrieved values
                Message mess = new Message(
                    rs.getInt("message_id"), 
                    rs.getInt("posted_by"),
                    rs.getString("message_text"), 
                    rs.getInt("time_posted_epoch"));
                // add each message objects to array
                msgs.add(mess);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        // return array/list of messages
        return msgs;
    }

 }
