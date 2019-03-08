package io.github.jhipster.sample.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bank_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BankAccount extends PanacheEntity {

  @NotNull
  @Column(name = "name", nullable = false)
  public String name;

  @NotNull
  @Column(name = "balance", precision = 10, scale = 2, nullable = false)
  public BigDecimal balance;

  @OneToMany(mappedBy = "bankAccount")
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  public Set<Operation> operations = new HashSet<>();

}
