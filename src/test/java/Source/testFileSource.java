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
    public void test1_FirstCharIsŻ() {
        Assertions.assertEquals('ż', source.getCurrentChar());
    }
    @Test
    public void test2_InitialCoordinatesAre1_1() {
        Assertions.assertEquals(new Point(1,1), new Point (source.getCurrentRow(), source.getCurrentColumn()));
    }
    @Test
    public void test3_SecondCharIsÓ() {
        source.consume();
        Assertions.assertEquals('ó', source.getCurrentChar());
    }
    @Test
    public void test4_AfterConsumingFirstCharCoordinatesChangeTo1_2() {
        source.consume();
        Assertions.assertEquals(new Point(1,2), new Point (source.getCurrentRow(), source.getCurrentColumn()));
    }
    @Test
    public void test5_ThirdCharIsNewLine() {
        source.consume();
        source.consume();
        Assertions.assertEquals('\n', source.getCurrentChar());
    }
    @Test
    public void test6_PositionOfThirdCharIs2_0() {
        source.consume();
        source.consume();
        Assertions.assertEquals(new Point(2,0), new Point (source.getCurrentRow(), source.getCurrentColumn()));
    }
    @Test
    public void test7_WhenEOFOccuresCurrentCharIsETX() {
        for (int i = 0; i < 4; i++)
            source.consume();
        Assertions.assertEquals(Source.ETX, source.getCurrentChar());
    }
}
