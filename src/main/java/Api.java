import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.zaxxer.hikari.HikariDataSource;
import io.javalin.Javalin;
import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
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
        //HikariDataSource hikariDataSource = createDataSource();

        //sql2o = new Sql2o(hikariDataSource);
        Javalin app = Javalin.create()
                .enableStaticFiles("/static")
                .start(5000);

        app.routes(() -> {
            get("/hello", ctx -> ctx.result("Hello World"));
            path("/api", () -> {
                get("/arrangementer", ctx -> ctx.result("Hent alle arrangementer"));
                path("/arrangement", () -> {
                    log.info("incoming request");
                    get(":id", ctx -> ctx.result("Hent arrangement"));
                    get("/fylke", ctx -> ctx.result("Hent arrangement i fylke " ));
                });

            });

        });
    }



    private static String saveKondisData() {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO ARRANGEMENT(id, overskrift, arrangør) values (2, 'Oslo Maraton2', 'SK Vidar' )";
            return con.createQuery(sql)
                    .executeScalar(String.class);

        }

    }

    public static String getArrangementById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT overskrift FROM arrangement where id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(String.class);
        }
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

    @NotNull
    private static HikariDataSource createDataSource() {
        return HikariHelper.createDataSource(
                System.getenv("DATABASE_URL"),
                System.getenv("DATABASE_USER"),
                System.getenv("DATABASE_PASSWORD")
        );
    }
}
