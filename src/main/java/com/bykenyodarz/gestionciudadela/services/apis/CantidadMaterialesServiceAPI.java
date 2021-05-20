package com.bykenyodarz.gestionciudadela.services.apis;

import com.bykenyodarz.gestionciudadela.models.CantidadMateriales;
import com.bykenyodarz.gestionciudadela.shared.GenericServiceAPI;

import java.util.List;

public interface CantidadMaterialesServiceAPI extends GenericServiceAPI<CantidadMateriales, String> {
    List<CantidadMateriales> findByEdificacionAndMaterial(String nombre, String abreviatura);
    List<CantidadMateriales> findByEdificacion(String nombre);
}
