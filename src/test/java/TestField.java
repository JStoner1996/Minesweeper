import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TestField {

    Field testField = new Field(4, 4);


    @Test
    public void testIsMine() {
        testField.getCell(1,1).setMine(false);
        Assertions.assertEquals(false, testField.getCell(1,1).isMine, "Cell is not mine");

        testField.getCell(1,1).setMine(true);
        Assertions.assertEquals(true, testField.getCell(1,1).isMine, "Cell is now mine");


    }

    @Test
    public void testIsVisible() {
        testField.getCell(1,1).setVisible(false);
        Assertions.assertEquals(false, testField.getCell(1,1).isVisible, "Cell is not visible");

        testField.getCell(1,1).setVisible(true);
        Assertions.assertEquals(true, testField.getCell(1,1).isVisible, "Cell is now visible");
    }


    }