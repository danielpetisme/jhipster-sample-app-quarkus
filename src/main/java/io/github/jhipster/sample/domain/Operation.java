package io.github.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "operation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Operation extends PanacheEntity {

  @NotNull
  @Column(name = "jhi_date", nullable = false)
  public Instant date;

  @Column(name = "description")
  public String description;

  @NotNull
  @Column(name = "amount", precision = 10, scale = 2, nullable = false)
  public BigDecimal amount;

  @ManyToOne
  @JsonIgnoreProperties("operations")
  public BankAccount bankAccount;

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @JoinTable(name = "operation_label",
    joinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "label_id", referencedColumnName = "id"))
  public Set<Label> labels = new HashSet<>();

}
