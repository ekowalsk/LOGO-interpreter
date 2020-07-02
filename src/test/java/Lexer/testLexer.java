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
    public void test1_recognizeBEGINToken() {
        initLexer("   oto  \" string \"");
        Token token1 = lexer.getCurrentToken();
        Assertions.assertEquals(LexemeType.BEGIN, token1.getType(), "Type of first lexeme should be BEGIN");
        Assertions.assertEquals("oto", token1.getValue(), "Value of first lexeme should be oto");
        Assertions.assertEquals(new Point(1, 4), token1.getPosition(), "Position of first token should be (1,4)");
    }
    @Test
    public void test2_recognizeStringLiteralAsSecondToken() {
        initLexer("   oto  \" string \"");
        lexer.consume();
        Token token2 = lexer.getCurrentToken();
        Assertions.assertEquals(LexemeType.STRING, token2.getType(), "Type of second lexeme should be STRING");
        Assertions.assertEquals(" string ", token2.getValue(), "Value of second lexeme should be string");
        Assertions.assertEquals(new Point(1, 9), token2.getPosition(), "Position of second token should be (1,9)");
    }
    @Test
    public void test3_RecognizeNumberAsFirstToken() {
        initLexer("99");
        Token token = lexer.getCurrentToken();
        Assertions.assertEquals(LexemeType.NUMBER, token.getType(), "Type of second lexeme should be NUMBER");
        Assertions.assertEquals("99", token.getValue(), "Value of second lexeme should be 99");
        Assertions.assertEquals(new Point(1, 1), token.getPosition(), "Position of first token should be (1,1)");
    }
    @Test
    public void test4_RecognizeNumberAsFirstToken() {
        initLexer("99,55");
        Token token = lexer.getCurrentToken();
        Assertions.assertEquals(LexemeType.NUMBER, token.getType(), "Type of second lexeme should be NUMBER");
        Assertions.assertEquals("99,55", token.getValue(), "Value of second lexeme should be 99");
        Assertions.assertEquals(new Point(1, 1), token.getPosition(), "Position of first token should be (1,1)");
    }
    @Test
    public void test5_RecognizeDoubleSpecialCharacter() {
        initLexer(":=");
        Token token = lexer.getCurrentToken();
        Assertions.assertEquals(LexemeType.ASSIGNOP, token.getType(), "Type of second lexeme should be ASSIGNOP");
        Assertions.assertEquals(":=", token.getValue(), "Value of second lexeme should be :=");
    }
    @Test
    public void test6_RecognizeSingleSpecialCharacter() {
        initLexer("*");
        Token token = lexer.getCurrentToken();
        Assertions.assertEquals(LexemeType.MUL, token.getType(), "Type of second lexeme should be MUL");
        Assertions.assertEquals("*", token.getValue(), "Value of second lexeme should be *");
    }
}
