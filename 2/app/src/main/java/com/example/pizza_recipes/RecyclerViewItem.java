package com.example.pizza_recipes;

public class RecyclerViewItem {

    private int imageResource;
    private String text1;
    private String text2;
    private String recipe;

    public String getRecipe() {
        return recipe;
    }

    public RecyclerViewItem (int imageResource, String text1, String text2, String recipe) {
        this.imageResource = imageResource;
        this.text1 = text1;
        this.text2 = text2;
        this.recipe = recipe;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText1(){
        return text1;
    }

    public String getText2() {
        return text2;
    }
}
