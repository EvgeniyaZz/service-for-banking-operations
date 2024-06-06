package com.github.EvgeniyaZz.bank.model;

import com.github.EvgeniyaZz.bank.HasId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_detail")
public class UserDetail extends AbstractBaseEntity implements HasId {

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

    public UserDetail(String firstname, String lastname, String middlename, LocalDate birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthDate = birthDate;
    }

    public UserDetail(int id, String firstname, String lastname, String middlename, LocalDate birthDate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthDate = birthDate;
    }
}
