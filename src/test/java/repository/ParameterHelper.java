package repository;

import io.vavr.control.Option;

public class ParameterHelper {
    public ParameterHelper() {
    }

    public static String getOptionalParameter(String parameterName, String defaultValue) {
        return (String)Option.of(System.getenv(parameterName)).getOrElse(defaultValue);
    }

    public static String getRequiredParameter(String parameterName) {
        return (String)Option.of(System.getenv(parameterName)).getOrElseThrow(() -> {
            return new RuntimeException(String.format("Missing required parameter %s", parameterName));
        });
    }
}