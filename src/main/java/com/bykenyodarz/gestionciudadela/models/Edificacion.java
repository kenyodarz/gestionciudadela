package com.bykenyodarz.gestionciudadela.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "edificaciones", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre")
})
@Data
public class Edificacion {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idEdificacion;

    @Column
    private Integer cantidadDias;

    @Column
    private String nombre;

    @OneToOne
    @JoinColumn(name = "cantidad")
    private CantidadMateriales materiales;
}

