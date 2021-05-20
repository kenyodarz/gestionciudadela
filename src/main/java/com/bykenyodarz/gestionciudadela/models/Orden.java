package com.bykenyodarz.gestionciudadela.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"coordenadax", "coordenaday"})
})
@Data
public class Orden {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idOrden;

    @Column
    @NotNull
    private Double coordenadaX;

    @Column
    @NotNull
    private Double coordenadaY;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String estado;

    @OneToOne
    private Edificacion edificacion;

    @Transient
    private Boolean isActive;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    public Boolean getActive() {
        return (LocalDateTime.now().plusDays(edificacion.getCantidadDias()).isAfter(createdAt));
    }
}
