package com.bykenyodarz.gestionciudadela.shared;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface GenericServiceAPI<E, V extends Serializable> {

    E save(E entity);

    void delete(V id);

    E get(V id);

    List<E> getAll();

    JpaRepository<E, V> getJpaRepository();

}
