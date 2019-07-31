package io.github.jhipster.sample.web.rest.util;

import javax.ws.rs.core.Response;

public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static Response wrapOrNotFound(Object object) {
        if(object != null) {
            return Response.ok(object).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
