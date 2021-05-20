package com.bykenyodarz.gestionciudadela.services.apis;

import com.bykenyodarz.gestionciudadela.models.Orden;
import com.bykenyodarz.gestionciudadela.shared.GenericServiceAPI;

public interface OrdenServiceAPI extends GenericServiceAPI<Orden, String> {
    Boolean existsByCoordenadaXAndCoordenadaY(double coordinateX, double coordinateY);
}
