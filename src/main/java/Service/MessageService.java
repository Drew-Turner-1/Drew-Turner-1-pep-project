package Service;

import java.util.*;
import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Message;
import Model.Account;

public class MessageService {

    int messageServiceStatus;

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
            return allMessages;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> getAllUserMessages(int postingUser){
        try{
            List<Message> allMessagesById = messageDAO.getAllMessagesById(postingUser);
            return allMessagesById;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void validateNewMessage(Message message){

        if(Objects.isNull(message)){
            throw new IllegalArgumentException("Message can't be empty. ");
        }
        if(message.getMessage_text().length() >= 255){
            throw new IllegalArgumentException("Message must be under 255 characters. ");
        }
        if(! accountDAO.getAllAccountIds().contains(message.getPosted_by())){
            throw new IllegalArgumentException("User doesn't exist. ");
        }
    }

}
