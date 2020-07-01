package Lexer;

import Dictionary.ErrorMessage;
import Dictionary.Keywords;
import Dictionary.LexemeType;
import Source.Source;
import java.awt.Point;

public class Lexer {
    private final Source source;
    private StringBuilder lexeme;
    private int startRow;
    private int startColumn;
    private LexemeType type;
    private Token token;
    private final int MAX_IDENT_LEN = 30;
    private final int MAX_STRING_LEN = 400;
    private final char ETX = (char) 3;
    private boolean hasTokens;

    public Lexer (Source source) {
        this.source = source;
        hasTokens = true;
        consume();
    }

    public Token getCurrentToken() { return token; }

    public void consume() {
        if (source.getCurrentChar() != Source.ETX) {
            getLexeme();
            setToken();
        }
        else
            hasTokens = false;
    }
    private void getLexeme() {
        createNewLexemeBuffer();
        skipWhiteSpacesAndComments();
        setPosition();
        completeLexeme();
    }
    private void completeLexeme() {
        try {
            if (Character.isLetter(source.getCurrentChar()))
                getIdentifierOrKeyword();
            else if (Character.isDigit(source.getCurrentChar()))
                getNumber();
            else if (source.getCurrentChar() == '\"')
                getString();
            else
                getSpecialSymbol();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
    private void createNewLexemeBuffer() {
        lexeme = new StringBuilder();
    }
    private void setPosition() {
        startRow = source.getCurrentRow();
        startColumn = source.getCurrentColumn();
    }
    private void setToken() {
        token = new Token (type, lexeme.toString(), new Point(startRow, startColumn));
    }
    private void skipWhiteSpacesAndComments() {
        while (Character.isWhitespace(source.getCurrentChar()) || source.getCurrentChar() == ';') {
            if (source.getCurrentChar() == ';')
                skipComments();
            else
                source.consume();
        }
        if (source.getCurrentChar() == ETX)
            setETXToken();
    }
    private void skipComments() {
        while (source.getCurrentChar() != '\n' || source.getCurrentChar() != ETX)
            source.consume();
        source.consume();
    }
    private void getIdentifierOrKeyword() throws IllegalArgumentException {
        while (Character.isLetterOrDigit(source.getCurrentChar()) || source.getCurrentChar() == '_') {
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
        consumeCharacter('\"');
        while (source.getCurrentChar() != '\"' && source.getCurrentChar() != ETX) {
            if (lexeme.length() > MAX_STRING_LEN)
                throw new IllegalArgumentException(ErrorMessage.STRING_SIZE_EXCEEDED + printPosition());
            if (source.getCurrentChar() == '\\')
                escapeChar();
            else {
                lexeme.append(source.getCurrentChar());
                source.consume();
            }
        }
        consumeCharacter('\"');
        type = LexemeType.STRING;
    }
    private void getSpecialSymbol() {
        if (tryDoubleSymbol()) {
            lexeme.append(source.getCurrentChar());
            source.consume();
        }
        else
            trySingleSymbol();
    }
    private boolean tryDoubleSymbol() {
        lexeme.append(source.getCurrentChar());
        source.consume();
        type = Keywords.getLexemeType(lexeme.toString() + source.getCurrentChar());
        return !type.equals(LexemeType.UNDEFINED);
    }
    private void trySingleSymbol() {
        type = Keywords.getLexemeType(lexeme.toString());
    }
    private void escapeChar() {
        consumeCharacter('\\');
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
    private void consumeCharacter(char character) {
        if (source.getCurrentChar() == character)
            source.consume();
        else
            throw new IllegalArgumentException(character + " not expected here");
    }
    private void setETXToken () {
        lexeme.append(ETX);
        type = LexemeType.ETX;
    }
}
