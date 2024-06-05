package com.github.EvgeniyaZz.bank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.EvgeniyaZz.bank.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@ToString(callSuper = true, exclude = {"account", "phones", "mails"})
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity implements HasId {

    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "middlename", nullable = false)
    private String middlename;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @NotEmpty
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference(value = "user-phone")
    private List<Phone> phones;

    @NotEmpty
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference(value = "user-mail")
    private List<Mail> mails;

    public User(String firstname, String lastname, String middlename, LocalDate birthDate) {
        this.firstname=firstname;
        this.lastname=lastname;
        this.middlename=middlename;
        this.birthDate=birthDate;
    }

    public User(String firstname, String lastname, String middlename, LocalDate birthDate, Account account) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthDate = birthDate;
        this.account = account;
    }

    public User(int id, String firstname, String lastname, String middlename, LocalDate birthDate, Account account) {
        super(id);
        this.firstname=firstname;
        this.lastname=lastname;
        this.middlename=middlename;
        this.birthDate=birthDate;
        this.account = account;
    }
}