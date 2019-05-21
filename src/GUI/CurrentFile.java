package GUI;

import java.io.File;

class CurrentFile{
    private boolean isFileDirty;
    private boolean isNewFile;
    private File file;

    /**
     * Constructs a new CurrentFile object, to store the state of the file in the VECPanel
     * @param file The File object that's state is being stored
     * @param isNewFile Whether the file is a new file or an imported file
     * @param isFileDirty Whether the file has been changed since it has last been saved
     */
    CurrentFile(File file, boolean isNewFile, boolean isFileDirty){
        this.file = file;
        this.isNewFile = isNewFile;
        this.isFileDirty = isFileDirty;
    }

    /**
     * The File object of the CurrentFile
     * @return A File Object of the CurrentFile
     */
    File getFileObj(){
        return file;
    }

    /**
     * Returns whether the file has been changed since its last saved
     * @return True if changed, false if not
     */
    boolean isFileDirty() {
        return isFileDirty;
    }

    /**
     * Sets whether the file is dirty or not
     * @param fileDirty Boolean whether the file is dirty or not
     */
    void setFileDirty(boolean fileDirty) {
        this.isFileDirty = fileDirty;
    }

    /**
     * Returns whether the file was imported or is a new file
     * @return True if new file, false if imported
     */
    boolean isNewFile() {
        return isNewFile;
    }

}
