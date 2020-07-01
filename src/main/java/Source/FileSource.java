package Source;

import java.io.*;

public class FileSource extends Source {
    private InputStreamReader source;
    private boolean isOpened;

    public FileSource(String filePath) {
        openFile(filePath);
        readCharacter();
        setInitialCoordinates();
    }

    @Override
    public void consume() {
        if (isOpened) {
            readCharacter();
            updateCoordinates();
        }
    }
    public boolean isOpened() { return isOpened; }

    private void readCharacter() {
        try {
            int character = source.read();
            if (!isEOF(character))
                currentChar = (char) character;
            else {
                currentChar = ETX;
                closeFile();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
    private boolean isEOF(int fileCharacter) {
        return fileCharacter == -1;
    }
    private void openFile(String filePath){
        File file = new File(filePath);
        try {
            source = new InputStreamReader(new FileInputStream(file), "UTF-8");
            isOpened = true;
        } catch (FileNotFoundException e) {
            System.err.println("File " + filePath + " not found: " + e.getMessage());
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding: " + e.getMessage());
            System.exit(-1);
        }
    }
    private void setInitialCoordinates() {
        currentRow = 1;
        currentColumn = 1;
    }
    private void closeFile() {
        try {
            source.close();
            isOpened = false;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
