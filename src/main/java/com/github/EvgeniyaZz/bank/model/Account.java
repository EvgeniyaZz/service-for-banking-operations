package com.github.EvgeniyaZz.bank.model;

import com.github.EvgeniyaZz.bank.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString(callSuper = true, exclude = {"user", "password"})
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = "user_id", name = "user_unique_account_idx"))
public class Account extends AbstractBaseEntity implements HasId {

    @NotBlank
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotBlank
    @Size(min = 5, max = 32)
    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "depositMoney", nullable = false)
    @Range(min = 0)
    private int depositMoney;
}
