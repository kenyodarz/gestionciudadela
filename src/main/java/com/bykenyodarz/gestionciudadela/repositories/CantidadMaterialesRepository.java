package com.bykenyodarz.gestionciudadela.repositories;

import com.bykenyodarz.gestionciudadela.models.CantidadMateriales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CantidadMaterialesRepository extends JpaRepository<CantidadMateriales, String> {
}
