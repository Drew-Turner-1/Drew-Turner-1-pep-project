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
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, message.getPosted_by());
            pstmt.setString(2, message.getMessage_text());
            pstmt.setLong(3, message.getTime_posted_epoch());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                Message newMessage = new Message(rs.getInt("message_id"), message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
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
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                Message messageToAdd = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                allMessages.add(messageToAdd);
                return allMessages;
            }
            else{
                return allMessages;
            }
        }
        catch(Exception e){
        e.printStackTrace();
        return null;
        }
    }

    public List<Message> getAllUserMessages(int postingUser){
        
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message WHERE posted_by = ?";
        try{
            List<Message> allMessages = new ArrayList<>();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,postingUser);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                Message messageToAdd = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                allMessages.add(messageToAdd);
                return allMessages;
            }
            else{
                return allMessages;
            }
        }
        catch(Exception e){
        e.printStackTrace();
        return null;
        }
    }

    public Message getMessageById(int messageIdOnly){
        
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message WHERE message_id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,messageIdOnly);
            ResultSet rs = pstmt.executeQuery();

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


    public boolean updateMessageById(int messageIdOnly, String textToUpdate){
        
        Connection conn = ConnectionUtil.getConnection();
        String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,textToUpdate);
            pstmt.setInt(2,messageIdOnly);

            int rowsAffected = pstmt.executeUpdate();

            if(rowsAffected == 1){
                boolean isUpdated = true;
                return isUpdated;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
        e.printStackTrace();
        return false;
        }
    }

    public boolean deleteMessageById(Message messageIdOnly){
        
        Connection conn = ConnectionUtil.getConnection();
        String sql = "DELETE FROM message WHERE message_id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,messageIdOnly.getMessage_id());

            int rowsAffected = pstmt.executeUpdate();

            if(rowsAffected == 1){
                boolean isDeleted = true;
                return isDeleted;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
        e.printStackTrace();
        return false;
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
