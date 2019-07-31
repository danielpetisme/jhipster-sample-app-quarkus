package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.domain.Label;
import io.github.jhipster.sample.web.rest.util.HeaderUtil;
import io.github.jhipster.sample.web.rest.util.ResponseUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/api/labels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LabelResource {

    private final Logger log = LoggerFactory.getLogger(LabelResource.class);

    private static final String ENTITY_NAME = "label";

    @ConfigProperty(name = "application.name")
    String applicationName;

    @Context
    HttpHeaders headers;

    /**
     * {@code POST  /labels} : Create a new label.
     *
     * @param label the label to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new label, or with status {@code 400 (Bad Request)} if the label has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @POST
    public Response createLabel(@Valid Label label, @Context UriInfo uriInfo) {
        log.debug("REST request to save Label : {}", label);
        if (label.id != null) {
//            throw new BadRequestAlertException("A new label cannot already have an ID", ENTITY_NAME, "idexists");
        }
        label.persist();
        headers.getRequestHeaders().putAll(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, label.id.toString()));
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(label.id)).build();
        return Response.created(uri).entity(label).build();
    }

    /**
     * {@code PUT  /labels} : Updates an existing label.
     *
     * @param label the label to update.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the updated label,
     * or with status {@code 400 (Bad Request)} if the label is not valid,
     * or with status {@code 500 (Internal Server Error)} if the label couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PUT
    public Response updateLabel(@Valid Label label) throws URISyntaxException {
        log.debug("REST request to update Label : {}", label);
        if (label.id == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        label.persist();
        headers.getRequestHeaders().putAll(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, label.id.toString()));
        return Response.ok().entity(label).build();
    }

    /**
     * {@code GET  /labels} : get all the labels.
     *
     * @return the {@link Response} with status {@code 200 (OK)} and the list of labels in body.
     */
    @GET
    public List<Label> getAllLabels() {
        log.debug("REST request to get all Labels");
        return Label.findAll().list();
    }

    /**
     * {@code GET  /labels/:id} : get the "id" label.
     *
     * @param id the id of the label to retrieve.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the label, or with status {@code 404 (Not Found)}.
     */
    @GET
    @Path("{id}")
    public Response getLabel(@PathParam("id") Long id) {
        log.debug("REST request to get Label : {}", id);
        Label label = Label.findById(id);
        return ResponseUtil.wrapOrNotFound(label);
    }

    /**
     * {@code DELETE  /labels/:id} : delete the "id" label.
     *
     * @param id the id of the label to delete.
     * @return the {@link Response} with status {@code 204 (NO_CONTENT)}.
     */
    @DELETE
    @Path("{id}")
    public Response deleteLabel(@PathParam("id") Long id) {
        log.debug("REST request to delete Label : {}", id);
        Label.findById(id).delete();
        headers.getRequestHeaders().putAll(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()));
        return Response.noContent().build();
    }
}
