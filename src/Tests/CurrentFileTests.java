package Tests;

import GUI.CurrentFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.spi.CurrencyNameProvider;

import static org.junit.jupiter.api.Assertions.*;

class CurrentFileTests {
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
        CurrentFile newFile = new CurrentFile(file, false, false);
        assertTrue(newFile.isNewFile());
        assertFalse(currentFile.isNewFile());
    }
}
