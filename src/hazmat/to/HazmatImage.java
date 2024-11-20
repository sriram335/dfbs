package hazmat.to;

import hs.to.HsCompleteContact;

import java.sql.Blob;
import java.io.Serializable;

public class HazmatImage extends HsCompleteContact implements Serializable {
    private int id;
    private String name;
    private Blob image;

    public HazmatImage() {}

    public HazmatImage(int id, String name, Blob image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
