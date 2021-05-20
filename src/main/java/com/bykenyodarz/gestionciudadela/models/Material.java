package com.bykenyodarz.gestionciudadela.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "materiales", uniqueConstraints = {
        @UniqueConstraint(columnNames = "identificador")
})
@Data
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idMaterial;

    @NotBlank
    @Size(max = 60)
    private String nombre;

    @NotBlank
    @Size(max = 10)
    private String identificador;

    @NotNull
    private Integer stock;

}
