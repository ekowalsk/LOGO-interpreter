package Source;

public abstract class Source {
    protected char currentChar;
    protected int currentRow;
    protected int currentColumn;
    public final char ETX = (char) 3;

    public char getCurrentChar() {
        return currentChar;
    }
    public int getCurrentColumn() { return currentColumn; }
    public int getCurrentRow() { return currentRow; }

    public abstract void consume();
    public abstract boolean isOpened();
    protected void updateCoordinates() {
        if (currentChar == '\n') {
            currentRow++;
            currentColumn = 0;
        }
        else
            currentColumn++;
    }
}
