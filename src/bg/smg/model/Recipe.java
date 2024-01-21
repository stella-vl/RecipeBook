package bg.smg.model;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class Recipe {
    private int id;
    private String name;
    private String cookingSteps;
    private String imageName;
    private ImageIcon image;
    private String difficulty;
    private String cookingTime;
    private String ingredients;
    private int isDeleted;
    //private ArrayList<Ingredient> ingredients;

    public Recipe() {
        this.id = 0;
        this.name = "";
        this.cookingSteps = "";
        this.imageName = "";
        this.difficulty = "";
        this.cookingTime = "";
        this.ingredients = "";
        isDeleted = 0;
        //this.ingredients = new ArrayList<>();
    }

    public Recipe(int id, String name, String cooking_steps, String image_name, String difficulty, String cooking_time, String ingredients) {
        this.id = id;
        this.name = name;
        this.cookingSteps = cooking_steps;
        this.imageName = image_name;
        this.difficulty = difficulty;
        this.cookingTime = cooking_time;
        this.ingredients = ingredients;
        isDeleted = 0;
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

    public String getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(String cooking_steps) {
        this.cookingSteps = cooking_steps;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        
        this.imageName = imageName;
    }
    
    public ImageIcon getIcon(ImageIcon imageI) {
    //promenq razmerite na kartinata taka, che da e podhodqshta za ImageIcon
    Image img = imageI.getImage();
    Image newImage = img.getScaledInstance(210, 144, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon1 = new ImageIcon(newImage);
    return icon1;
    }
    
    public ImageIcon getImage() {
        return getIcon(this.image);
    }

    public void setImage(ImageIcon image) {
        this.image = getIcon(image);
    }
    
    public ImageIcon getImageFromRes(Recipe recipe) {
        //String path = ("./Source Packages/resources/" + imageName);
        //String n = recipe.getImageName();
        String path = "resources/" + recipe.getImageName();
        System.out.println(path);
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource(path));
        return img;
       
        
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
    
    public int getIsDeleted(){
        return isDeleted;
    }
    
    public void setIsDeleted(int isDeleted){
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
