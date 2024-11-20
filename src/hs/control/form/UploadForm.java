package hs.control.form;



public class UploadForm extends StrutsUploadForm {
    
    private String notes;
    private String type;
    private String linkId;


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
}
