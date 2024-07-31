package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
     /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

     private AccountService accountService;

     public SocialMediaController(AccountService accountService){
         this.accountService = accountService;
     }

    public Javalin startAPI() {
            Javalin app = Javalin.create();
            //app.get("example-endpoint", this::exampleHandler);
            app.post("/register", this::addAccount);
            app.post("/login", this::loginAccount);
            return app;
        }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    //     context.json("sample text");
    // }
    private void addAccount(Context ctx){
        Account account = ctx.bodyAsClass(Account.class);
        Account newAccount = accountService.addAccount(account);
        ctx.status(201).json(newAccount);
    }

    private void addAccount(Context ctx){
        Account account = ctx.bodyAsClass(Account.class);
        Account newAccount = accountService.addAccount(account);
        ctx.status(201).json(newAccount);
    }

}