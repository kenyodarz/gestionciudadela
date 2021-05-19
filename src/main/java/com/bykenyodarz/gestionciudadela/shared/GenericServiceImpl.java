package com.bykenyodarz.gestionciudadela.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericServiceImpl<E, ID extends Serializable> implements GenericServiceAPI<E, ID> {

    @Override
    public E save(E entity) {
        return getJpaRepository().save(entity);
    }

    @Override
    public void delete(ID id) {
        getJpaRepository().deleteById(id);
    }

    @Override
    public E get(ID id) {
        // Optional
        Optional<E> optional = getJpaRepository().findById(id);
        // En caso de no encontrar el valor retornamos null
        return optional.orElse(null);
    }

    @Override
    public List<E> getAll() {
        return getJpaRepository().findAll();
    }

    @Override
    public abstract JpaRepository<E, ID> getJpaRepository();
}
