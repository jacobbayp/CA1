package rest;


import dtos.MemberDTO;
import entities.MemberEntity;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import static rest.MemberResourceTest.startServer;
import utils.EMF_Creator;
//@Disabled

public class MemberResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static MemberEntity me1, me2, me3;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();

        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        me1 = new MemberEntity("Jacob", "cph-jp385", "blå");
           me2 = new MemberEntity("Jonas", "cph-jj467", "rød");
              me3 = new MemberEntity("Casper", "cph-ct139", "grøn");
                           

        try {
            em.getTransaction().begin();
            em.createNamedQuery("MemberEntity.deleteAllRows").executeUpdate();
                em.persist(me1);
                em.persist(me2);
                em.persist(me3);
               

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
   public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/members").then().statusCode(200);
    }
   
 
    @Test
    public void countTest() throws Exception {
        given()
                .contentType("application/json")
                .get("/members/count").then()
                .assertThat()
                .body("count", equalTo(3));
    }

    
        @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/members/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Vi styrer for vildt!"));
    }
    
    
}