package rest;

import com.google.gson.Gson;
import dto.BankCustomerDTO;
import facades.BankCustomerFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bankcustomer")
public class BankCustomerResource {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private BankCustomerFacade facade = BankCustomerFacade.getBankCustomerFacade(emf);
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        return new Gson().toJson(facade.getAllBankCustomers());
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@PathParam("id") int id) {
        return new Gson().toJson(facade.getCustomerByID(id));
    }

 
}
