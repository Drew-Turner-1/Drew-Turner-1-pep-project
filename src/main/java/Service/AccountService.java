package Service;

import java.util.*;

import DAO.AccountDAO;
import Model.Account;
//import io.javalin.http.HttpStatus;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService(){
        this.accountDAO = new AccountDAO();
    }

    int serviceStatus;

    
    public Account addAccount(Account account){
        validateAccount(account);
        try{
            Account newAccount = accountDAO.addAccount(account);
            serviceStatus = 200;
            return newAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            serviceStatus = 400;
            //throw new RuntimeException("An error occurred while saving the account: " + e.getMessage() + ".");
        }
        return null;
    }
    
    private void validateAccount(Account account) {
        try{
            if (Objects.isNull(account)) {
                serviceStatus = 400;
                throw new IllegalArgumentException("A Username and Password are required.");
            }
            if (Objects.isNull(account.getUsername()) || account.getUsername().trim().isEmpty()) {
                serviceStatus = 400;
                throw new IllegalArgumentException("A Username is required.");
            }
            if (Objects.isNull(account.getPassword()) || account.getPassword().trim().isEmpty()) {
                serviceStatus = 400;
                throw new IllegalArgumentException("A Password is required.");
            }
            if (account.getPassword().length() < 4) {
                serviceStatus = 400;
                throw new IllegalArgumentException("A Password must be at least four characters long.");
            }
            if(accountDAO.getAllAccountUsernames().contains(account.getUsername().trim())){
                serviceStatus = 400;
                throw new IllegalArgumentException("This Username is already taken :(");
            }
            serviceStatus = 200;
        }

        catch(IllegalArgumentException e) {
            e.printStackTrace();
            serviceStatus = 400;
        }
    }

    public Account loginAccount(Account account){
        //validateAccountForLogin(account);
        try{
            //if(accountDAO.processLogin().contains(account)){
                Account validAccount = accountDAO.LoginAccount(account);
                serviceStatus = 200;
                return validAccount;
            //}
        }
        catch (Exception e) {
            e.printStackTrace();
            serviceStatus = 401;
            return null;
            //throw new RuntimeException("An error occurred while logging in: " + e.getMessage() + ".");
        }
        // status = 401;
        // return null;
    }
    

    // private void validateAccountForLogin(Account account) {
    //     if (Objects.isNull(account)) {
    //         serviceStatus = 400;
    //         throw new IllegalArgumentException("A Username and Password are required.");
    //     }
    //     if (Objects.isNull(account.getUsername()) || account.getUsername().trim().isEmpty()) {
    //         serviceStatus = 400;
    //         throw new IllegalArgumentException("A Username is required.");
    //     }
    //     if (Objects.isNull(account.getPassword()) || account.getPassword().trim().isEmpty()) {
    //         serviceStatus = 400;
    //         throw new IllegalArgumentException("A Password is required.");
    //     }
    //     serviceStatus = 200;
    // }

    public int getStatus(){
        return serviceStatus;
    }

    public int getStatusDAO(){
        return accountDAO.getStatusDAO();
    }
}
