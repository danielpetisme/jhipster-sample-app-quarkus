package io.github.jhipster.sample.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil() {
    }

    /**
     * <p>createAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param message a {@link java.lang.String} object.
     * @param param a {@link java.lang.String} object.
     * @return a {@link javax.ws.rs.core.MultivaluedMap} object.
     */
    public static MultivaluedMap<String, String> createAlert(String applicationName, String message, String param) {
        MultivaluedMap<String, String> headers = new MultivaluedHashMap<>();
        headers.add("X-" + applicationName + "-alert", message);
        headers.add("X-" + applicationName + "-params", param);
        return headers;
    }

    /**
     * <p>createEntityCreationAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param enableTranslation a boolean.
     * @param entityName a {@link java.lang.String} object.
     * @param param a {@link java.lang.String} object.
     * @return a {@link javax.ws.rs.core.MultivaluedMap} object.
     */
    public static MultivaluedMap<String, String> createEntityCreationAlert(String applicationName, boolean enableTranslation, String entityName, String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".created"
                : "A new " + entityName + " is created with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    /**
     * <p>createEntityUpdateAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param enableTranslation a boolean.
     * @param entityName a {@link java.lang.String} object.
     * @param param a {@link java.lang.String} object.
     * @return a {@link javax.ws.rs.core.MultivaluedMap} object.
     */
    public static MultivaluedMap<String, String> createEntityUpdateAlert(String applicationName, boolean enableTranslation, String entityName, String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".updated"
                : "A " + entityName + " is updated with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    /**
     * <p>createEntityDeletionAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param enableTranslation a boolean.
     * @param entityName a {@link java.lang.String} object.
     * @param param a {@link java.lang.String} object.
     * @return a {@link javax.ws.rs.core.MultivaluedMap} object.
     */
    public static MultivaluedMap<String, String> createEntityDeletionAlert(String applicationName, boolean enableTranslation, String entityName, String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".deleted"
                : "A " + entityName + " is deleted with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    /**
     * <p>createFailureAlert.</p>
     *
     * @param applicationName a {@link java.lang.String} object.
     * @param enableTranslation a boolean.
     * @param entityName a {@link java.lang.String} object.
     * @param errorKey a {@link java.lang.String} object.
     * @param defaultMessage a {@link java.lang.String} object.
     * @return a {@link javax.ws.rs.core.MultivaluedMap} object.
     */
    public static MultivaluedMap<String, String> createFailureAlert(String applicationName, boolean enableTranslation, String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);

        String message = enableTranslation ? "error." + errorKey : defaultMessage;

        MultivaluedMap<String, String> headers = new MultivaluedHashMap<>();
        headers.add("X-" + applicationName + "-error", message);
        headers.add("X-" + applicationName + "-params", entityName);
        return headers;
    }
}
