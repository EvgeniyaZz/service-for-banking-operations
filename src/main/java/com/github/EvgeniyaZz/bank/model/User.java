package com.github.EvgeniyaZz.bank.model;

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
    @Size(min = 2, max = 128)
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "middlename", nullable = false)
    private String middlename;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;

    @NotEmpty
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Phone> phones;

    @NotEmpty
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Mail> mails;
}