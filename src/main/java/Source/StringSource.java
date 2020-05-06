package Source;

public class StringSource extends Source {
    private String source;
    private boolean isEnd;
    private int positionInString;

    public StringSource (String source) {
        currentRow = 1;
        currentColumn = 0;
        this.source = source + (char) 3;
        isEnd = false;
        positionInString = 0;
        consume();
    }
    @Override
    public void consume() {
        if (!isEnd) {
            getNextChar();
            updateCoordinates();
        }
    }
    @Override
    public boolean isOpened() { return !isEnd; }
    private void getNextChar() {
        if (positionInString < source.length()) {
            currentChar = source.charAt(positionInString);
            positionInString++;
        }
        else
            isEnd = true;
    }
}
