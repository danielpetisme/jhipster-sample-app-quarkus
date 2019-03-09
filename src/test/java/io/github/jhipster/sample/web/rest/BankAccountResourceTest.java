package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.domain.BankAccount;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BankAccountResourceTest {

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


  @Test
  public void createBankAccount() {
    long databaseSizeBeforeCreate = BankAccount.count();

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
    assertThat(bankAccountList).hasSize((int) (databaseSizeBeforeCreate + 1));
    BankAccount testBankAccount = bankAccountList.get((int) (bankAccountList.size() - 1L));
    assertThat(testBankAccount.name).isEqualTo(DEFAULT_NAME);
    assertThat(testBankAccount.balance).isEqualTo(DEFAULT_BALANCE);
  }

}
