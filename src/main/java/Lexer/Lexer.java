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
