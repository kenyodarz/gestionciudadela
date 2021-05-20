package com.bykenyodarz.gestionciudadela.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cantidad_materiales", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"identificador", "nombre"})
})
@Data
public class CantidadMateriales {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idCantidad;

    @NotNull
    @OneToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador")
    private Material material;

    @NotNull
    @OneToOne
    @JoinColumn(name = "nombre", referencedColumnName = "nombre")
    private Edificacion edificacion;

    @Column
    private Integer cantidad;
}
