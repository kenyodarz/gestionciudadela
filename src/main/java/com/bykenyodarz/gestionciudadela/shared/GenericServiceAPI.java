package com.bykenyodarz.gestionciudadela.shared;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface GenericServiceAPI<E, ID extends Serializable> {

    E save(E entity);

    void delete(ID id);

    E get(ID id);

    List<E> getAll();

    JpaRepository<E, ID> getJpaRepository();

}
