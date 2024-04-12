package org.bnb.Model;

import java.util.List;

public class Recipe {

    private  int id;
    private String name;
    private String description;
    private String lvl;
    private String duration;
    private String userComment;
    private String meal;
    private boolean isVegan;
    private boolean isVegetarian;
    private List<Ingredient> ingredients;
    private List<RecipeImage> imageList;
    private List<Folder> folderList;

    public Recipe() {
    }

    public Recipe(String name, String description, String lvl, String duration, String userComment, String meal, boolean isVegan, boolean isVegetarian, List<Ingredient> ingredients, List<RecipeImage> imageList, List<Folder> folderList) {
        this.name = name;
        this.description = description;
        this.lvl = lvl;
        this.duration = duration;
        this.userComment = userComment;
        this.meal = meal;
        this.isVegan = isVegan;
        this.isVegetarian = isVegetarian;
        this.ingredients = ingredients;
        this.imageList = imageList;
        this.folderList = folderList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<RecipeImage> imageList) {
        this.imageList = imageList;
    }

    public List<Folder> getFolderList() {
        return folderList;
    }

    public void setFolderList(List<Folder> folderList) {
        this.folderList = folderList;
    }
}
