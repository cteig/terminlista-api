package repository;

import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.embedded.PreparedDbProvider;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDbUtils
{


  // remember to call resetAndClose after each test
  public static DataSource createDataSource() {

      if (System.getenv("POSTGRES_DB") != null) {
          HikariDataSource dataSource = new HikariDataSource();
          dataSource.setJdbcUrl("jdbc:postgresql://postgres/" + ParameterHelper.getRequiredParameter("POSTGRES_DB"));
          dataSource.setUsername("postgres");
          dataSource.setPassword("postgres");
          FluentConfiguration configuration = new FluentConfiguration();
          configuration.locations("db/migration");
          configuration.dataSource(dataSource);
          configuration.schemas("public");
          Flyway flyway = new Flyway(configuration);
          flyway.migrate();
          return dataSource;
      } else {
          try {
              return PreparedDbProvider.forPreparer(FlywayPreparer.forClasspathLocation("db/migration")).createDataSource();
          } catch (SQLException var2) {
              throw new RuntimeException(var2);
          }
      }
  }




  public static void resetAndClose(DataSource dataSource) {

    try (Connection connection = dataSource.getConnection()) {
      connection.setAutoCommit(false);
      connection.createStatement().execute("DROP TABLE IF EXISTS distanse");
      connection.createStatement().execute("DROP TABLE IF EXISTS kontaktperson");
      connection.createStatement().execute("DROP TABLE IF EXISTS arrangement");
      connection.createStatement().execute("DELETE FROM flyway_schema_history");

      connection.commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    // HikariDataSource is a pool and close on connection does not close connection to db
    if (dataSource instanceof HikariDataSource) {
      ((HikariDataSource)dataSource).close();
    }

  }

}
