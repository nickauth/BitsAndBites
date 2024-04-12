package org.bnb.Model;

public class RecipeImage {

    private String imgAdress;

    public RecipeImage() {
    }

    public RecipeImage(String imgAdress, String altText) {
        this.imgAdress = imgAdress;
    }

    public String getImgAdress() {
        return imgAdress;
    }

    public void setImgAdress(String imgAdress) {
        this.imgAdress = imgAdress;
    }
}
