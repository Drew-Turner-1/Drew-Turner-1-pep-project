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
        }
        return newMessage;
    }

    public int getMessageStatus(){
        return messageServiceStatus;
    }

    public int getMessageStatusDAO(){
        return messageDAO.getMessageStatusDAO();
    }

    public boolean validateMessageStatus(){
        if(getStatus() == 200 && getStatus() == (getStatusDAO())){
            return true;
        }
        else{
            return false;
        }
    }
    
}
