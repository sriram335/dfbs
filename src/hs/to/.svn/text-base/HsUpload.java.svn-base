package hs.to;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


public class HsUpload extends HsEntity {
    private int linkId;
    private String filename;
    private String notes;
    
    public HsUpload() {
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


    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public int getLinkId() {
        return linkId;
    }
}
