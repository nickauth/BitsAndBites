package org.bnb.Database.Classes;

public class RecipeDB {
    private int rid;
    private String rName;
    private String description;
    private int rLvl;
    private int durationLvlID;
    private String userComment;
    private int mealID;
    private int isVegan;
    private int isVegetarian;

    public RecipeDB() {
    }

    public RecipeDB(int rid, String rName, String description, int rLvl, int durationLvlID, String userComment, int mealID, int isVegan, int isVegetarian) {
        this.rid = rid;
        this.rName = rName;
        this.description = description;
        this.rLvl = rLvl;
        this.durationLvlID = durationLvlID;
        this.userComment = userComment;
        this.mealID = mealID;
        this.isVegan = isVegan;
        this.isVegetarian = isVegetarian;
    }

    //Getter and Setter methods
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getrLvl() {
        return rLvl;
    }

    public void setrLvl(int rLvl) {
        this.rLvl = rLvl;
    }

    public int getDurationLvlID() {
        return durationLvlID;
    }

    public void setDurationLvlID(int durationLvlID) {
        this.durationLvlID = durationLvlID;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public int getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(int isVegan) {
        this.isVegan = isVegan;
    }

    public int getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(int isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    public void setId(int rid) {
    }

    @Override
    public String toString() {
        return "ID: " + rid + "\n" +
                "Name: " + rName + "\n" +
                "Beschreibung: " + description + "\n" +
                "Schwierigkeitsgrad: " + rLvl + "\n" +
                "Dauer: " + durationLvlID + "\n" +
                "Kommentar: " + userComment + "\n" +
                "Mahlzeit-ID: " + mealID + "\n" +
                "Ist vegan: " + isVegan + "\n" +
                "Ist vegetarisch: " + isVegetarian + "\n";
    }

}
