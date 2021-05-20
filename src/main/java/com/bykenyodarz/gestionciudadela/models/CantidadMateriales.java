package com.bykenyodarz.gestionciudadela.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cantidad_materiales")
@Data
public class CantidadMateriales {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String cantidad;

    @Column(name = "ce")
    @NotNull
    public Integer Ce;

    @Column(name = "gr")
    @NotNull
    public Integer Gr;

    @Column(name = "ar")
    @NotNull
    public Integer Ar;

    @Column(name = "ma")
    @NotNull
    public Integer  Ma;

    @Column(name = "ad")
    @NotNull
    public Integer Ad;

}
