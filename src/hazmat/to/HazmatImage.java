package hazmat.to;

import hs.to.HsCompleteContact;

import java.sql.Blob;
import java.io.Serializable;
import java.sql.Date;

public class HazmatImage extends HsCompleteContact implements Serializable {
    private int image_Id;
    private String name;
    private String imageType;
    private Blob image;
    private Date effectiveDate;
    private String createdBy;
    private Date createdDate;

    public HazmatImage(int image_Id, Date createdDate, String createdBy, Date effectiveDate, Blob image, String imageType, String name) {
        this.image_Id = image_Id;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.effectiveDate = effectiveDate;
        this.image = image;
        this.imageType = imageType;
        this.name = name;
    }



    public HazmatImage() {}

    public int getImage_Id() {
        return image_Id;
    }

    public void setImage_Id(int image_Id) {
        this.image_Id = image_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }




}
