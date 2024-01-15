package bg.smg.model;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Recipe {
    private String name;
    private String cookingSteps;
    private String imageName;
    private ImageIcon image;
    private String difficulty;
    private String cookingTime;
    private String ingredients;
    private boolean isDeleted;
    //private ArrayList<Ingredient> ingredients;

    public Recipe() {
        this.name = "";
        this.cookingSteps = "";
        this.imageName = "";
        this.difficulty = "";
        this.cookingTime = "";
        this.ingredients = "";
        isDeleted = false;
        //this.ingredients = new ArrayList<>();
    }

    public Recipe(String name, String cooking_steps, String image_name, String difficulty, String cooking_time, String ingredients) {
        this.name = name;
        this.cookingSteps = cooking_steps;
        this.imageName = image_name;
        this.difficulty = difficulty;
        this.cookingTime = cooking_time;
        this.ingredients = ingredients;
        isDeleted = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(String cooking_steps) {
        this.cookingSteps = cooking_steps;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String image_name) {
        this.imageName = image_name;
    }
    
    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cooking_time) {
        this.cookingTime = cooking_time;
    }
    
    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    
    public boolean getIsDeleted(){
        return isDeleted;
    }
    
    public void setIsDeleted(boolean isDeleted){
        this.isDeleted = isDeleted;
    }
/*
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
*/
    @Override
    public String toString() {
        return "Recipe{" + "name=" + name + ", cooking_steps=" + cookingSteps + ", image_name=" + imageName + ", difficulty=" + difficulty + ", cooking_time=" + cookingTime + ", ingredients=" + ingredients + '}';
    }
}
