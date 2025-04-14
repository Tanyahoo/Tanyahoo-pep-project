package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

import DAO.*;
import Model.*;
import Service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {


    UserAccountService accountService;
    MessageService messageService;


    // no args constructor with new service objects
    public SocialMediaController(){
        this.accountService = new UserAccountService();
        this.messageService = new MessageService();
    }


    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // calls postAccountHandler method on path /resgister
        app.post("/register", this::postAccountHandler);
        // calls method on login path
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        //app.start(8080);

        return app;
    }

   

     /**
     * Handler to post a new account to database
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object
     */
    private void postAccountHandler(Context ctx) {
        // use context object to turn request body into account object
        Account account = ctx.bodyAsClass(Account.class);
        Account addedAccount = accountService.createAccount(account);
    
        if (addedAccount != null) {
            ctx.status(200).json(addedAccount); // 200: resource successfully created
        } else {
            ctx.status(400);
        }
    }



     /**
     * Handler to verify login with username and password
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin, it will
     *            be available to this method automatically thanks to the app.post method.
     */
    private void loginHandler(Context ctx) {
        // use context object to turn request body into account object
        Account credentials = ctx.bodyAsClass(Account.class);
        // accessing username and password from account object
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        // verifying the username and password
        Account account = accountService.login(username, password);
        if (account != null) {
            // returning status
            ctx.status(200).json(account); 
        } else {
            ctx.status(401); 
        }
    }




    /**
     * Handler to post a new message
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.post method.
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
     */
    private void createMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage == null){
            ctx.status(400);
        }else{
            ctx.json(mapper.writeValueAsString(addedMessage));
        }
    }

    

    
    // Handler to retrieve all messages 
    
    private void getAllMessagesHandler(Context ctx){
        // returns to client flights in json format
        ctx.json(messageService.getAllMessages());
    }













}