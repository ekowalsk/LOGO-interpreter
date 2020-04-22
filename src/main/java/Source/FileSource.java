package Source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileSource extends Source{
    private FileInputStream source;
    private boolean isOpened;

    public FileSource(String filePath){
        openFile(filePath);
        currentRow = 1;
        currentColumn = 1;
    }
    @Override
    public boolean consume(){
        if (isOpened) {
            readCharacter();
            incrementCurrentPosition();
            return true;
        }
        else
            return false;
    }

    private void readCharacter(){
        try {
            int character = source.read();
            if (!isEOF(character))
                currentChar = (char) character;
            else
                closeFile();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
    private void incrementCurrentPosition() {
        if (currentChar == '\n')
        {
            currentRow++;
            currentColumn = 0;
        }
        else
            currentColumn++;
    }
    private boolean isEOF(int fileCharacter){
        return fileCharacter == -1;
    }
    private void openFile(String filePath){
        File file = new File(filePath);
        try {
            source = new FileInputStream(file);
            isOpened = true;
        }
        catch (FileNotFoundException e){
            System.err.println("File " + filePath + " not found: " + e.getMessage());
            System.exit(-1);
        }
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
