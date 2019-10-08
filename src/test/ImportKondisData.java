import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;
import domain.Arrangement;
import domain.Arrangementer;
import domain.Distanse;
import domain.Kontaktperson;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import repository.HikariHelper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.UUID;

public class ImportKondisData {

    private static final Logger log = LoggerFactory.getLogger(ImportKondisData.class);

    private static Sql2o sql2o;



    @Test
    public void importData() {

        HikariDataSource hikariDataSource = createDataSource();
        sql2o = new Sql2o(hikariDataSource);
        Gson gson = new Gson();

        try (Reader reader = new FileReader("/Users/christineteig/repos/ctekk/spike/javalin-test/resources/details.json")) {

            // Convert JSON File to Java Object
            Arrangementer arrangementer = gson.fromJson(reader, Arrangementer.class);
            java.util.List<Arrangement> arrangements = arrangementer.getArrangements();

            String arrangementSql = "INSERT INTO ARRANGEMENT(" +
                    "arrangement_id, " +
                    "overskrift, " +
                    "arrangør," +
                    "arrangert," +
                    "adresse," +
                    "postnr," +
                    "poststed," +
                    "land," +
                    "fylke," +
                    "kommune," +
                    "dato," +
                    "start," +
                    "kart," +
                    "mål," +
                    "parkering," +
                    "garderobe," +
                    "etteranmeldingsgebyr," +
                    "kontonummer," +
                    "påmelding," +
                    "påmelding_postadresse," +
                    "påmelding_postnr," +
                    "påmelding_poststed," +
                    "kvinneløp," +
                    "motbakkeløp," +
                    "nfif_lisens," +
                    "inngår_i," +
                    "link," +
                    "underlag," +
                    "underlag_kommentar," +
                    "hjemmesider," +
                    "premier," +
                    "kommentar," +
                    "klasseinndeling," +
                    "beskrivelse" +
                    ") values (" +
                    ":arrangement_idParam, " +
                    ":overskriftParam, " +
                    ":arrangørParam," +
                    ":arrangertParam," +
                    ":adresseParam," +
                    ":postnrParam," +
                    ":poststedParam," +
                    ":landParam," +
                    ":fylkeParam," +
                    ":kommuneParam," +
                    ":datoParam," +
                    ":startParam," +
                    ":kartParam," +
                    ":målParam," +
                    ":parkeringParam," +
                    ":garderobeParam," +
                    ":etteranmeldingsgebyrParam," +
                    ":kontonummerParam," +
                    ":påmeldingParam," +
                    ":påmelding_postadresseParam," +
                    ":påmelding_postnrParam," +
                    ":påmelding_poststedParam," +
                    ":kvinneløpParam," +
                    ":motbakkeløpParam," +
                    ":nfif_liseøænsParam," +
                    ":inngår_iParam," +
                    ":linkParam," +
                    ":underlagParam," +
                    ":underlag_kommentarParam," +
                    ":hjemmesiderParam," +
                    ":premierParam," +
                    ":kommentarParam," +
                    ":klasseinndelingParam," +
                    ":beskrivelseParam" +
                    " )";

            String kontaktpersonSql = "INSERT INTO KONTAKTPERSON(" +
                    "kontaktperson_id, " +
                    "arrangement_id, " +
                    "navn, " +
                    "telefon, " +
                    "mobil, " +
                    "epost" +
                    ") values (" +
                    ":kontaktperson_idParam, " +
                    ":arrangement_idParam, " +
                    ":navnParam, " +
                    ":telefonParam, " +
                    ":mobilParam, " +
                    ":epostParam" +
                    " )";

            String distanseSql = "INSERT INTO DISTANSE(" +
                    "distanse_id, " +
                    "arrangement_id, " +
                    "distanse_lengde, " +
                    "distanse_starttid, " +
                    "distanse_startkontingent, " +
                    "distanse_fullforte_aaret_for, " +
                    "distanse_barnelop, " +
                    "distanse_stafett, " +
                    "distanse_etapper, " +
                    "distanse_kontrollmålt " +
                    ") values (" +
                    ":distanse_idParam, " +
                    ":arrangement_idParam, " +
                    ":distanse_lengdeParam, " +
                    ":distanse_starttidParam, " +
                    ":distanse_startkontingentParam, " +
                    ":distanse_fullforte_aaret_forParam, " +
                    ":distanse_barnelopParam, " +
                    ":distanse_stafettParam, " +
                    ":distanse_etapperParam, " +
                    ":distanse_kontrollmåltParam " +
                    " )";

            try (Connection con = sql2o.open()) {

                arrangements.forEach((arr) -> {
                    UUID arrId =  UUID.randomUUID();
                    con.createQuery(arrangementSql)
                            .addParameter("arrangement_idParam", arrId)
                            .addParameter("overskriftParam", arr.getOverskrift())
                            .addParameter("arrangørParam", arr.getArrangør())
                            .addParameter("arrangertParam", arr.getArrangert())
                            .addParameter("adresseParam", arr.getAdresse())
                            .addParameter("postnrParam", arr.getPostnr())
                            .addParameter("poststedParam", arr.getPoststed())
                            .addParameter("landParam", arr.getLand())
                            .addParameter("fylkeParam", arr.getFylke())
                            .addParameter("kommuneParam", arr.getKommune())
                            .addParameter("datoParam", arr.getDato())
                            .addParameter("startParam", arr.getStart())
                            .addParameter("kartParam", arr.getKart())
                            .addParameter("målParam", arr.getMål())
                            .addParameter("parkeringParam", arr.getParkering())
                            .addParameter("garderobeParam", arr.getGarderobe())
                            .addParameter("etteranmeldingsgebyrParam", arr.getEtteranmeldingsgebyr())
                            .addParameter("kontonummerParam", arr.getKontonummer())
                            .addParameter("påmeldingParam", arr.getPåmelding())
                            .addParameter("påmelding_postadresseParam", arr.getPåmelding_postadresse())
                            .addParameter("påmelding_postnrParam", arr.getPåmelding_postnr())
                            .addParameter("påmelding_poststedParam", arr.getPåmelding_poststed())
                            .addParameter("kvinneløpParam", arr.getKvinneløp())
                            .addParameter("motbakkeløpParam", arr.getMotbakkeløp())
                            .addParameter("nfif_liseøænsParam", arr.getNfif_lisens())
                            .addParameter("inngår_iParam", arr.getInngår_i())
                            .addParameter("linkParam", arr.getLink())
                            .addParameter("underlagParam", arr.getUnderlag())
                            .addParameter("underlag_kommentarParam", arr.getUnderlag_kommentar())
                            .addParameter("hjemmesiderParam", arr.getHjemmesider())
                            .addParameter("premierParam", arr.getPremier())
                            .addParameter("kommentarParam", arr.getKommentar())
                            .addParameter("klasseinndelingParam", arr.getKlasseinndeling())
                            .addParameter("beskrivelseParam", arr.getBeskrivelse())
                            .executeUpdate();

                    java.util.List<Kontaktperson> kontaktpersonList = arr.getKontaktpersonList();
                    java.util.List<Distanse> distanseList = arr.getDistanseList();

                    kontaktpersonList.forEach((person) -> {

                            con.createQuery(kontaktpersonSql)
                                .addParameter("kontaktperson_idParam",  UUID.randomUUID())
                                .addParameter("arrangement_idParam", arrId)
                                .addParameter("navnParam", person.getKontaktperson_navn())
                                .addParameter("telefonParam", person.getKontaktperson_mobil())
                                .addParameter("mobilParam", person.getKontaktperson_mobil())
                                .addParameter("epostParam", person.getKontaktperson_epost())
                                    .executeUpdate();

                        });
                    distanseList.forEach((distanse -> {
                        con.createQuery(distanseSql)
                                .addParameter("distanse_idParam",  UUID.randomUUID())
                                .addParameter("arrangement_idParam", arrId)
                                .addParameter("distanse_lengdeParam", distanse.getDistanse_lengde())
                                .addParameter("distanse_starttidParam", distanse.getDistanse_starttid())
                                .addParameter("distanse_startkontingentParam" , distanse.getDistanse_startkontingent())
                                .addParameter("distanse_fullforte_aaret_forParam", distanse.getDistanse_fullførte_året_før())
                                .addParameter("distanse_barnelopParam", distanse.getDistanse_barneløp())
                                .addParameter("distanse_stafettParam", distanse.getDistanse_stafett())
                                .addParameter("distanse_etapperParam", distanse.getDistanse_etapper())
                                .addParameter("distanse_kontrollmåltParam", distanse.getDistanse_kontrollmålt())
                                .executeUpdate();
                    }));
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
