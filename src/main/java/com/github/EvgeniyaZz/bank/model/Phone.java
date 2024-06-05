package com.github.EvgeniyaZz.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.EvgeniyaZz.bank.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString(callSuper = true, exclude = {"user"})
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone extends AbstractBaseEntity implements HasId {

    @NotBlank
    @Size(min = 7, max = 16)
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference(value = "user-phone")
    private User user;

    public Phone(String number) {
        this.number = number;
    }

    public Phone(String number, User user) {
        this.number = number;
        this.user = user;
    }

    public Phone(Integer id, String number) {
        super(id);
        this.number = number;
    }
}
