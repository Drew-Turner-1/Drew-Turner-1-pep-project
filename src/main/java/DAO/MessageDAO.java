package DAO;

import java.sql.*;
import java.util.*;
import Model.Message;
import Model.Account;
import Util.ConnectionUtil;

public class MessageDAO {

    int messageStatusDAO;

    public Message addMessage(Message message){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, message.getPosted_by());
            pstmt.setString(2, message.getMessage_text());
            pstmt.setLong(3, message.getTime_posted_epoch());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                Message newMessage = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                return newMessage;
            }
            else{
                return null;
            }
        }
        catch(Exception e){
        e.printStackTrace();
        return null;
        }
    }

    public List<Message> getAllMessages(Message message){
        ArrayList<Message> allMessages = new ArrayList<>();
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                Message messageToAdd = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                allMessages.add(messageToAdd);
                return allMessages;
            }
            else{
                return null;
            }
        }
        catch(Exception e){
        e.printStackTrace();
        return null;
        }
    }
    
}
