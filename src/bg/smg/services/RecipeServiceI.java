
package bg.smg.services;

import bg.smg.model.Recipe;
import java.sql.SQLException;

/**
 *
 * @author Stela
 */
public interface RecipeServiceI {
    public void addRecipe(Recipe recipe) throws SQLException;
    public void editRecipe(Recipe recipe) throws SQLException;
    public void deleteRecipe(Recipe recipe) throws SQLException;
    public void showDetails(Recipe recipe) throws SQLException;
}
