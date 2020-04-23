package Source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class testStringSource {
    final String sourceString = "żó\n";
    StringSource source = new StringSource(sourceString);
    @Test
    public void testFirstCharIsŻ() {
        Assertions.assertEquals('ż', source.getCurrentChar());
    }
    @Test
    public void testCoordinatesAtTheBeginningAre11() {
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
        Assertions.assertTrue(source.isEnd());
    }
    @Test
    public void testWhenEndOfStringOccuredCurrentCharIsTheLastValidChar() {
        for (int i = 0; i < 4; i++)
            source.consume();
        Assertions.assertEquals('\n', source.getCurrentChar());
    }
}
