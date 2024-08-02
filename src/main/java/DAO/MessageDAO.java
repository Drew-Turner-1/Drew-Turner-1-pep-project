package DAO;

import java.sql.*;
import java.util.*;
import Model.Message;
//import Model.Account;
import Util.ConnectionUtil;

public class MessageDAO {



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
                Message newMessage = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
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

    public List<Message> getAllMessages(){
        
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message";
        try{
            List<Message> allMessages = new ArrayList<>();
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

    public List<Message> getAllUserMessages(Message postingUser){
        
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message WHERE posted_by = ?";
        int postingUserConversion = postingUser.getPosted_by();
        try{
            List<Message> allMessages = new ArrayList<>();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,postingUserConversion);
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

    public Message getMessageById(Message messageIdOnly){
        
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message WHERE message_id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,messageIdOnly.getMessage_id());
            pstmt.executeQuery();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                Message messageById = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                return messageById;
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


    public Message editMessageById(Message messageIdOnly){
        
        Connection conn = ConnectionUtil.getConnection();
        String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,messageIdOnly.getMessage_text());
            pstmt.setInt(2,messageIdOnly.getMessage_id());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                Message updatedMessage = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                return updatedMessage;
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

    public Message deleteMessageById(Message messageIdOnly){
        
        Connection conn = ConnectionUtil.getConnection();
        String sql = "DELETE FROM message WHERE message_id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,messageIdOnly.getMessage_id());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                Message deletedMessage = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                return deletedMessage;
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


    public List<Integer> getAllPosters(Message message){
        ArrayList<Integer> allPosters = new ArrayList<>();
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT posted_by FROM message";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                int userToAdd = rs.getInt("posted_by");
                allPosters.add(userToAdd);
                return allPosters;
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

    public List<Integer> getAllMessageIds(Message message){
        ArrayList<Integer> allMessageIds = new ArrayList<>();
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT message_id FROM message";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                int idToAdd = rs.getInt("message_id");
                allMessageIds.add(idToAdd);
                return allMessageIds;
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
