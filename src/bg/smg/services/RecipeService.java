
package bg.smg.services;

import bg.smg.model.Recipe;
import bg.smg.util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;


public class RecipeService implements RecipeServiceI {
    
    private DataSource dataSource;
    private Connection connection;
    
    public RecipeService() throws SQLException {
        dataSource = DBManager.getInstance().getDataSource();
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
}
