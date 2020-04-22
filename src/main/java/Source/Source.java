package Source;

public abstract class Source {
    private char currentChar;
    private int currentRow;
    private int currentColumn;

    public char getCurrentChar() { return currentChar; }
    public int getCurrentColumn() { return currentColumn; }
    public int getCurrentRow() { return currentRow; }

    public void consume(){;}
}
