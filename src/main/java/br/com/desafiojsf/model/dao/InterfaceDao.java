/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafiojsf.model.dao;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author decio
 */
@Local
public interface InterfaceDao<T> {

    void clear() throws Exception;

    void persist(T bean) throws Exception;

    T merge(T bean) throws Exception;

    void excluir(T bean) throws Exception;

    void excluir(T bean, Integer id) throws Exception;

    void excluir(Class<T> clazz, Integer id) throws Exception;
    
    List<T> getBeans(Class<T> clazz) throws Exception;

}
