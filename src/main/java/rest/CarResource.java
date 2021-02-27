package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.CarEntity;
import facades.CarsFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

@Path("cars")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final CarsFacade FACADE = CarsFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarsCount() {
        long count = FACADE.getCarsCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCars() {
        List<CarEntity> allCars = FACADE.getAllCars();
        return GSON.toJson(allCars);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarById(@PathParam("id") int id) {
        CarEntity car = FACADE.getCarsById(id);
        return GSON.toJson(car);
    }

    @GET
    @Path("/model/{model}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarByName(@PathParam("model") String model) {
        List<CarEntity> CarList = FACADE.getCarsByModel(model);
        return GSON.toJson(CarList);
    }

    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateDB();
        return "{\"msg\":\"5 rows added\"}";
    }
}
