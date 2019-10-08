import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;
import domain.Arrangement;
import io.javalin.Javalin;
import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;
import repository.Database;
import repository.HikariHelper;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class Api {
    private static int CORALOGIX_COMPANY = 6804;
    private static String CORALOGIX_SUBSYSTEM_NAME = "backend";

    private static final Logger log = LoggerFactory.getLogger(Api.class);

    private static Sql2o sql2o;

    public static void main(String[] args) {
        initLogging();
        log.info("Starting");
        HikariDataSource hikariDataSource = createDataSource();

        sql2o = new Sql2o(hikariDataSource);
        Javalin app = Javalin.create()
                .enableStaticFiles("/static")
                .start(5000);

        app.routes(() -> {
            get("/hello", ctx -> ctx.result("Hello World"));
            path("/api", () -> {
                get("/arrangementer", ctx -> {
                    log.info("Henter alle arrangementer");
                    ctx.result("Arrangementer" + getAlleArrangementer());
                });
                path("/arrangement", () -> {
                    get("/navn", ctx -> {
                        log.info("Henter arrangementinfo");
                        String overskrift = "Grisebakken Opp Lørdag 21. september 2019 ";
                        ctx.result("Arrangement" + getArrangementFraNavn(overskrift));
                    });
                    get("/fylke", ctx -> {
                        log.info("Henter arrangementinfo i et fylke");
                        String fylke = "Oslo ";
                        ctx.result("Arrangementer" + getArrangementIFylke(fylke));
                    });
                });
            });
        });
    }

    private static String getAlleArrangementer() {
        Gson gson = new Gson();
        Database database = new Database();
        java.util.List<Arrangement> arrangementer = database.getAlleArrangementer(sql2o);
        return gson.toJson(arrangementer);
    }

    public static String getArrangementFraNavn(String overskrift) {
        Gson gson = new Gson();
        Database database = new Database();
        java.util.List<Arrangement> arrangementer = database.getArrangementsFraNavn(sql2o, overskrift);
        return gson.toJson(arrangementer.get(0));
    }

    public static String getArrangementIFylke(String fylke) {
        Gson gson = new Gson();
        Database database = new Database();
        java.util.List<Arrangement> arrangementer = database.getArrangementsIFylke(sql2o, fylke);
        return gson.toJson(arrangementer);
    }

    private static void initLogging() {
        boolean coralogixEnabled =
                Option.of(System.getenv("CORALOGIX_ENABLED"))
                        .exists(s -> Boolean.valueOf(s));

        List<Appender<ILoggingEvent>> appenders = List.empty();
        if (coralogixEnabled) {
            appenders = appenders.prepend(LogbackConfigurer
                    .coralogixAppender(
                            CORALOGIX_COMPANY,
                            System.getenv("CORALOGIX_PRIVATE_KEY"),
                            System.getenv("CORALOGIX_ENVIRONMENT"),
                            CORALOGIX_SUBSYSTEM_NAME
                    ));
        }

        LogbackConfigurer.init(
                Level.INFO,
                List.of(Tuple.of("org.http4s", Level.WARN),
                        Tuple.of("cargojobs.Scheduler", Level.INFO),
                        Tuple.of("elasticsearch", Level.WARN)
                ).toMap(t -> t),
                appenders
        );
    }

/*    @NotNull
    private static HikariDataSource createDataSource() {
        return HikariHelper.createDataSource(
                System.getenv("DATABASE_URL"),
                System.getenv("DATABASE_USER"),
                System.getenv("DATABASE_PASSWORD")
        );
    }*/

    @NotNull
    private static HikariDataSource createDataSource() {
        return HikariHelper.createDataSource(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres"
        );
    }
}
