package RestAssuredTriangle;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static RestAssuredTriangle.Utilities.*;

public class TriangleTests {

    @Test
    public void checkTriangleArea() throws Exception {
        String id = createTriangle("{\"separator\": \";\", \"input\": \"3;4;5\"}");
        float area = calculateArea(id);
        Assert.assertEquals(area, 6.0, 0.0001);
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
    public void oneSideZero() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"3;4;0\"}", 422);
        deleteTriangle(getFirstExistingTriangle());
    }

    //Possible Bug: Possible to create triangle with all sides as 0
    @Test
    public void allSidesZero() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"0;0;0\"}", 200);
        deleteTriangle(getFirstExistingTriangle());
    }

    //Possible Bug: Ignores fourth side (possible bug)
    @Test
    public void fourSides() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"3;4;5;4\"}", 200);
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void twoSides() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"3;4\"}", 422);
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void wrongSeparator() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \",\", \"input\": \"3;4;5\"}", 422);
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void differentSeparator() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \",\", \"input\": \"3,4,6.55\"}", 200);
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void oneOfSeparatorsDiffers() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \",\", \"input\": \"3,4;3.55\"}", 422);
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void noInput() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \",\", \"3,4;3.55\"}", 400);
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void noSeparatorSpecified() throws Exception {
        createTriangleAndCheckResponse("{\"input\": \"3;4;5\"}", 200);
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void noSeparatorSpecifiedCommaUsed() throws Exception {
        createTriangleAndCheckResponse("{\"input\": \"3,4,5\"}", 422);
        deleteTriangle(getFirstExistingTriangle());
    }

    //Possible bug: negative side values accepted
    @Test
    public void negativeSidesLengthsDoNotCauseException() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"-3;-4;-5\"}", 200);
        deleteTriangle(getFirstExistingTriangle());
    }

    //Possible bug: straight line triangle
    @Test
    public void straightLineTriangleDoesNotCauseException() throws Exception {
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"3;4;7\"}", 200);
        deleteTriangle(getFirstExistingTriangle());
    }

    //Bug: area of a triangle with 2x Float.MAX_VALUE sides and third side as 1 is equal to 0
    @Ignore
    @Test
    public void maxValueTwoSidesAreaCalculation() throws Exception {
        float side = Float.MAX_VALUE;
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"" +side+ ";" +side+ ";1\"}", 200);
        calculateArea(getFirstExistingTriangle());
        deleteTriangle(getFirstExistingTriangle());
    }

    @Test
    public void maxValueSidesBoundaryCondition() throws Exception {
        float side = Float.MAX_VALUE;
        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"" +side+ ";" +side+ ";"+side+"\"}", 200);
        deleteTriangle(getFirstExistingTriangle());
    }

    //Bug: Possible to create 11 triangles instead of 10
    @Test
    @Ignore
    public void checkImpossibleToCreateEleventhTriangle() throws Exception {
        String[] ids = new String[10];
        for  (int i=0; i < 10; i++) {
          ids[i]  = createTriangle("{\"separator\": \";\", \"input\": \"3;4;5\"}");
        }

        createTriangleAndCheckResponse("{\"separator\": \";\", \"input\": \"3;4;5\"}", 422);

        for  (int i=0; i < 10; i++) {
            deleteTriangle(getFirstExistingTriangle());
        }
    }

    @AfterClass
    public void cleanupTriangles() {
        String id = getFirstExistingTriangle();
        while (id != null) {
            deleteTriangle(id);
            System.out.println(id + "id found");
            id = getFirstExistingTriangle();
        }
    }
}



