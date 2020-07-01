package Source;

public class StringSource extends Source {
    private final String source;
    private int positionInString;

    public StringSource (String source) {
        currentRow = 1;
        currentColumn = 0;
        this.source = source + ETX;
        positionInString = 0;
        consume();
    }
    @Override
    public void consume() {
        if (currentChar != ETX) {
            getNextChar();
            updateCoordinates();
        }
    }

    private void getNextChar() {
        if (positionInString < source.length()) {
            currentChar = source.charAt(positionInString);
            positionInString++;
        }
    }
}
