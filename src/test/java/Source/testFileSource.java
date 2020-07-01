package Source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;

public class testFileSource {
    final String filePath = "test_code.txt";
    FileSource source;

    @BeforeEach
    private void openSource() {
        source = new FileSource(filePath);
    }
    // test 1
    @Test
    public void testWhenSourceOpenedCurrentCharIsŻ() {
        Assertions.assertEquals('ż', source.getCurrentChar());
    }
    // test 2
    @Test
    public void testWhenSourceOpenedCurrentCoordinatesAre11() {
        Assertions.assertEquals(new Point(1,1), new Point (source.getCurrentRow(), source.getCurrentColumn()));
    }
    // test 3
    @Test
    public void testWhenConsumeFirstCharCurrentCharIsÓ() {
        source.consume();
        Assertions.assertEquals('ó', source.getCurrentChar());
    }
    // test 4
    @Test
    public void testWhenConsumeFirstCharCurrentPositionIs12() {
        source.consume();
        Assertions.assertEquals(new Point(1,2), new Point (source.getCurrentRow(), source.getCurrentColumn()));
    }
    // test 5
    @Test
    public void testWhenConsumeSecondCharCurrentCharIsNewLine() {
        source.consume();
        source.consume();
        Assertions.assertEquals('\n', source.getCurrentChar());
    }
    // test 6
    @Test
    public void testWhenConsumeSecondCharCurrentPositionIs20() {
        source.consume();
        source.consume();
        Assertions.assertEquals(new Point(2,0), new Point (source.getCurrentRow(), source.getCurrentColumn()));
    }
    // test 7
    @Test
    public void testWhenEOFOccuresCurrentCharIsETX() {
        for (int i = 0; i < 4; i++)
            source.consume();
        Assertions.assertEquals(Source.ETX, source.getCurrentChar());
    }
}
