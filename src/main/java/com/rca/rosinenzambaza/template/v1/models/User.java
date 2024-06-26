package com.rca.rosinenzambaza.template.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rca.rosinenzambaza.template.v1.enums.EUserStatus;
import com.rca.rosinenzambaza.template.v1.enums.EVisibility;
import com.rca.rosinenzambaza.template.v1.utils.ExpirationTokenUtils;
import com.rca.rosinenzambaza.template.v1.utils.Utility;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "email" , nullable = false)//    @Email
    private String email;

    @Column(name = "password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must be strong")
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    private String firstName;

    private String lastName;

    @Column(name = "activation_code", nullable = false)
    private String activationCode = Utility.randomUUID(6, 0, 'N');

    private String expirationToken = ExpirationTokenUtils.generateToken();

    @Column(name = "status", nullable = false)
    private EUserStatus accountStatus = EUserStatus.WAIT_EMAIL_VERIFICATION;

    @Column(name = "visibilty", nullable = false)
    @JsonIgnore
    private EVisibility visibility = EVisibility.VISIBLE;

    @Column(name = "profile_picture", nullable = true)
    private String profilePicture = null;

    //    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();
}
