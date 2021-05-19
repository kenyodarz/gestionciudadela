package com.bykenyodarz.gestionciudadela.services.apis;

import com.bykenyodarz.gestionciudadela.models.Material;
import com.bykenyodarz.gestionciudadela.shared.GenericServiceAPI;

public interface MaterialServiceAPI extends GenericServiceAPI<Material, String> {
    Material findMaterialByWithStock(String id);

    Material findByIdentificador(String id);
}
