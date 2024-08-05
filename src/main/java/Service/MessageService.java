package Service;

import java.util.*;
import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Message;

public class MessageService {

    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService(){
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }


    public Message createMessage(Message message){
        try{
            validateNewMessage(message);
            Message newMessage = messageDAO.addMessage(message);
            return newMessage;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> getAllMessages(){
        try{
            List<Message> allMessages = messageDAO.getAllMessages();
            validateGetAllMessages(allMessages);
            return allMessages;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> getAllUserMessages(int postingUser){
        try{
            List<Message> allMessagesById = messageDAO.getAllUserMessages(postingUser);
            return allMessagesById;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message getMessageById(int messageIdOnly){
        try{

            Message messageById = messageDAO.getMessageById(messageIdOnly);
            return messageById;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message editMessageById(int messageIdOnly, String textToUpdate ){

        try{
            validateMessageExists(messageIdOnly);
            validateMessageText(textToUpdate);
            boolean isUpdated = messageDAO.updateMessageById(messageIdOnly, textToUpdate);
            if(isUpdated = true){
                Message updatedMessage = messageDAO.getMessageById(messageIdOnly);
                return updatedMessage;
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Message deleteMessageById(int messageIdOnly){
        
            try{
                Message messageToDelete = messageDAO.getMessageById(messageIdOnly);
                validateMessageDeletion(messageIdOnly);
                return messageToDelete;
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;

            }
    }

    public void validateMessageDeletion(int messageIdOnly){
        Message messageToDelete = messageDAO.getMessageById(messageIdOnly); 

        if(Objects.isNull(messageToDelete)){
            throw new IllegalArgumentException("Message doesn't exist. ");
        }
    }

    public void validateGetAllMessages(List<Message> allMessages){

        if(Objects.isNull(allMessages)){
            throw new IllegalArgumentException("No messages. ");
        }
    }

    public void validateNewMessage(Message message){

        if(Objects.isNull(message)){
            throw new IllegalArgumentException("Message can't be empty. ");
        }
        if(message.getMessage_text().length() >= 255){
            throw new IllegalArgumentException("Message must be under 255 characters. ");
        }
        if(message.getMessage_text().equals("")){
            throw new IllegalArgumentException("Message can't be empty. ");
        }
        if(! accountDAO.getAllAccountIds().contains(message.getPosted_by())){
            throw new IllegalArgumentException("User doesn't exist. ");
        }
    }

    public void validateMessageExists(int messageIdOnly){

        if(Objects.isNull(messageDAO.getMessageById(messageIdOnly))){
            throw new IllegalArgumentException("Message with this ID doesn't exist. ");
        }
    }

    public void validateMessageText(String messageText){

        if(messageText.equals(null)){
            throw new IllegalArgumentException("Message can't be empty. ");
        }
        if(messageText.equals("")){
            throw new IllegalArgumentException("Message can't be empty. ");
        }
        if(messageText.length() >= 255){
            throw new IllegalArgumentException("Message must be under 255 characters. ");
        }
    }



}
