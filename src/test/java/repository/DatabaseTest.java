package repository;

import domain.Arrangement;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class DatabaseTest {

    private static final Logger log = LoggerFactory.getLogger(DatabaseTest.class);

    //private static Sql2o sql2o;

    private DataSource dataSource;




    @Before
    public void before() {
        dataSource = TestDbUtils.createDataSource();
    }

    @After
    public void after() {
        TestDbUtils.resetAndClose(dataSource);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void getArrangementFraOverskrift() throws IOException {

        Database database = new Database(dataSource);

        insertTestArrangement();

        String overskrift = "Oslo Maraton";
        List<Arrangement> result = database.getArrangementsFraNavn(overskrift);

        Assert.assertEquals("SK Vidar", result.get(0).getArrangør());
    }



    @Test
    public void getArrangementFraFylke() throws IOException {

        Database database = new Database(dataSource);
        insertTestArrangement();

        String fylke = "Oslo";
        List<Arrangement> result = database.getArrangementsIFylke(fylke);

        Assert.assertEquals("Oslo Maraton", result.get(0).getOverskrift());
    }

    private void insertTestArrangement() {
        Sql2o sql2o = new Sql2o(dataSource);
        try (Connection con = sql2o.open()) {
            UUID uuid = UUID.randomUUID();
            String sql = "INSERT INTO ARRANGEMENT(" +
                    "arrangement_id, " +
                    "overskrift, " +
                    "arrangør," +
                    "fylke) " +
                    "values ('" + uuid + "' , " +
                    "'Oslo Maraton' , " +
                    "'SK Vidar'," +
                    "'Oslo' );";
            con.createQuery(sql)
                    .executeUpdate();

        }

    }

}
