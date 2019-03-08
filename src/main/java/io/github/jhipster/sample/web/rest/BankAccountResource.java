package io.github.jhipster.sample.web.rest;

import com.oracle.svm.core.annotate.Delete;
import io.github.jhipster.sample.domain.BankAccount;
import io.github.jhipster.sample.web.rest.util.HeaderUtil;
import io.github.jhipster.sample.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/api/bank-accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BankAccountResource {

  private final Logger log = LoggerFactory.getLogger(BankAccountResource.class);

  private static final String ENTITY_NAME = "bankAccount";

  @Context
  HttpHeaders headers;

  @POST
  public Response createBankAccount(BankAccount bankAccount, @Context UriInfo uriInfo) {
    if (bankAccount.id != null) {
//      throw new BadRequestAlertException("A new bankAccount cannot already have an ID", ENTITY_NAME, "idexists");
    }
    bankAccount.persist();
    URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(bankAccount.id)).build();
    return Response.created(uri).entity(bankAccount).build();
  }


  @PUT
  public Response updateBankAccount(@Valid BankAccount bankAccount) throws URISyntaxException {
    log.debug("REST request to update BankAccount : {}", bankAccount);
    if (bankAccount.id == null) {
//      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    bankAccount.persist();
    headers.getRequestHeaders().putAll(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bankAccount.id.toString()));
    return Response.ok(bankAccount).build();
  }

  @GET
  public long getAllBankAccounts() {
    log.debug("REST request to get all BankAccounts");
    return BankAccount.count();
  }

  @GET
  @Path("{id}")
  public Response getBankAccount(@PathParam("id") Long id) {
    log.debug("REST request to get BankAccount : {}", id);
    BankAccount bankAccount = BankAccount.findById(id);
    return ResponseUtil.wrapOrNotFound(bankAccount);
  }

  @Delete
  @Path("{id}")
  public Response deleteBankAccount(@PathParam("id") Long id) {
    log.debug("REST request to delete BankAccount : {}", id);
    BankAccount.findById(id).delete();
    headers.getRequestHeaders().putAll(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()));
    return Response.ok().build();
  }
}
