package Lexer;

import Dictionary.ErrorMessage;
import Dictionary.Keywords;
import Dictionary.LexemeType;
import Source.Source;
import java.awt.Point;

public class Lexer {
    private Source source;
    private StringBuilder lexeme;
    private int startRow;
    private int startColumn;
    private LexemeType type;
    private Token token;
    private final int MAX_IDENT_LEN = 30;
    private final int MAX_STRING_LEN = 400;

    public Lexer (Source source) {
        this.source = source;
        //consume();
    }

    public Token getToken() { return token; }
    private void setToken() {
        token = new Token (type, lexeme.toString(), new Point(startRow, startColumn));
    }
    private void skipWhiteSpacesAndComments() {
        while (Character.isWhitespace(source.getCurrentChar()) || source.getCurrentChar() == ';'){
            if (source.getCurrentChar() == ';')
                skipComments();
            else
                source.consume();
        }
    }
    private void skipComments() {
        if (source.getCurrentChar() == ';') {
            while (source.getCurrentChar() != '\n')
                source.consume();
            source.consume();
        }
    }
    private void getIdentifierOrKeyword() throws IllegalArgumentException {
        while (Character.isLetterOrDigit(source.getCurrentChar()) || source.getCurrentChar() == '_'){
            if (lexeme.length() > MAX_IDENT_LEN)
                throw new IllegalArgumentException(ErrorMessage.IDENTIFIER_SIZE_EXCEEDED + printPosition());
            lexeme.append(source.getCurrentChar());
            source.consume();
        }
        setLexemeType();
    }
    private void getNumber() throws IllegalArgumentException {
        if (source.getCurrentChar() == '0') {
            lexeme.append(source.getCurrentChar());
            source.consume();
            if (source.getCurrentChar() == '0')
                throw new IllegalArgumentException(ErrorMessage.BAD_NUMBER + printPosition());
            getDigits();
            if (source.getCurrentChar() == ',') {
                lexeme.append(source.getCurrentChar());
                source.consume();
                if(!Character.isDigit(source.getCurrentChar()))
                    throw new IllegalArgumentException(ErrorMessage.BAD_NUMBER + printPosition());
                getDigits();
            }
            type = LexemeType.NUMBER;
        }
    }
    private void getString() throws IllegalArgumentException {
        // consume '\"' character that starts the string
        source.consume();
        while (source.getCurrentChar() != '\"') {
            if (lexeme.length() > MAX_STRING_LEN)
                throw new IllegalArgumentException(ErrorMessage.STRING_SIZE_EXCEEDED + printPosition());
            if (source.getCurrentChar() == '\\')
                escapeChar();
            else {
                lexeme.append(source.getCurrentChar());
                source.consume();
            }
        }
        // consume '\"' character that ends the string
        source.consume();
        type = LexemeType.STRING;
    }
    private void escapeChar() {
        source.consume();
        lexeme.append(source.getCurrentChar());
        source.consume();
    }
    private void getDigits() {
        while (Character.isDigit(source.getCurrentChar())) {
            lexeme.append(source.getCurrentChar());
            source.consume();
        }
    }
    private void setLexemeType() {
        type = Keywords.getLexemeType(lexeme.toString());
        if (type.equals(LexemeType.UNDEFINED))
            type = Dictionary.LexemeType.IDENT;
    }
    private String printPosition() {
        return startRow + ", " + startColumn;
    }
}
