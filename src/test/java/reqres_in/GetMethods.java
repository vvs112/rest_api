package reqres_in;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GetMethods {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";
       // RestAssured.config = config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true));

    }
    @AfterClass
    public void tearDown(){
        RestAssured.reset();
    }


    @Test
    public void getUser(){
         expect().statusCode(200).
                 body("data.id", equalTo(2)).body("data", hasKey("id")).
                 when().get("/api/users/2");
    }

    @Test
    public void getUsers(){
        expect().statusCode(200).
                body(containsString("total_pages")).
                body("data[0].id",equalTo(4)).
                when().get("/api/users?page=2");
    }

    @Test
    public void badGetRequest(){
        expect().statusCode(404).
                body(is("{}")).
                when().get("/api/users/23");
    }


    @Test
    public void responseValidationA(){
        Response response = get("/api/users?page=3");

        assertThat(response.getHeader("Content-Type"), equalTo("application/json; charset=utf-8"));
        assertThat(response.header("Connection").length(), is(10));

    }

    @Test
    public void responseValidationB(){
        Headers headers = get("/api/users?page=4").headers();
        List<Header> values = headers.asList();
        //values.forEach(System.out::println);
        assertThat(values.size(), isOneOf(14,13,11,12));
    }

    @Test
    public void jsonMapCheck(){
        Map<String, List<?>> m = get("/api/users?page=4").as(Map.class);
        List<?> l = m.get("data");
        assertThat(l.stream().count(), is(3L));
    }

    @Test
    public void delayCheck() {
        Response response = get("/api/users?page=4");
        JsonPath jp = response.jsonPath();
        assertThat(jp.get("page"), is(4));


    }

    @Test
    public void simplePost(){
        RequestSpecification req = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
        given(req).expect().statusCode(201).
                when().post("/api/users");
    }
}
