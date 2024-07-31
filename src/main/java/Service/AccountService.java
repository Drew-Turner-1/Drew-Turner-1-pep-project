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

    int status;

    
    public Account addAccount(Account account){
        validateAccount(account);
        try{
            Account newAccount = accountDAO.addAccount(account);
            status = 200;
            return newAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            status = 400;
            throw new RuntimeException("An error occurred while saving the account: " + e.getMessage() + ".");
        }
    }
    
    private void validateAccount(Account account) {
        if (Objects.isNull(account)) {
            status = 400;
            throw new IllegalArgumentException("A Username and Password are required.");
        }
        if (Objects.isNull(account.getUsername()) || account.getUsername().trim().isEmpty()) {
            status = 400;
            throw new IllegalArgumentException("A Username is required.");
        }
        if (Objects.isNull(account.getPassword()) || account.getPassword().trim().isEmpty()) {
            status = 400;
            throw new IllegalArgumentException("A Password is required.");
        }
        if (account.getPassword().length() < 4) {
            status = 400;
            throw new IllegalArgumentException("A Password must be at least four characters long.");
        }
        if(accountDAO.getAllAccountUsernames().contains(account.getUsername().trim())){
            status = 400;
            throw new IllegalArgumentException("This Username is already taken :(");
        }
        status = 200;
    }

    public Account processLoginAccount(Account account){
        //validateAccountForLogin(account);
        try{
            //if(accountDAO.processLogin().contains(account)){
                Account validAccount = accountDAO.processLoginAccount(account);
                status = 200;
                return validAccount;
            //}
        }
        catch (Exception e) {
            e.printStackTrace();
            status = 401;
            return null;
            //throw new RuntimeException("An error occurred while logging in: " + e.getMessage() + ".");
        }
        // status = 401;
        // return null;
    }
    

    // private void validateAccountForLogin(Account account) {
    //     if (Objects.isNull(account)) {
    //         status = 400;
    //         throw new IllegalArgumentException("A Username and Password are required.");
    //     }
    //     if (Objects.isNull(account.getUsername()) || account.getUsername().trim().isEmpty()) {
    //         status = 400;
    //         throw new IllegalArgumentException("A Username is required.");
    //     }
    //     if (Objects.isNull(account.getPassword()) || account.getPassword().trim().isEmpty()) {
    //         status = 400;
    //         throw new IllegalArgumentException("A Password is required.");
    //     }
    //     status = 200;
    // }

    public int getStatus(){
        return status;
    }

    public int getStatusDAO(){
        return accountDAO.getStatusDAO();
    }
}
