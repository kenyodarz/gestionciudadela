package com.bykenyodarz.gestionciudadela.shared;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericServiceImpl<E, V extends Serializable> implements GenericServiceAPI<E, V> {

    @Override
    public E save(E entity) {
        return getJpaRepository().save(entity);
    }

    @Override
    public void delete(V id) {
        getJpaRepository().deleteById(id);
    }

    @Override
    public E get(V id) {
        // Optional
        Optional<E> optional = getJpaRepository().findById(id);
        // En caso de no encontrar el valor retornamos null
        return optional.orElse(null);
    }

    @Override
    public List<E> getAll() {
        return getJpaRepository().findAll();
    }

}
