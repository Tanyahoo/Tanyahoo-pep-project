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
     * @param author an author object.
     * @return The persisted author if the persistence is successful.
     */
    public Message addMessage(Message mess) {

        // get message text
        String msgTxt = mess.getMessage_text();
        // get user id
        int user = mess.getPosted_by();


        // check if message is not empty or over 255 chars
        if (msgTxt == null || msgTxt.trim().isEmpty() || msgTxt.length() > 255) {
            throw new IllegalArgumentException("Message cannot empty or over 255 characters long!");
         }


        // check if user exists
        if (user == 0) {
            throw new IllegalArgumentException("User not found!");
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




    // method to get all messages
    //@return all messages
    public List<Message> getAllMessages() {
        // call method in DAO class
        return msgDao.getAllMessages();
    }


    /* method to delete message by id
     * first checks if message exists
     * 
     */
    public boolean deleteMessage(int id){
        Message existingMessage = msgDao.getMessageById(id);

        if (existingMessage == null) {
            throw new IllegalArgumentException("Message not found");
        }
        
        // check if message is deleted
        int messageDeleted = msgDao.deleteMessageById(id);
        if (messageDeleted == 0){
            // if not return false
            return false;
        }
        
        return true;
        
    }





    
}
