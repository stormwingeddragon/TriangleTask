package RestAssuredTriangle;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.text.Utilities;
import java.util.ArrayList;

import static RestAssuredTriangle.Utilities.*;
import static io.restassured.RestAssured.given;
import static jdk.nashorn.internal.runtime.Debug.id;

public class TriangleTests {
    private Utilities u;

    @BeforeClass
    public void setUp() {
        u = new Utilities();
    }



    @Test
    public void checkTriangleArea() throws Exception {
        String id = createTriangle("{\"separator\": \";\", \"input\": \"3;4;5\"}");
        float area = calculateArea(id);
        Assert.assertEquals(area, 6.0);
        deleteTriangle(id);
    }

    @Test
    public void createSameTriangleTwice() throws Exception {
        String id = createTriangle("{\"separator\": \";\", \"input\": \"3;4;5\"}");
        String id2 = createTriangle("{\"separator\": \";\", \"input\": \"3;4;5\"}");
        deleteTriangle(id);
        deleteTriangle(id2);
    }


    @Test
    public void cleanupTriangles() {
        //while(deleteExistingTriangle()) {
            String id = getFirstExistingTriangle();
         deleteTriangle(id);
        //}
    }


    public boolean deleteExistingTriangle() {
       String id = getFirstExistingTriangle();
       if (id == null) return false;
       deleteTriangle(id);
       return true;
    }

//    public void deleteTriangle(String id) {
//        given().
//                header("Content-Type", "application/json").
//                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
//        when().
//                delete("https://qa-quiz.natera.com/triangle/"+id).
//        then().
//                statusCode(200).
//                log().
//                all();
//    }

//    public String createTriangle(String input) {
//        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
//        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json").
//                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
//                body(input);
//        Response response = httpRequest.post("https://qa-quiz.natera.com/triangle");
//        JsonPath jsonPathEvaluator = response.jsonPath();
//        String  id = jsonPathEvaluator.get("id");
//        System.out.println("id of newly created Triangle is " + id);
//        //return id(0);
//        return id;
//    }

//    public float calculateArea(String id) {
//        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json").
//                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22");
//        Response response = httpRequest.get("https://qa-quiz.natera.com/triangle/" + id + "/area");
//        JsonPath jsonPathEvaluator = response.jsonPath();
//        float area = jsonPathEvaluator.get("result");
//        System.out.println("area of newly created Triangle is " + area);
//        return area;
//    }

    public void getTriangleArea(String id) {
        given().
                header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
        when().
                get("https://qa-quiz.natera.com/triangle/" + id + "/area").
        then().
                statusCode(200).
                log().
                all();
    }

//    public String createTriangle() {
//        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json").
//                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
//                body("{\"separator\": \";\", \"input\": \"3;3.5;6.5\"}");
//        Response response = httpRequest.post("https://qa-quiz.natera.com/triangle");
//        JsonPath jsonPathEvaluator = response.jsonPath();
//        List<String> id = jsonPathEvaluator.get("id");
//        System.out.println("id of newly created Triangle is " + id);
//        return id(0);
//    }

    public static String getFirstExistingTriangle() {
        RequestSpecification httpRequest = RestAssured.given().
                header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22");
        Response response = httpRequest.get("https://qa-quiz.natera.com/triangle/all");
        JsonPath jsonPathEvaluator = response.jsonPath();
        ArrayList id = jsonPathEvaluator.get("id");
        return id(0);
    }


}
