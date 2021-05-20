package com.bykenyodarz.gestionciudadela.services.providers;

import com.bykenyodarz.gestionciudadela.models.Edificacion;
import com.bykenyodarz.gestionciudadela.repositories.EdificacionRepository;
import com.bykenyodarz.gestionciudadela.services.apis.EdificacionServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EdificacionServiceImpl extends GenericServiceImpl<Edificacion, String> implements EdificacionServiceAPI {

    private final EdificacionRepository repository;

    public EdificacionServiceImpl(EdificacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Edificacion, String> getJpaRepository() {
        return this.repository;
    }

    @Override
    @Transactional
    public Edificacion findByNombre(String nombre) {
        return this.repository.findByNombre(nombre).orElse(null);
    }

    @Override
    public Boolean existsByNombre(String nombre) {
        return this.repository.existsByNombre(nombre);
    }
}
