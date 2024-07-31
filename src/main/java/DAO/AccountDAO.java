package DAO;

import java.sql.*;
import java.util.*;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

  int statusDAO;

  public Account addAccount(Account account){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "INSERT INTO account (username,password) VALUES (?,?)";
    try{
      PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pstmt.setString(1, account.getUsername());
      pstmt.setString(2, account.getPassword());
      pstmt.executeUpdate();

      ResultSet accountId = pstmt.getGeneratedKeys();

      if(accountId.next()){
        int newAccountId = (int) accountId.getLong(1);

        Account newAccount = new Account(newAccountId, account.getUsername(), account.getPassword());
        statusDAO = 200;
        return newAccount;  
      }
    }

    catch(SQLException e){
      statusDAO = 400;
      e.printStackTrace();
    }

    // finally {
    //   try {
    //     conn.close();
    //   } 
    //   catch (Exception e) {
    //     e.printStackTrace();
    //   }
    // }
    statusDAO = 400;
    return null;
  } 

  public List<Account> processLogin(){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "SELECT username, password FROM account";

    try{
      ArrayList<Account> allAccountsNoIds = new ArrayList<>();
      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()){
        Account newAccount = new Account(rs.getString("username"), rs.getString("password"));
        allAccountsNoIds.add(newAccount);
      }
      return allAccountsNoIds;
    }
    catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }



  public Account processLoginAccount(Account account){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "SELECT * FROM account WHERE username = '?' AND password = '?')";

    try{
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, account.getUsername());
      pstmt.setString(2, account.getPassword());
      ResultSet rs = pstmt.executeQuery();
      
      Account validAccount = new Account(rs.getInt("account_id"), account.getUsername(), account.getPassword());
      
      return validAccount;
      
    }
    catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }




  public List<Account> getAllAccounts(){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "SELECT* FROM account";

    try{
      ArrayList<Account> allAccounts = new ArrayList<>();
      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()){
        Account newAccountToAdd = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
        allAccounts.add(newAccountToAdd);
      }
      return allAccounts;
    }
    catch(SQLException e){
      e.printStackTrace();
    }
    finally{
      try{
        conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
    return null;
  }



  public List<String> getAllAccountUsernames(){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "SELECT username FROM account";

    try{
      ArrayList<String> allAccountUsernames = new ArrayList<>();
      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()){
        String newUsernameToAdd = (rs.getString("username"));
        allAccountUsernames.add(newUsernameToAdd);
      }
      return allAccountUsernames;
    }
    catch(SQLException e){
      e.printStackTrace();
    }
    finally{
      try{
        conn.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
    return null;
  }

  public int getStatusDAO(){
    return statusDAO;
  }
}
