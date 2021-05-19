package com.bykenyodarz.gestionciudadela.services.providers;

import com.bykenyodarz.gestionciudadela.models.Material;
import com.bykenyodarz.gestionciudadela.repositories.MaterialRepository;
import com.bykenyodarz.gestionciudadela.services.apis.MaterialServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MaterialServiceImpl extends GenericServiceImpl<Material, String> implements MaterialServiceAPI {

    private final MaterialRepository repository;

    public MaterialServiceImpl(MaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Material, String> getJpaRepository() {
        return repository;
    }

    @Override
    @Transactional
    public Material findMaterialByWithStock(String id) {
        return this.repository.findMaterialByWithStock(id).orElse(null);
    }

    @Override
    public Material findByIdentificador(String id) {
        return this.repository.findByIdentificador(id).orElse(null);
    }

    @Override
    public Boolean existsByIdentificador(String id) {
        return this.repository.existsByIdentificador(id);
    }
}
