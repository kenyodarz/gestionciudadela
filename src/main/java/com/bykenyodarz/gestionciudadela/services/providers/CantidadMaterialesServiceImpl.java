package com.bykenyodarz.gestionciudadela.services.providers;

import com.bykenyodarz.gestionciudadela.models.CantidadMateriales;
import com.bykenyodarz.gestionciudadela.repositories.CantidadMaterialesRepository;
import com.bykenyodarz.gestionciudadela.services.apis.CantidadMaterialesServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CantidadMaterialesServiceImpl extends GenericServiceImpl<CantidadMateriales, String> implements
        CantidadMaterialesServiceAPI{

    private final CantidadMaterialesRepository repository;

    public CantidadMaterialesServiceImpl(CantidadMaterialesRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<CantidadMateriales, String> getJpaRepository() {
        return this.repository;
    }
}
