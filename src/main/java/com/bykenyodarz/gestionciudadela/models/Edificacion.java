package com.bykenyodarz.gestionciudadela.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "edificaciones", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre")
})
@Data
public class Edificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idEdificacion;

    @Column
    @NotNull
    private Integer cantidadDias;

    @Column
    @NotBlank
    private String nombre;
}

