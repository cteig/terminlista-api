package repository;

import domain.Arrangement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Database {

    private static int CORALOGIX_COMPANY = 6804;
    private static String CORALOGIX_SUBSYSTEM_NAME = "backend";

    private static final Logger log = LoggerFactory.getLogger(Database.class);


    protected List<Arrangement> getArrangementsFraNavn(Sql2o sql2o, String oversrkift) {
        String selectSQL = "Select * from ARRANGEMENT where overskrift = :overskrift";
        List<Arrangement>  result;

        try (Connection con = sql2o.open()) {

            result = con.createQuery(selectSQL)
                    .addParameter("overskrift", oversrkift)
                    .executeAndFetch(Arrangement.class);
        }
        return result;
    }





}
