package com.assignment.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 10)
    private int id;

    @Size(min = 4, max = 30, message = "{Size.account.username}")
    @NotEmpty(message = "{NotEmpty.account.username}")
    @Column(nullable = false, length = 255)
    private String username;

    // @Pattern(regexp="^(?=.*\\d)[a-z\\d]{8,16}+") 8-16 kí tự, có số + không viết
    // hoa
    @Size(min = 6, message = "{Size.account.password}")
    @NotEmpty(message = "{NotEmpty.account.password}")
    @Column(nullable = false, length = 255)
    private String password;

    @Size(max = 255, message = "{Size.account.fullname}")
    @NotEmpty(message = "{NotEmpty.account.fullname}")
    @Column(nullable = false, length = 255)
    private String fullname;

    @NotNull(message = "{NotNull.account.gender}")
    @Min(value = 0, message = "{NotBlank.account.gender}")
    @Max(value = 2, message = "{NotBlank.account.gender}")
    @Column(nullable = false, precision = 10)
    private int gender;

    @Size(max = 255, message = "{Size.account.email}")
    @NotEmpty(message = "{NotEmpty.account.email}")
    @Email
    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 255)
    private String image;
    
    @Column(length = 255)
    private String token;
    
    @Column(length = 255)
    private String verifyCode;

    @Column(nullable = false, precision = 10)
    private boolean activated;

    @Column(nullable = false, precision = 10)
    private int admin;

}
