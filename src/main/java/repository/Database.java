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


        try (Connection con = sql2o.open()) {

            result = con.createQuery(selectArrangementSQL)
                    .addParameter("overskrift", overskrift)
                    .executeAndFetch(Arrangement.class);

            result.forEach((arrangement) -> {

                arrangement.setDistanseList(getDistanseForArrangement(con, arrangement));
                arrangement.setKontaktpersonList(getKontaktpersonForArrangement(con, arrangement));

            });
        }
        return result;
    }

    public List<Arrangement> getArrangementsIFylke(String fylke) {
        Sql2o sql2o = new Sql2o(dataSource);
        String selectSQL = "SELECT * FROM arrangement" +
                " WHERE fylke = :fylke";

        List<Arrangement> result;

        try (Connection con = sql2o.open()) {

            result = con.createQuery(selectSQL)
                    .addParameter("fylke", fylke)
                    .executeAndFetch(Arrangement.class);

            result.forEach((arrangement) -> {

                arrangement.setDistanseList(getDistanseForArrangement(con, arrangement));
                arrangement.setKontaktpersonList(getKontaktpersonForArrangement(con, arrangement));

            });
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

            result.forEach((arrangement) -> {

                arrangement.setDistanseList(getDistanseForArrangement(con, arrangement));
                arrangement.setKontaktpersonList(getKontaktpersonForArrangement(con, arrangement));

            });
        }
        return result;
    }

    public List<Arrangement> getArrangementerFraTilDato(LocalDate fra) {

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

    private List<Distanse> getDistanseForArrangement(Connection con, Arrangement arrangement) {

        String selectDistanseSQL = "SELECT * FROM distanse " +
                "WHERE arrangement_id = :arrangement_id";

        return con.createQuery(selectDistanseSQL)
                .addParameter("arrangement_id", arrangement.getArrangementId())
                .executeAndFetch(Distanse.class);
    }

    private List<Kontaktperson> getKontaktpersonForArrangement(Connection con, Arrangement arrangement) {

        String selectKontaktSQL = "SELECT * FROM kontaktperson " +
                "WHERE arrangement_id = :arrangement_id";

        return con.createQuery(selectKontaktSQL)
                .addParameter("arrangement_id", arrangement.getArrangementId())
                .executeAndFetch(Kontaktperson.class);
    }


}
