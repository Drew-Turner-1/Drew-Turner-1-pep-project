package Service;
 
import java.util.*;
import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService(){
        this.accountDAO = new AccountDAO();
    }

    public Account addAccount(Account account){
        
        try{
            validateAccount(account);
            Account newAccount = accountDAO.addAccount(account);
            return newAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void validateAccount(Account account) {
        try{
            if (Objects.isNull(account)) {
                throw new IllegalArgumentException("A Username and Password are required.");
            }
            if (Objects.isNull(account.getUsername()) || account.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("A Username is required.");
            }
            if (Objects.isNull(account.getPassword()) || account.getPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("A Password is required.");
            }
            if (account.getPassword().length() < 4) {
                throw new IllegalArgumentException("A Password must be at least four characters long.");
            }
            if(accountDAO.getAllAccountUsernames().contains(account.getUsername().trim())){
                throw new IllegalArgumentException("This Username is already taken :(");
            }
        }

        catch(IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Login failed :/ ");
        }
    }

    public Account loginAccount(Account account){
        try{
            validateAccountForLogin(account);
            //if(accountDAO.processLogin().contains(account)){
                Account validAccount = accountDAO.LoginAccount(account);
                return validAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    private void validateAccountForLogin(Account account) {
        try{
            if (Objects.isNull(account)) {
                throw new IllegalArgumentException("A Username and Password are required.");
            }
            if (Objects.isNull(account.getUsername()) || account.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("A Username is required.");
            }
            if (Objects.isNull(account.getPassword()) || account.getPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("A Password is required.");
            }
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Login failed :/ ");
        }
    }
}
