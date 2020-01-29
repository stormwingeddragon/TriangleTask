package RestAssuredTriangle;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static jdk.nashorn.internal.runtime.Debug.id;

public class Utilities {

    public static float calculateArea(String id) {
        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22");
        Response response = httpRequest.get("https://qa-quiz.natera.com/triangle/" + id + "/area");
        JsonPath jsonPathEvaluator = response.jsonPath();
        float area = jsonPathEvaluator.get("result");
        System.out.println("area of newly created Triangle is " + area);
        return area;
    }

    public static String createTriangle(String input) {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
                body(input);
        Response response = httpRequest.post("https://qa-quiz.natera.com/triangle");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String  id = jsonPathEvaluator.get("id");
        System.out.println("id of newly created Triangle is " + id);
        //return id(0);
        return id;
    }

    public static void deleteTriangle(String id) {
        given().
                header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
                when().
                delete("https://qa-quiz.natera.com/triangle/"+id).
                then().
                statusCode(200).
                log().
                all();
    }

    public static String getFirstExistingTriangle() {
        RequestSpecification httpRequest = RestAssured.given().
                header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22");
        Response response = httpRequest.get("https://qa-quiz.natera.com/triangle/all");
        JsonPath jsonPathEvaluator = response.jsonPath();
        ArrayList id = jsonPathEvaluator.get("id");
        return id(0);
    }

    public static String createTriangle() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
                body("{\"separator\": \";\", \"input\": \"3;3.5;6.5\"}");
        Response response = httpRequest.post("https://qa-quiz.natera.com/triangle");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Then simply query the JsonPath object to get a String value of the node
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        String id = jsonPathEvaluator.get("id");

        // Let us print the city variable to see what we got
        System.out.println("City received from Response " + id);

        // Validate the response
        //Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");

        return id;
    }

//    public void deleteTriangle(String id) {
//        given().
//                header("Content-Type", "application/json").
//                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
//                when().
//                delete("https://qa-quiz.natera.com/triangle/"+id).
//                then().
//                statusCode(200).
//                log().all();
//    }

    public int amountOfExistingTriangles() {
        given().
                header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
                when().
                get("https://qa-quiz.natera.com/triangle/all").
                then().
                statusCode(200).
                log().all();

        return 1;
    }

    public int[] idsOfExistingTriangles() {

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22");
        //body("{\"separator\": \";\", \"input\": \"3;3.5;6.5\"}");
        Response response = httpRequest.get("https://qa-quiz.natera.com/triangle/all");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Then simply query the JsonPath object to get a String value of the node
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        String id = jsonPathEvaluator.get("id");

        // Let us print the city variable to see what we got
        System.out.println("City received from Response " + id);

        // Validate the response
        //Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");


        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        System.out.println("Response Body is: " + body.asString());

        return new int[]{0};

//        given().
//                header("Content-Type", "application/json").
//                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
//                when().
//                get("https://qa-quiz.natera.com/triangle/all").
//                then().
//                statusCode(200).
//                log().all();
    }

    public String getIdOfFirstExistingTriangle() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given().
                header("Content-Type", "application/json").
                header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22").
                body("{\"separator\": \";\", \"input\": \"3;3.5;6.5\"}");
        Response response = httpRequest.get("https://qa-quiz.natera.com/triangle/all");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Then simply query the JsonPath object to get a String value of the node
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        String id = jsonPathEvaluator.get("id");

        // Let us print the city variable to see what we got
        System.out.println("City received from Response " + id);

        // Validate the response
        //Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");

        return id;
    }
    }







