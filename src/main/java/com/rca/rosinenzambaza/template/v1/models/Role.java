package com.rca.rosinenzambaza.template.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles" , uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    public UUID roleId;

    @Column
    @NotNull
    public String roleName;

    @Column
    public String code;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles" , fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    public Role (String roleName){
        this.roleName = roleName;
    }
}
