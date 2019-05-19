package Tests;

import GUI.CurrentFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentFileTests {
    private CurrentFile currentFile;
    private File file = new File("../test.txt");

    @BeforeEach
    void beforeEach(){
        currentFile = new CurrentFile(file, true, true);
    }

    @Test
    void testGetFileObj(){
        File result = currentFile.getFileObj();
        assertEquals(file, result);
    }

    @Test
    void isFileDirty(){
        assertTrue(currentFile.isFileDirty());
        currentFile.setFileDirty(false);
        assertFalse(currentFile.isFileDirty());
    }

    @Test
    void isNewFile(){
        assertTrue(currentFile.isNewFile());
        currentFile.setNewFile(false);
        assertFalse(currentFile.isNewFile());
    }

}
