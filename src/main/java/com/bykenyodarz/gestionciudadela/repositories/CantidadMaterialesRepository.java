package com.bykenyodarz.gestionciudadela.repositories;

import com.bykenyodarz.gestionciudadela.models.CantidadMateriales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CantidadMaterialesRepository extends JpaRepository<CantidadMateriales, String> {
    @Query("select c from CantidadMateriales c where c.edificacion.nombre = ?1 and c.material.identificador = ?2")
    List<CantidadMateriales> findByEdificacionAndMaterial(String nombre, String abreviatura);

    @Query("select c from CantidadMateriales c where c.edificacion.nombre =?1")
    List<CantidadMateriales> findAllByEdificacionNombre(String nombre);
}
