package Dictionary;

import java.util.HashMap;


public class Keywords {
    private static final HashMap <String, LexemeType> keywords = new HashMap<>() {{
        put("oto", LexemeType.BEGIN);
        put("już", LexemeType.END);
        put("(", LexemeType.LBRACKET);
        put(")", LexemeType.RBRACKET);
        put("+", LexemeType.ADD);
        put("or", LexemeType.ADD);
        put("-", LexemeType.SUB);
        put("*", LexemeType.MUL);
        put("and", LexemeType.MUL);
        put("/", LexemeType.DIV);
        put(":=", LexemeType.ASSIGNOP);
        put("niech", LexemeType.MAKE);
        put("przyp", LexemeType.ASSIGN);
        put("np", LexemeType.FORWARD);
        put("ws", LexemeType.BACKWARD);
        put("lw", LexemeType.LEFT);
        put("pw", LexemeType.RIGHT);
        put("sż", LexemeType.HIDE);
        put("pż", LexemeType.SHOW);
        put("ustawpoz", LexemeType.SETPOS);
        put("ukp", LexemeType.SETPENCOLOR);
        put("ukm", LexemeType.SETPAINTCOLOR);
        put("pod", LexemeType.UP);
        put("opu", LexemeType.DOWN);
        put("pisz", LexemeType.WRITE);
        put("czytaj", LexemeType.READ);
        put("kolor", LexemeType.COLOR);
        put("[", LexemeType.SQUARELBRACKET);
        put("]", LexemeType.SQUARERBRACKET);
        put("dopóki", LexemeType.WHILE);
        put("powtórz", LexemeType.REPEAT);
        put("jeślitaknie", LexemeType.IFELSE);
        put(";", LexemeType.COLON);
        put("_", LexemeType.UNDERSCORE);
        put("=", LexemeType.RELOP);
        put("<", LexemeType.RELOP);
        put(">", LexemeType.RELOP);
        put("<>", LexemeType.RELOP);
        put("<=", LexemeType.RELOP);
        put(">=", LexemeType.RELOP);
        put(",", LexemeType.SEPARATOR);
        put("\0", LexemeType.ETX);
        put("num", LexemeType.TYPE);
        put("czyść", LexemeType.CLEAN);
    }};
    public static LexemeType getLexemeType(String symbol) throws IllegalArgumentException {
        if (keywords.containsKey(symbol))
            return keywords.get(symbol);
        throw new IllegalArgumentException("Symbol not recognized");
    }
}
