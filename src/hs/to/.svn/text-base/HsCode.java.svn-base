package hs.to;

import java.io.Serializable;


public class HsCode extends HsTransferObject implements Serializable {
    private String code;
    private String label;
    private String description;
    private String codeType;
    private boolean active;

    public HsCode() {
    }


    public boolean isActive() {
        return active;
    }

    public String getActive() {
        return (active) ? "A" : "I";
    }

    public void setActive(boolean newActive) {
        active = newActive;
    }

    public void setActive(String newActive) {
        active = (newActive.equals("A")) ? true : false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return label;
    }

    public void setName(String label) {
        this.label = label;
    }

}
