package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.domain.BankAccount;
import io.quarkus.test.junit.DisabledOnSubstrate;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.SubstrateTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class BankAccountResourceTest {

    @SubstrateTest
    public static class NativeBankAccountResourceIT extends BankAccountResourceTest {

    }

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private BankAccount bankAccount;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankAccount createEntity() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.name = DEFAULT_NAME;
        bankAccount.balance = DEFAULT_BALANCE;
        return bankAccount;
    }

    @BeforeEach
    public void initTest() {
        bankAccount = createEntity();
    }


    @DisabledOnSubstrate
    @Test
    public void createBankAccount() {
        BankAccount.listAll();
        int databaseSizeBeforeCreate = Math.toIntExact(BankAccount.count());

        // Create the BankAccount
        given()
                .contentType(ContentType.JSON)
                .body(bankAccount)
                .when()
                .post("/bank-accounts")
                .then()
                .statusCode(201);


        // Validate the BankAccount in the database
        List<BankAccount> bankAccountList = BankAccount.listAll();
        assertThat(bankAccountList).hasSize((databaseSizeBeforeCreate + 1));
        BankAccount testBankAccount = bankAccountList.get(bankAccountList.size() - 1);
        assertThat(testBankAccount.name).isEqualTo(DEFAULT_NAME);
        assertThat(testBankAccount.balance).isEqualTo(DEFAULT_BALANCE);
    }

}
