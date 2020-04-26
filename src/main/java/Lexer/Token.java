import Dictionary.LexemeType;

import java.awt.*;

public class Token {
    private LexemeType type;
    private String value;
    private Point position;

    public Token(LexemeType type, String value, Point position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    public LexemeType getType() { return type; }
    public String getValue() { return value; }
    public Point getPosition() { return position; }
}
