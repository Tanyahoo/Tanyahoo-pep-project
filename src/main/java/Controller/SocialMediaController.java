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
        //app.start(8080);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
   // private void exampleHandler(Context context) {
   //     context.json("sample text");
   // }


     /**
     * Handler to post a new account to database.
     * The Jackson ObjectMapper will automatically convert the JSON of the POST request into an Account object.
     * If UserAccountService returns a null account (meaning posting an Account was unsuccessful), the API will return a 400
     * message (client error). There is no need to change anything in this method.
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.post method.
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
     */
    private void postAccountHandler(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);
        Account addedAccount = accountService.createAccount(account);
    
        if (addedAccount != null) {
            ctx.status(200).json(addedAccount); // 200: resource successfully created
        } else {
            ctx.status(400);
        }
    }

     /**
     * 
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





 /* 
    private void updateAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Flight flight = mapper.readValue(ctx.body(), Flight.class);
        int flight_id = Integer.parseInt(ctx.pathParam("flight_id"));
        Flight updatedFlight = flightService.updateFlight(flight_id, flight);
        System.out.println(updatedFlight);
        if(updatedFlight == null){
            ctx.status(400);
        }else{
            ctx.json(mapper.writeValueAsString(updatedFlight));
        }

    }

*/













}