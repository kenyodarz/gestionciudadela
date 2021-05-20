package com.bykenyodarz.gestionciudadela.services.providers;

import com.bykenyodarz.gestionciudadela.models.Orden;
import com.bykenyodarz.gestionciudadela.repositories.OrdenRepository;
import com.bykenyodarz.gestionciudadela.services.apis.OrdenServiceAPI;
import com.bykenyodarz.gestionciudadela.shared.GenericServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdenServiceImpl extends GenericServiceImpl<Orden, String> implements OrdenServiceAPI {

    private final OrdenRepository repository;

    public OrdenServiceImpl(OrdenRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Orden, String> getJpaRepository() {
        return this.repository;
    }
}
