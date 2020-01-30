package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author carol
 */
@Path("animal")
public class AnimalResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
//        return "{\"msg\":\"It works!\"}";
        return "It works!";
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        List<Animal> animals = createList();
        Random random = new Random();
        Animal animal = animals.get(random.nextInt(animals.size()));
        return new Gson().toJson(animal);
//        return "This is a random msg";
    }

    private List<Animal> createList() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Duck", 2017, "Quack!"));
        animals.add(new Animal("Dog", 2007, "Bark!"));
        animals.add(new Animal("Cat", 2012, "Meow!"));
        animals.add(new Animal("Bunny", 2019, "Squeak!"));
        return animals;
    }
}
