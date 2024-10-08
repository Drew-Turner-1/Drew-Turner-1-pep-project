package Controller;

//import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
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
    private MessageService messageService;

    public SocialMediaController(){
            this.accountService = new AccountService();
            this.messageService = new MessageService();
    }

    public Javalin startAPI() {
            Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);

        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageById);
        app.get("accounts/{account_id}/messages", this::getAllUserMessages);
        app.post("/register", this::addAccount);
        app.post("/login", this::loginAccount);
        app.post("/messages", this::createMessage);
        app.patch("/messages/{message_id}", this::updateMessageById);
        app.delete("/messages/{message_id}", this::deleteMessageById);
        
        
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
        
        if((newAccount != null)){
            ctx.status(200).json(newAccount);
        }
        else{
            ctx.status(400);
        }
    }

    private void loginAccount(Context ctx){
        Account account = ctx.bodyAsClass(Account.class);
        Account validAccount = accountService.loginAccount(account);

        if((validAccount != null)){
            ctx.status(200).json(validAccount);
        }
        else{
            ctx.status(401);
        }
    }

    private void createMessage(Context ctx){
        Message message = ctx.bodyAsClass(Message.class);
        Message newMessage = messageService.createMessage(message);

        if((newMessage != null)){
            ctx.status(200).json(newMessage);
        }
        else{
            ctx.status(400);
        }
    }

    private void getAllMessages(Context ctx){
        List<Message> allMessages = new ArrayList<Message>(messageService.getAllMessages());
        ctx.status(200).json(allMessages);
    }

    private void getAllUserMessages(Context ctx){
        int postingUser = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> allMessagesById = new ArrayList<Message>(messageService.getAllUserMessages(postingUser));
        if(allMessagesById != null){
            ctx.status(200).json(allMessagesById);
        }    
        ctx.status(200); 
    }

    private void getMessageById(Context ctx){
        int messageIdOnly = Integer.parseInt(ctx.pathParam("message_id"));
        Message messageById = messageService.getMessageById(messageIdOnly);

        if((messageById != null)){
            ctx.status(200).json(messageById);
        }    
        ctx.status(200);    
    }

    private void updateMessageById(Context ctx){
        int messageIdOnly = Integer.parseInt(ctx.pathParam("message_id"));

        //need to change this...
        //String textToUpdate = "";
        Message textToUpdate = ctx.bodyAsClass(Message.class);        
        Message updatedMessage = messageService.updateMessageById(messageIdOnly, textToUpdate);
        //ctx.status(200).json(updatedMessage);

        if((updatedMessage != null)){
            ctx.status(200).json(updatedMessage);
        }
        else{
            ctx.status(400);
        }
        
    }


    private void deleteMessageById(Context ctx){
        int messageIdOnly = Integer.parseInt(ctx.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessageById(messageIdOnly);
        if((deletedMessage != null)){
            ctx.status(200).json(deletedMessage);
        }    
        ctx.status(200);   
    }

}