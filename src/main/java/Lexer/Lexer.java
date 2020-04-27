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
                throw new IllegalArgumentException(getMessage(ErrorMessage.IDENTIFIER_SIZE_EXCEEDED));
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
                throw new IllegalArgumentException(ErrorMessage.BAD_NUMBER + getPosition());
            getDigits();
            if (source.getCurrentChar() == ',') {
                lexeme.append(source.getCurrentChar());
                source.consume();
                if(!Character.isDigit(source.getCurrentChar()))
                    throw new IllegalArgumentException(ErrorMessage.BAD_NUMBER + getPosition());
                getDigits();
            }
            type = LexemeType.NUMBER;
        }
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
    private Point getPosition() {
        return new Point(startRow, startColumn);
    }
    private String getMessage(String message){
        return message + "" + getPosition() + ")\n";
    }
}
