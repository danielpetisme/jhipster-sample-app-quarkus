package io.github.jhipster.sample.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;

public final class HeaderUtil {

  private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

  private static final String APPLICATION_NAME = "jhipsterSampleApplicationApp";

  private HeaderUtil() {
  }


  private static Map<String, List<String>> createAlert(String message, String param) {
    Map<String, List<String>> headers = new HashMap<>();
    headers.put("X-" + APPLICATION_NAME + "-alert", singletonList(message));
    headers.put("X-" + APPLICATION_NAME + "-params", singletonList(param));
    return headers;
  }

  public static Map<String, List<String>> createEntityCreationAlert(String entityName, String param) {
    return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
  }

  public static Map<String, List<String>> createEntityUpdateAlert(String entityName, String param) {
    return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
  }


  public static Map<String, List<String>> createEntityDeletionAlert(String entityName, String param) {
    return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
  }

  public static Map<String, List<String>> createFailureAlert(String entityName, String errorKey, String defaultMessage) {
    log.error("Entity processing failed, {}", defaultMessage);
    Map<String, List<String>> headers = new HashMap<>();
    headers.put("X-" + APPLICATION_NAME + "-error", singletonList("error." + errorKey));
    headers.put("X-" + APPLICATION_NAME + "-params", singletonList(entityName));
    return headers;
  }
}
