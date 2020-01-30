package rest;

import com.google.gson.Gson;
import entity.Customer;
import facade.CustomerFacade;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author carol
 */
@Path("customer")
public class CustomerResource {

    private CustomerFacade facade;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    public CustomerResource(EntityManagerFactory emf) {
        this.facade = CustomerFacade.getCustomerFacade(emf);
    }

    public CustomerFacade getFacade() {
        return facade;
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CustomerResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        CustomerResource res = new CustomerResource(Persistence.createEntityManagerFactory("pu"));
        CustomerFacade facade = res.getFacade();
        return new Gson().toJson(facade.allCustomers());
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        CustomerResource res = new CustomerResource(Persistence.createEntityManagerFactory("pu"));
        CustomerFacade facade = res.getFacade();
        Random random = new Random();
        int randomId = random.nextInt(facade.getNumberOfCustomers() + 1);
        Customer customer = facade.findByID(randomId);
        return new Gson().toJson(customer);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@PathParam("id") int id) {
        CustomerResource res = new CustomerResource(Persistence.createEntityManagerFactory("pu"));
        CustomerFacade facade = res.getFacade();
        return new Gson().toJson(facade.findByID(id));
    }

}
