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
    @Test
    public void testWhenSourceOpenedCurrentCharIsŻ() {
        Assertions.assertEquals('ż', source.getCurrentChar());
    }
    @Test
    public void testWhenSourceOpenedCurrentCoordinatesAre11() {
        Assertions.assertEquals(new Point(1,1), new Point (source.getCurrentRow(), source.getCurrentColumn()));
    }
    @Test
    public void testWhenConsumeFirstCharCurrentCharIsÓ() {
        source.consume();
        Assertions.assertEquals('ó', source.getCurrentChar());
    }
    @Test
    public void testWhenConsumeFirstCharCurrentPositionIs12() {
        source.consume();
        Assertions.assertEquals(new Point(1,2), new Point (source.getCurrentRow(), source.getCurrentColumn()));
    }
    @Test
    public void testWhenConsumeSecondCharCurrentCharIsNewLine() {
        source.consume();
        source.consume();
        Assertions.assertEquals('\n', source.getCurrentChar());
    }
    @Test
    public void testWhenConsumeSecondCharCurrentPositionIs20() {
        source.consume();
        source.consume();
        Assertions.assertEquals(new Point(2,0), new Point (source.getCurrentRow(), source.getCurrentColumn()));
    }
    @Test
    public void testWhenLastCharIsConsumedSourceIsClosed() {
        for (int i = 0; i < 4; i++)
            source.consume();
        Assertions.assertFalse(source.isOpened());
    }
    @Test
    public void testWhenEOFOccuresCurrentCharIsEOF() {
        for (int i = 0; i < 4; i++)
            source.consume();
        Assertions.assertEquals((char) 3, source.getCurrentChar());
    }
}
