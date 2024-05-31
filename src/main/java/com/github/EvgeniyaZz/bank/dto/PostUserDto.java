package com.github.EvgeniyaZz.bank.dto;

import com.github.EvgeniyaZz.bank.HasId;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@ToString(callSuper = true, exclude = "password")
@EqualsAndHashCode(callSuper = true)
public class PostUserDto extends BaseDto implements HasId {

    @NotBlank
    @Size(min = 2, max = 20)
    String firstname;

    @NotBlank
    @Size(min = 2, max = 30)
    String lastname;

    @NotBlank
    @Size(min = 2, max = 30)
    String middlename;

    @NotNull
    LocalDate birthDate;

    @NotBlank
    @Size(min = 2, max = 32)
    String login;

    @NotBlank
    @Size(min = 5, max = 32)
    String password;

    @NotBlank
    @Size(min = 7, max = 16)
    String number;

    @NotBlank
    @Size(max = 100)
    @Email
    String email;

    int depositMoney;

    public PostUserDto(Integer id, String firstname, String lastname, String middlename, LocalDate birthDate,
                       String login, String password, String number, String email, int depositMoney) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.number = number;
        this.email = email;
        this.depositMoney = depositMoney;
    }
}
