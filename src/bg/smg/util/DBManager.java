package bg.smg.util;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DBManager {

    private static DBManager instance;
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    DBManager() throws SQLException {
        // First try with a DataSource without pooling:
        MariaDbDataSource dataSource = new MariaDbDataSource();
        /*
         * That should fail (SQLException: too many connections)
         * Try now commenting the previous executable line
         * and using the following DataSource that supports pooling:
         * MariaDbPoolDataSource dataSource = new MariaDbPoolDataSource();
         * That should work!
         */
        dataSource.setUrl("jdbc:mariadb://localhost:3306/recipebook");
        dataSource.setUser("root");
        dataSource.setPassword(null);
        this.dataSource = dataSource;
    }

    public static synchronized DBManager getInstance() throws SQLException {
        if(instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

}
