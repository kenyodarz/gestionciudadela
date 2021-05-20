package com.bykenyodarz.gestionciudadela.repositories;

import com.bykenyodarz.gestionciudadela.models.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, String> {

    Boolean existsByCoordenadaXAndCoordenadaY(double coordinateX, double coordinateY);
}