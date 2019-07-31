package io.github.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A BankAccount.
 */
@Entity(name = "bankAccount")
@Table(name = "bank_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BankAccount extends PanacheEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "name", nullable = false)
    public String name;

    @NotNull
    @Column(name = "balance", precision = 21, scale = 2, nullable = false)
    public BigDecimal balance;

    /*@ManyToOne
    @JsonIgnoreProperties("bankAccounts")
    public User user;

    @OneToMany(mappedBy = "bankAccount")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public Set<Operation> operations = new HashSet<>();
*/
}
