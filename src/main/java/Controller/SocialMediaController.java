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
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
       
       // app.post("/register", this::postAccountHandle);
        //app.post("/authors", this::postAuthorHandler);
        //app.get("example-endpoint", this::exampleHandler);
        app.start(8080);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


     /**
     * Handler to post a new author.
     * The Jackson ObjectMapper will automatically convert the JSON of the POST request into an Author object.
     * If AuthorService returns a null author (meaning posting an Author was unsuccessful), the API will return a 400
     * message (client error). There is no need to change anything in this method.
     * @param ctx the context object handles information HTTP requests and generates responses within Javalin. It will
     *            be available to this method automatically thanks to the app.post method.
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
     */
    private void postAccountHandle(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Author author = mapper.readValue(ctx.body(), Author.class);
        Author addedAuthor = authorService.addAuthor(author);
        if(addedAuthor!=null){
            ctx.json(mapper.writeValueAsString(addedAuthor));
        }else{
            ctx.status(400);
        }
    }


















}