package com.github.EvgeniyaZz.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.EvgeniyaZz.bank.HasId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@ToString(callSuper = true, exclude = "user")
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "mail")
public class Mail extends AbstractBaseEntity implements HasId {

    @NotBlank
    @Size(max = 100)
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference(value = "user-mail")
    private User user;

    public Mail(String email) {
        this.email = email;
    }

    public Mail(Integer id, String email) {
        super(id);
        this.email = email;
    }

    public Mail(String email, User user) {
        this.email = email;
        this.user = user;
    }
}