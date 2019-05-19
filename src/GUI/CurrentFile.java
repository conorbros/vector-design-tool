package GUI;

import java.io.File;

public class CurrentFile{
    private boolean isFileDirty;
    private boolean isNewFile;
    private File file;

    public CurrentFile(File file, boolean isNewFile, boolean isFileDirty){
        this.file = file;
        this.isNewFile = isNewFile;
        this.isFileDirty = isFileDirty;
    }

    public File getFileObj(){
        return file;
    }

    public boolean isFileDirty() {
        return isFileDirty;
    }

    public void setFileDirty(boolean fileDirty) {
        this.isFileDirty = fileDirty;
    }

    public boolean isNewFile() {
        return isNewFile;
    }

    public void setNewFile(boolean newFile) {
        this.isNewFile = newFile;
    }
}
