package repository;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;
import domain.Arrangement;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.eclipse.jetty.util.Loader.getResource;

public class DatabaseTest {

    private static final Logger log = LoggerFactory.getLogger(DatabaseTest.class);

    private static Sql2o sql2o;

    @Test
    public void getArrangementFraId() throws IOException {

        HikariDataSource hikariDataSource = createDataSource();
        sql2o = new Sql2o(hikariDataSource);
        Gson gson = new Gson();
        Database database = new Database();

        URL resource = getResource("arrangement.json");
        String arrangementFromFile = Resources.toString(resource, Charsets.UTF_8);
        String overskrift = "Grisebakken Opp Lørdag 21. september 2019 ";

        List<Arrangement> result = database.getArrangementsFraNavn(sql2o,overskrift);

        Assert.assertEquals(gson.toJson(result.get(0)), arrangementFromFile);
    }



    @NotNull
    private static HikariDataSource createDataSource() {
        return HikariHelper.createDataSource(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres"
        );
    }
}
