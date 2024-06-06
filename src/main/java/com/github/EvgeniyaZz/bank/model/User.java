package com.github.EvgeniyaZz.bank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.EvgeniyaZz.bank.HasId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@ToString(callSuper = true, exclude = {"password", "userDetail", "account", "phones", "mails"})
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity implements HasId {

    @NotBlank
    @Size(min = 2, max = 32)
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotBlank
    @Size(min = 5, max = 128)
    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
    private UserDetail userDetail;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference(value = "user-phone")
    private List<Phone> phones;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference(value = "user-mail")
    private List<Mail> mails;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = EnumSet.noneOf(Role.class);

    public void setRoles(Collection<Role> roles) {
        this.roles = roles.isEmpty() ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, UserDetail userDetail, Account account, Role... roles) {
        this(null, login, password, userDetail, account, List.of(roles));
    }

    public User(Integer id, String login, String password, UserDetail userDetail, Account account, Collection<Role> roles) {
        super(id);
        this.login = login;
        this.password = password;
        this.userDetail = userDetail;
        this.account = account;
        setRoles(roles);
    }
}