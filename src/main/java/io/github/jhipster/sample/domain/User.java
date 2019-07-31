package io.github.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jhipster.sample.config.Constants;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A user.
 */
@Entity
@Table(name = "jhi_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends PanacheEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    public String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    public String password;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    public String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    public String lastName;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    public String email;

    @NotNull
    @Column(nullable = false)
    public boolean activated = false;

    @Size(min = 2, max = 10)
    @Column(name = "lang_key", length = 10)
    public String langKey;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    public String imageUrl;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    public String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    public String resetKey;

    @Column(name = "reset_date")
    public Instant resetDate = null;

    /*@JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "jhi_user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    public Set<Authority> authorities = new HashSet<>();*/
}
