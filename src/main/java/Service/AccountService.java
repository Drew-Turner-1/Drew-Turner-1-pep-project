package Service;
 
import java.util.*;
import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    private AccountDAO accountDAO;

    int serviceStatus;

    public AccountService(){
        this.accountDAO = new AccountDAO();
    }

    public Account addAccount(Account account){
        
        try{
            validateAccount(account);
            Account newAccount = accountDAO.addAccount(account);
            serviceStatus = 200;
            return newAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            serviceStatus = 400;
            return null;
        }
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
            throw new IllegalArgumentException("Login failed :/ ");
        }
    }

    public Account loginAccount(Account account){
        try{
            validateAccountForLogin(account);
            //if(accountDAO.processLogin().contains(account)){
                Account validAccount = accountDAO.LoginAccount(account);
                serviceStatus = 200;
                return validAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            serviceStatus = 401;
            return null;
        }
    }
    

    private void validateAccountForLogin(Account account) {
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
            serviceStatus = 200;
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            serviceStatus = 400;
            throw new IllegalArgumentException("Login failed :/ ");
        }
    }

    public int getStatus(){
        return serviceStatus;
    }

    public int getStatusDAO(){
        return accountDAO.getStatusDAO();
    }

    public boolean validateStatus(){
        if(getStatus() == 200 && getStatus() == (getStatusDAO())){
        return true;
        }
        else{
            return false;
        }
    }
}
