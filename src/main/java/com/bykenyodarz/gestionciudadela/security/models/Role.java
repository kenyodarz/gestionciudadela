package com.bykenyodarz.gestionciudadela.security.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
