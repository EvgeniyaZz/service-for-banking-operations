package com.github.EvgeniyaZz.bank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {

    @NotBlank
    @Size(min = 2, max = 32)
    String login;

    @NotBlank
    @Size(min = 5, max = 32)
    String password;
}
