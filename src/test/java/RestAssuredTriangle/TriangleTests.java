package RestAssuredTriangle;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.text.Utilities;

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
    public void checkTrianglePerimeter() throws Exception {
        String id = createTriangle("{\"separator\": \";\", \"input\": \"3;4;5\"}");
        float perimeter = calculatePerimeter(id);
        Assert.assertEquals(perimeter, 12.0);
        deleteTriangle(id);
    }

    @Test
    public void incompatibleSidesLength() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"3;4;50\"}", 422);
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





    }



