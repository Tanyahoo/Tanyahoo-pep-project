package Service;

import java.util.List;

import DAO.*;
import Model.*;


public class MessageService {
    
    // instance variable of type MessageDAO
    private MessageDAO msgDao;


     // no args constructor for creating a new MessageService with a new MessageDAO
     public MessageService(){
        msgDao = new MessageDAO();
    }





     /**
     * method uses the MessageDAO class method to persist a message
     * meets conditions that message_text is not blank and is not over 255 chars
     * verfifies that user posted_by exists
     *
     * @param mess an message object.
     * @return The persisted message if the persistence is successful.
     */
    public Message addMessage(Message mess) {
        // get message text
        String msgTxt = mess.getMessage_text();
        // get user id
        int user = mess.getPosted_by();

        // check if message is not empty or over 255 chars
        if (msgTxt == null || msgTxt.trim().isEmpty() || msgTxt.length() > 255) {
            return null;
           // throw new IllegalArgumentException("Message cannot empty or over 255 characters long!");
         }
        // check if user exists
        if (user == 0) {
            return null;
           // throw new IllegalArgumentException("User not found!");
        }
        // return the message after calling the MessageDAO class method
        Message message = msgDao.createMessage(mess);
        return message;
    }






    // method to get message using its id
    public Message getMessageByID(int id){
        // return message after calling MessageDAO class method
        return msgDao.getMessageById(id);
    }






    /* method to delete message by id
     * first checks if message exists
     * @return message if deleted
     */
    public Message deleteMessage(int id) {
        Message existingMessage = msgDao.getMessageById(id);
    
        if (existingMessage != null) {
            int deleted = msgDao.deleteMessageById(id);
            if (deleted > 0) {
                // return the deleted message
                return existingMessage; 
            }
        }
        // no message found, or failed to delete
        return null; 
    }





    /* method to update message by id
     * first checks if message exists, checks conditions of updated message
     * @return message object that has been updated
     */
    public Message upDateMessageById(int id, Message m){
        Message existingMessage = msgDao.getMessageById(id);
       
        // check if message exists
        if (existingMessage == null) {
            return null; 
           // throw new IllegalArgumentException("Message not found");
        }
        // check if updated message meets conditions
        if (m.getMessage_text() == null || 
        m.getMessage_text().trim().isEmpty() || 
        m.getMessage_text().length() > 255) {
        return null;
    }
        // call method to update message 
        msgDao.updateMessageById(id, m);

       // return the updated message
        return msgDao.getMessageById(id);  
    }



    

    /**
     * method using MessageDAO to retrieve a List containing all messages
     * @return all messages in the database
     */
     public List<Message> getAllMessages() {
        // call method in DAO class
        return msgDao.getAllMessages();
     }

    
}
