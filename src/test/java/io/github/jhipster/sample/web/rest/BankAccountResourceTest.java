package io.github.jhipster.sample.web.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BankAccountResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/bank-accounts")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}