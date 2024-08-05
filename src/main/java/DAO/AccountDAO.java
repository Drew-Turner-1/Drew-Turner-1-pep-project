package DAO;

import java.sql.*;
import java.util.*;
import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

  int statusDAO;

  public Account addAccount(Account account){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "INSERT INTO account (username, password) VALUES (?,?)";
    try{
      PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pstmt.setString(1, account.getUsername());
      pstmt.setString(2, account.getPassword());
      pstmt.executeUpdate();

      ResultSet accountId = pstmt.getGeneratedKeys();

      if(accountId.next()){
        int newAccountId = accountId.getInt(1);

        Account newAccount = new Account(newAccountId, account.getUsername(), account.getPassword());
        statusDAO = 200;
        return newAccount;
      }
    }

    catch(SQLException e){
      statusDAO = 400;
      e.printStackTrace();
      statusDAO = 400;
      return null;
    }
    statusDAO = 400;
      return null;
  } 

  public Account LoginAccount(Account account){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "SELECT * FROM account WHERE username = ? AND password = ?";

    try{
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, account.getUsername());
      pstmt.setString(2, account.getPassword());
      ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
          Account validAccount = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
          //Account validAccount = new Account(rs.getInt("account_id"), account.getUsername(), account.getPassword());
          statusDAO = 200;
          return validAccount;
        }
        else{
          statusDAO = 401;
          return null;
        }
    }
    catch(SQLException e){
      e.printStackTrace();
      statusDAO = 401;
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
      statusDAO = 200;
      return allAccounts;
    }
    catch(SQLException e){
      e.printStackTrace();
      statusDAO = 401;
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

      statusDAO = 200;
      return allAccountUsernames;
    }
    catch(SQLException e){
      e.printStackTrace();
      statusDAO = 400;
    }
    return null;
  }

  public List<Integer> getAllAccountIds(){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "SELECT account_id FROM account";

    try{
      ArrayList<Integer> allAccountIds = new ArrayList<>();
      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      if(rs.next()){
        int newAccountIdToAdd = (rs.getInt("account_id"));
        allAccountIds.add(newAccountIdToAdd);
      }
      statusDAO = 200;
      System.out.println(allAccountIds);
      return allAccountIds;
    }
    catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }


  public int getStatusDAO(){
    return statusDAO;
  }
}
