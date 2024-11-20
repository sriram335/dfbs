package hs.control.form;



public class SystemUploadForm extends StrutsUploadForm {
    
    private String notes;
    private String type;
    private String linkId;
    private String folder;
    private String parentTable;

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkId() {
        return linkId;
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
