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
        String id = getFirstExistingTriangle();
        while (id != null) {
            //String id = getFirstExistingTriangle();
            deleteTriangle(id);
            System.out.println(id + "id found");
        }
    }


        public boolean deleteExistingTriangle() {
            String id = getFirstExistingTriangle();
            if (id == null) return false;
            System.out.println("triangle id" + id);
            deleteTriangle(id);
            return false;
        }


        public static String getFirstExistingTriangle () {
            RequestSpecification httpRequest = RestAssured.given().
                    header("Content-Type", "application/json").
                    header("X-User", "583e29b5-0ade-4e86-bb2e-5ab7529c8a22");
            Response response = httpRequest.get("https://qa-quiz.natera.com/triangle/all");
            JsonPath jsonPathEvaluator = response.jsonPath();
            ArrayList id = jsonPathEvaluator.get("id");
            if (id.isEmpty()) return null;
            System.out.println("collected ids" + id);
            System.out.println(id.get(0));
            System.out.println(id.get(0).toString());
            //System.out.println(id.get(1));
            return id.get(0).toString();
            //return id;
        }
    }



