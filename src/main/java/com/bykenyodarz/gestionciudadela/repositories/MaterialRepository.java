package com.bykenyodarz.gestionciudadela.repositories;

import com.bykenyodarz.gestionciudadela.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {
    @Query("select m from Material m where m.stock > 0 and m.identificador = ?1")
    Optional<Material> findMaterialByWithStock(String id);

    Optional<Material> findByIdentificador(String id);
}
