package com.br.banco.dao;

import java.util.List;

/**
 *
 * @author bruce
 */
public interface GenericDAO <T> {
    
    public void insert (T t);
    
    /**
     * Leitura a partir de uma chave primária
     * @param id
     * @return 
     */
    public T readById(int id);
    
    public List<T> read();
}
