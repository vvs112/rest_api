package reqres_in;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.RequestSpecificationImpl;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import org.testng.annotations.*;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.File;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Requests must contain <**>formParams||params</**>
 * in order to chnage Response body
 * Use RequestSpecBuilder*/
public class PostMethods {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";

    }
    @AfterClass
    public void tearDown(){
        RestAssured.reset();
    }

    @Test
    public void defaultPost(){
        RequestSpecification req = new RequestSpecBuilder().setContentType("application/json")
                .setBody("{\"name\":\"Kolia\", \"job\":\"test\"}")
                .build();
        given(req).post("api/users").then().statusCode(201);
    }

    @Test
    public  void defaultPatch(){
        RequestSpecification req = new RequestSpecBuilder().setContentType("application/json")
                .setBody("{\"name\":\"Pappi\", \"job\":\"test\"}")
                .build();
        given(req).post("api/users/2").then()
                .statusCode(201).and().body("name", is("Pappi"));
    }

    @Test
    public void postRegister(){
        given().post("/api/register").then().statusCode(415);
    }

    @Test
    public void postLogin(){
        given().post("/api/login").then().statusCode(415);
    }
}
