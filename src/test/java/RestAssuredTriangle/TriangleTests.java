package RestAssuredTriangle;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
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
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void negativeSidesLengthsDoNotCauseException() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"-3;-4;-5\"}", 200);
        //deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void straightLineTriangleDoesNotCauseException() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"3;4;7\"}", 200);
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    @Ignore
    public void checkImpossibleToCreateEleventhTriangle() throws Exception {
        String[] ids = new String[10];
        //String[] ids = {"0"};
        for  (int i=0; i < 10; i++) {
          ids[i]  = createTriangle("{\"separator\": \";\", \"input\": \"3;4;5\"}");
        }

        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"3;4;5\"}", 422);

        for  (int i=0; i < 10; i++) {
            deleteTriangle(getFirstExistingTriangle());
        }
    }

    @Test
    public void cleanupTriangles() {
        String id = getFirstExistingTriangle();
        while (id != null) {
            deleteTriangle(id);
            System.out.println(id + "id found");
            id = getFirstExistingTriangle();
        }
    }
}



