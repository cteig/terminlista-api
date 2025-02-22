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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Locale;
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
    public void getArrangementFraTilDato() {

        String fra = "2020-03-07";
        LocalDate localDateFra = LocalDate.parse(fra);

        Database database = new Database(dataSource);
        insertTestArrangement();

        List<Arrangement> arrangementerFraTilDato = database.getArrangementerFraTilDato(localDateFra);

        System.out.println("arrangementerFraTilDato = " + arrangementerFraTilDato);
    }

    @Test
    public void hentDatoFraKondis() throws ParseException {
        String datoStreng = " Fredag 17. april 2020";
        String[] splitStr = datoStreng.trim().split("\\s+",2);

        System.out.println("SPLIT = " + splitStr[1]);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .withLocale(new Locale("no", "NO"));

        TemporalAccessor temporalAccessor = dateTimeFormatter.parse(splitStr[1]);

        LocalDate from = LocalDate.from(temporalAccessor);

        System.out.println("from = " + from);
    }

    @Test
    public void getArrangementFraOverskrift() throws IOException {

        Database database = new Database(dataSource);

        insertTestArrangement();

        String overskrift = "Oslo Maraton";
        List<Arrangement> result = database.getArrangementsFraNavn(overskrift);

        Assert.assertEquals("SK Vidar", result.get(0).getArrangør());
        Assert.assertTrue(result.get(0).getDistanseList().size()==2);
        Assert.assertEquals("7,000", result.get(0).getDistanseList().get(0).getDistanse_lengde());
    }



    @Test
    public void getArrangementFraFylke() throws IOException {

        Database database = new Database(dataSource);
        insertTestArrangement();

        String fylke = "Oslo";
        List<Arrangement> result = database.getArrangementsIFylke(fylke);

        Assert.assertEquals("Oslo Maraton", result.get(0).getOverskrift());
        Assert.assertTrue(result.get(0).getDistanseList().size()==2);
    }

    @Test
    public void getAllArrangement() throws IOException {

        Database database = new Database(dataSource);
        insertTestArrangement();

        List<Arrangement> result = database.getAlleArrangementer();

        Assert.assertEquals("Oslo Maraton", result.get(0).getOverskrift());
        Assert.assertTrue(result.get(0).getDistanseList().size()==2);
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

            String distanseSql = "INSERT INTO Distanse(" +
                    "distanse_id, " +
                    "arrangement_id, " +
                    "distanse_lengde, " +
                    "distanse_starttid, " +
                    "distanse_startkontingent) " +
                    "values (" + ":distanse_idParam," +
                    "'" + uuid + "' , " +
                    ":distanse_lengdeParam," +
                    "'12:00'," +
                    "'100' );";
            con.createQuery(distanseSql)
                    .addParameter("distanse_idParam", UUID.randomUUID())
                    .addParameter("distanse_lengdeParam", "7,000")
                    .executeUpdate();
            con.createQuery(distanseSql)
                    .addParameter("distanse_idParam", UUID.randomUUID())
                    .addParameter("distanse_lengdeParam", "3,000")
                    .executeUpdate();

        }

    }

}
