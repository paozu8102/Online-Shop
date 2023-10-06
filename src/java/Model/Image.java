/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nhat Anh
 */
public class Image {
    private int ImageID;
    private int TypeID;
    private int ObjectID;
    private String ImageUrl;

    public Image() {
    }

    public Image(int ImageID, int TypeID, int ObjectID, String ImageUrl) {
        this.ImageID = ImageID;
        this.TypeID = TypeID;
        this.ObjectID = ObjectID;
        this.ImageUrl = ImageUrl;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int ImageID) {
        this.ImageID = ImageID;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public int getObjectID() {
        return ObjectID;
    }

    public void setObjectID(int ObjectID) {
        this.ObjectID = ObjectID;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }
    
    
}
