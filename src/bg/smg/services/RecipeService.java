
package bg.smg.services;

import bg.smg.frames.DetailsForm;
import bg.smg.frames.MainMenu;
import bg.smg.model.Recipe;
import bg.smg.util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.ImageIcon;


public class RecipeService implements RecipeServiceI {
    
    private DataSource dataSource;
    private Connection connection;
    
    public RecipeService(){
        try {
            dataSource = DBManager.getInstance().getDataSource();
        } catch (SQLException ex) {
            Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    @Override
    public void addRecipe(Recipe recipe) throws SQLException{
        try {
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    //"INSERT INTO `recipes`(`recipeName`, `cookingSteps`, `imageName`, `difficulty`, `cookingTime`, `ingredients`) VALUES ('a','a','a','a','a','a')"
                    "INSERT INTO `recipes`(`recipeName`, `cookingSteps`, `imageName`, `difficulty`, `cookingTime`, `ingredients`) VALUES ('"+recipe.getName()+"', '"+recipe.getCookingSteps()+"', '"+recipe.getImageName()+"', '"+recipe.getDifficulty()+"', '"+recipe.getCookingTime()+"', '"+recipe.getIngredients()+"')")) {
                statement.executeQuery();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Connection valid: " );
            }
        }
    }
    
    public ArrayList<Recipe> getRecipes(){
        ArrayList<Recipe> recipes = new ArrayList<>();
        ResultSet p;
        try {
            this.connection = dataSource.getConnection();
            Statement s = connection.createStatement();
            p = s.executeQuery("SELECT * FROM `recipes`");
            
            Recipe rec;
            while (p.next()) {
                rec = new Recipe();
                rec.setId(p.getInt("id"));
                rec.setName(p.getString("recipeName"));
                rec.setCookingSteps(p.getString("cookingSteps"));
                String path = "./resources/" + p.getString("imageName");
                rec.setImage(new ImageIcon(path));
                rec.setDifficulty(p.getString("difficulty"));
                rec.setCookingTime(p.getString("cookingTime"));
                rec.setIngredients(p.getString("ingredients"));

                recipes.add(rec);
            }
            try {
                s.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return recipes;
        } catch (java.sql.SQLException ex) {
            
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } finally{

            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return recipes;
    }
    
    @Override
    public void editRecipe(Recipe recipe) throws SQLException{
        try {
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    //"INSERT INTO `recipes`(`recipeName`, `cookingSteps`, `imageName`, `difficulty`, `cookingTime`, `ingredients`) VALUES ('a','a','a','a','a','a')"
                    "UPDATE `recipes` SET `cookingSteps`='"+recipe.getCookingSteps()+"',`imageName`='"+recipe.getImageName()+"',`difficulty`='"+recipe.getDifficulty()+"',`cookingTime`='"+recipe.getCookingTime()+"',`ingredients`=''"+recipe.getIngredients()+"'' WHERE `recipeName`='"+recipe.getName()+"'")) {
                statement.executeQuery();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Connection valid: " );
            }
        }
        
    }
    
    @Override
    public void deleteRecipe(Recipe recipe) throws SQLException{
        recipe.setIsDeleted(true);
    }
    
    @Override
    public void showDetails(Recipe recipe) throws SQLException{
        DetailsForm df = new DetailsForm(recipe);
        df.setVisible(true);
    }
}
