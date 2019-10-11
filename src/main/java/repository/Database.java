package repository;

import domain.Arrangement;
import domain.Distanse;
import domain.Kontaktperson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class Database {

    final private DataSource dataSource;


    private static int CORALOGIX_COMPANY = 6804;
    private static String CORALOGIX_SUBSYSTEM_NAME = "backend";

    private static final Logger log = LoggerFactory.getLogger(Database.class);


    public List<Arrangement> getArrangementsFraNavn(String overskrift) {
        Sql2o sql2o = new Sql2o(dataSource);
        List<Arrangement> result;

        String selectArrangementSQL = "SELECT * FROM arrangement " +
                "WHERE overskrift = :overskrift";

        String selectDistanseSQL = "SELECT * FROM distanse " +
                "WHERE arrangement_id = :arrangement_id";

        String selectKontaktSQL = "SELECT * FROM kontaktperson " +
                "WHERE arrangement_id = :arrangement_id";

        try (Connection con = sql2o.open()) {

            result = con.createQuery(selectArrangementSQL)
                    .addParameter("overskrift", overskrift)
                    .executeAndFetch(Arrangement.class);

            result.forEach((arrangement) -> {
                System.out.println("arrangement = " + arrangement);
                List<Distanse> distanseList = con.createQuery(selectDistanseSQL)
                        .addParameter("arrangement_id", arrangement.getArrangementId())
                        .executeAndFetch(Distanse.class);

                List<Kontaktperson> kontaktpersonList = con.createQuery(selectKontaktSQL)
                        .addParameter("arrangement_id", arrangement.getArrangementId())
                        .executeAndFetch(Kontaktperson.class);

                arrangement.setDistanseList(distanseList);
                arrangement.setKontaktpersonList(kontaktpersonList);

            });
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

    public List<Arrangement> getArrangementerFraTilDato(LocalDate fra) {

        System.out.println("sinceInstant = " + fra);
        Sql2o sql2o = new Sql2o(dataSource);
        String selectSQL = "SELECT * FROM arrangement WHERE dato >= :fra";
        List<Arrangement> result;


        try (Connection con = sql2o.open()) {

            result = con.createQuery(selectSQL)
                    .addParameter("fra", fra)
                    .executeAndFetch(Arrangement.class);
        }
        return result;
    }
}
