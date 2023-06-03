package br.ifsp.techmaps.usecases.util;

import java.util.List;
import java.util.Optional;

public interface DAO <T, K> {
        T create(T type);

        Optional<T> findOne(K  key);

        List<T> findALL();

        T update(T type);

        T delete(T type);

        T deleteByKey(K key);

}
