package hazmat.to;

import hs.to.HsCompleteContact;

import java.sql.Blob;
import java.io.Serializable;
import java.sql.Date;

public class HazmatImage extends HsCompleteContact implements Serializable {
    private int imageId;
    private String name;
    private String imageType;
    private Blob image;

    public HazmatImage() {
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
    public String toString() {
        return "HazmatImage{" +
                "imageId=" + imageId +
                ", name='" + name + '\'' +
                ", imageType='" + imageType + '\'' +
                '}';
    }
}
