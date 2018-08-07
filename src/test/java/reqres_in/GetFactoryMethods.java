package reqres_in;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.*;


public class GetFactoryMethods {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";

    }
    @AfterClass
    public void tearDown(){
        RestAssured.reset();
    }

    @DataProvider(name="okk")
    public Object[][] provider(){
        return new Object[][]{{new Integer(1)},
                {new Integer(2)},
                {new Integer(3)},
                {new Integer(4)},
                {new Integer(5)},
                {new Integer(6)},
        };
    }

    @Test(dataProvider = "okk")
    public void getProvider(Integer n){
        expect().statusCode(200).
                body("data.id", equalTo(n))
                .body("data", allOf(hasKey("first_name"),hasKey("last_name"),hasKey("avatar"))).
                when().get("/api/users/"+n);
    }
}
