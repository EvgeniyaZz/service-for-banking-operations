package com.github.EvgeniyaZz.bank.model;

import com.github.EvgeniyaZz.bank.HasId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString(callSuper = true, exclude = "password")
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account extends AbstractBaseEntity implements HasId {

    @NotBlank
    @Size(min = 2, max = 32)
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotBlank
    @Size(min = 5, max = 32)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "deposit_money", nullable = false)
    @Range(min = 0)
    private int depositMoney;

    public Account(String login, String password, int depositMoney) {
        this.login = login;
        this.password = password;
        this.depositMoney = depositMoney;
    }

    public Account(Integer id, String login, String password, int depositMoney) {
        super(id);
        this.login = login;
        this.password = password;
        this.depositMoney = depositMoney;
    }
}
