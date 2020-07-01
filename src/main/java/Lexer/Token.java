package Lexer;

import Dictionary.LexemeType;

import java.awt.*;

public class Token {
    private final LexemeType type;
    private final String value;
    private final Point position;

    public Token(LexemeType type, String value, Point position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    public LexemeType getType() { return type; }
    public String getValue() { return value; }
    public Point getPosition() { return position; }
}
