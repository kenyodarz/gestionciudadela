package com.bykenyodarz.gestionciudadela.repositories;

import com.bykenyodarz.gestionciudadela.models.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, String> {

    Boolean existsByCoordenadaXAndCoordenadaY(double coordinateX, double coordinateY);

    @Query("select o from Orden o where o.estado <>'Finalizado'")
    List<Orden> findAllByEstado();
}
