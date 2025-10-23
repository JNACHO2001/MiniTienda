package com.mycompany.main.repository;

import java.util.List;

public interface Repositorio<T, ID> {

    T crear(T t);

    T buscarPorId(ID id);

    List<T> buscarTodos();

    T actualizar(T t);

    void eliminar(ID id);

}
