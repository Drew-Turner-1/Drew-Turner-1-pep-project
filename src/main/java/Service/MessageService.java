package Service;

import java.util.*;
import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Message;
import Model.Account;

public class MessageService {

    int messageServiceStatus;

    private MessageDAO messageDAO;

    public MessageService(){
        this.messageDAO = new MessageDAO();
    }



    public Message createMessage(Message message){
        try{
            messageDAO.validateNewMessage();
            Message newMessage = messageDAO.addMessage(message);
            return newMessage;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void validateNewMessage(Message message);

    if(Objects.isNull(message)){
        throw new IllegalArgumentException("Message can't be empty. ");
    }

    if(message.length() >= 255){
        throw new IllegalArgumentException("Message must. ");
    }


}
