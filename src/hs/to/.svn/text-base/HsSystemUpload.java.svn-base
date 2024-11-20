package hs.to;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


public class HsSystemUpload extends HsEntity {
    private String folder;
    private String filename;
    private String notes;
    private String parentTable;
    
    public HsSystemUpload() {
    }


    public String getNotesHtml() {
        if (notes == null)
            return "";

        return notes.replaceAll("\n", "<br />");
    }

   
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }


    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setParentTable(String parentTable) {
        this.parentTable = parentTable;
    }

    public String getParentTable() {
        return parentTable;
    }
}
