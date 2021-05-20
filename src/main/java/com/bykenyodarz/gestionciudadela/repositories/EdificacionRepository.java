package com.bykenyodarz.gestionciudadela.repositories;

import com.bykenyodarz.gestionciudadela.models.Edificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EdificacionRepository extends JpaRepository<Edificacion, String> {

    Optional<Edificacion> findByNombre(String nombre);

    Boolean existsByNombre(String nombre);

}
