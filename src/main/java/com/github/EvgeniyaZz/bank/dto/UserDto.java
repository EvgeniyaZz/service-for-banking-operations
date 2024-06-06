package com.github.EvgeniyaZz.bank.dto;

import com.github.EvgeniyaZz.bank.HasId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Запрос на регистрацию")
public class UserDto extends BaseDto implements HasId {

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

    int account;

    public UserDto(String firstname, String lastname, String middlename, LocalDate birthDate,
                   String login, String password, String number, String email, int account) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.number = number;
        this.email = email;
        this.account = account;
    }
}
