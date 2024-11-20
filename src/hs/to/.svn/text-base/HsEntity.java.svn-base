package hs.to;

import java.io.Serializable;

import java.util.Map;


public class HsEntity extends HsTransferObject implements Serializable {
    private int id;
    private int parentId;
    private int rootId;
    private int level;
    private String type;
    private String status;

    private String tagString;
    private Map tagMap;

    public HsEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTagString() {
        return tagString;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }

    public void addTagString(String tagString) {
        if (tagString == null || tagString.equals(""))
            return;

        StringBuffer sb = new StringBuffer();
        if (this.tagString != null) {
            sb.append(this.tagString).append(" ");
        }
        sb.append(tagString.replace(' ', '_'));
        this.tagString = sb.toString();
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return (status != null && status.equals("A") ? true : false);
    }

    public String getActive() {
        return (status != null && status.equals("A")) ? "A" : "I";
    }

    public void setActive(boolean newActive) {
        if (newActive) {
            status = "A";
        } else {
            status = "I";
        }
    }

    public void setActive(String newActive) {
        status = (newActive != null && newActive.equals("A")) ? "A" : "I";
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map getTagMap() {
        return tagMap;
    }

    public void setTagMap(Map tagMap) {
        this.tagMap = tagMap;
    }

}
