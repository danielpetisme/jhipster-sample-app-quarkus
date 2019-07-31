package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.domain.BankAccount;
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

@Path("/api/bank-accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BankAccountResource {

    private final Logger log = LoggerFactory.getLogger(BankAccountResource.class);

    private static final String ENTITY_NAME = "bankAccount";

    @ConfigProperty(name = "application.name")
    String applicationName;

    @Context
    HttpHeaders headers;

    /**
     * {@code POST  /bank-accounts} : Create a new bankAccount.
     *
     * @param bankAccount the bankAccount to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new bankAccount, or with status {@code 400 (Bad Request)} if the bankAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @POST
    public Response createBankAccount(@Valid BankAccount bankAccount, @Context UriInfo uriInfo) {
        log.debug("REST request to save BankAccount : {}", bankAccount);
        if (bankAccount.id != null) {
//            throw new BadRequestAlertException("A new bankAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        bankAccount.persist();
        headers.getRequestHeaders().putAll(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, bankAccount.id.toString()));
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(bankAccount.id)).build();
        return Response.created(uri).entity(bankAccount).build();
    }

    /**
     * {@code PUT  /bank-accounts} : Updates an existing bankAccount.
     *
     * @param bankAccount the bankAccount to update.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the updated bankAccount,
     * or with status {@code 400 (Bad Request)} if the bankAccount is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bankAccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PUT
    public Response updateBankAccount(@Valid BankAccount bankAccount) throws URISyntaxException {
        log.debug("REST request to update BankAccount : {}", bankAccount);
        if (bankAccount.id == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        bankAccount.persist();
        headers.getRequestHeaders().putAll(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bankAccount.id.toString()));
        return Response.ok().entity(bankAccount).build();
    }

    /**
     * {@code GET  /bank-accounts} : get all the bankAccounts.
     *
     * @return the {@link Response} with status {@code 200 (OK)} and the list of bankAccounts in body.
     */
    @GET
    public List<BankAccount> getAllBankAccounts() {
        log.debug("REST request to get all BankAccounts");
        return BankAccount.findAll().list();
    }

    /**
     * {@code GET  /bank-accounts/:id} : get the "id" bankAccount.
     *
     * @param id the id of the bankAccount to retrieve.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the bankAccount, or with status {@code 404 (Not Found)}.
     */
    @GET
    @Path("{id}")
    public Response getBankAccount(@PathParam("id") Long id) {
        log.debug("REST request to get BankAccount : {}", id);
        BankAccount bankAccount = BankAccount.findById(id);
        return ResponseUtil.wrapOrNotFound(bankAccount);
    }

    /**
     * {@code DELETE  /bank-accounts/:id} : delete the "id" bankAccount.
     *
     * @param id the id of the bankAccount to delete.
     * @return the {@link Response} with status {@code 204 (NO_CONTENT)}.
     */
    @DELETE
    @Path("{id}")
    public Response deleteBankAccount(@PathParam("id") Long id) {
        log.debug("REST request to delete BankAccount : {}", id);
        BankAccount.findById(id).delete();
        headers.getRequestHeaders().putAll(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()));
        return Response.noContent().build();
    }
}
