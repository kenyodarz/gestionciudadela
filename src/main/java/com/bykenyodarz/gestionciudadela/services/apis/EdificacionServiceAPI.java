package com.bykenyodarz.gestionciudadela.services.apis;

import com.bykenyodarz.gestionciudadela.models.Edificacion;
import com.bykenyodarz.gestionciudadela.shared.GenericServiceAPI;

public interface EdificacionServiceAPI extends GenericServiceAPI<Edificacion, String> {
    Edificacion findByNombre(String nombre);

    Boolean existsByNombre(String nombre);
}
