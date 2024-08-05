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

    public List<Message> getAllUserMessages(Message postingUser){
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

    public Message editMessageById(Message messageIdOnly){
        try{
            validateMessageById(messageIdOnly);
            Message updatedMessage = messageDAO.editMessageById(messageIdOnly);
            return updatedMessage;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Message deleteMessageById(Message messageIdOnly){
        
        try{
            int deletedMessage = messageDAO.getMessageById(messageIdOnly);
            if(){

            }
            return deletedMessage;
        }
        try{
            boolean isDeleted = messageDAO.deleteMessageById(messageIdOnly);
            return deletedMessage;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
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
            throw new IllegalArgumentException("Message must be under 255 characters. ");
        }
        if(! accountDAO.getAllAccountIds().contains(message.getPosted_by())){
            throw new IllegalArgumentException("User doesn't exist. ");
        }
    }

    public void validateMessageById(Message message){

        if(Objects.isNull(message)){
            throw new IllegalArgumentException("Message can't be empty. ");
        }
        if(! messageDAO.getAllMessageIds(message).contains(message.getMessage_id())){
            throw new IllegalArgumentException("Message doesn't exist. ");
        }
        if(message.getMessage_text().length() >= 255){
            throw new IllegalArgumentException("Message must be under 255 characters. ");
        }
        if(! accountDAO.getAllAccountIds().contains(message.getPosted_by())){
            throw new IllegalArgumentException("User doesn't exist. ");
        }
    }

}
