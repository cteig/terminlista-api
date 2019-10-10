package repository;

import domain.Arrangement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class Database {

    final private DataSource dataSource;


    private static int CORALOGIX_COMPANY = 6804;
    private static String CORALOGIX_SUBSYSTEM_NAME = "backend";

    private static final Logger log = LoggerFactory.getLogger(Database.class);


    public List<Arrangement> getArrangementsFraNavn(String overskrift) {
        Sql2o sql2o = new Sql2o(dataSource);
        String selectSQL = "SELECT * FROM arrangement WHERE overskrift = :overskrift";
        List<Arrangement> result;

        try (Connection con = sql2o.open()) {

            result = con.createQuery(selectSQL)
                    .addParameter("overskrift", overskrift)
                    .executeAndFetch(Arrangement.class);
        }
        return result;
    }

    public List<Arrangement> getArrangementsIFylke(String fylke) {
        Sql2o sql2o = new Sql2o(dataSource);
        String selectSQL = "SELECT * FROM arrangement WHERE fylke = :fylke";
        List<Arrangement> result;

        try (Connection con = sql2o.open()) {

            result = con.createQuery(selectSQL)
                    .addParameter("fylke", fylke)
                    .executeAndFetch(Arrangement.class);
        }
        return result;
    }


    public List<Arrangement> getAlleArrangementer() {
        Sql2o sql2o = new Sql2o(dataSource);
        String selectSQL = "SELECT * FROM arrangement";
        List<Arrangement> result;

        try (Connection con = sql2o.open()) {

            result = con.createQuery(selectSQL)
                    .executeAndFetch(Arrangement.class);
        }
        return result;
    }

    public List<Arrangement> getArrangementerFraTilDato(Instant sinceInstant) {

        System.out.println("sinceInstant = " + sinceInstant);
        Sql2o sql2o = new Sql2o(dataSource);
        String selectSQL = "SELECT * FROM arrangement WHERE dato >= :sinceInstant";
        List<Arrangement> result;
        Date myDate = Date.from(sinceInstant);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
        String formattedDate = formatter.format(myDate);
        System.out.println("formattedDate = " + formattedDate);

        try (Connection con = sql2o.open()) {

            result = con.createQuery(selectSQL)
                    .addParameter("sinceInstant", sinceInstant.toString())
                    .executeAndFetch(Arrangement.class);
        }
        return result;
    }
}
