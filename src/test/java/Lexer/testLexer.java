package Lexer;

import Dictionary.LexemeType;
import Source.StringSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class testLexer {
    private Lexer lexer;

    private void initLexer (String sourceString) {
        lexer = new Lexer (new StringSource(sourceString));
    }

    @Test
    public void testWhenFirstIsBEGINTokenLexerRecognizesIt() {
        initLexer("   oto  \" string \"");
        Token token1 = lexer.getCurrentToken();
        Assertions.assertEquals(LexemeType.BEGIN, token1.getType(), "Type of first lexeme should be BEGIN");
        Assertions.assertEquals("oto", token1.getValue(), "Value of first lexeme should be oto");
        Assertions.assertEquals(new Point(1, 5), token1.getPosition(), "Position of first token should be 1,5");
    }
    @Test
    public void testWhenSecondIsStringLiteralLexerRecognizesIt() {
        initLexer("   oto  \" string \"");
        lexer.consume();
        Token token2 = lexer.getCurrentToken();
        Assertions.assertEquals(LexemeType.STRING, token2.getType(), "Type of second lexeme should be STRING");
        Assertions.assertEquals(" string ", token2.getValue(), "Value of second lexeme should be string");
        Assertions.assertEquals(new Point(1, 5), token2.getPosition(), "Position of first token should be 1,5");
    }

}
