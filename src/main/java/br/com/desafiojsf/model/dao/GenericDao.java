package br.com.desafiojsf.model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author decio
 */
@Stateless
public class GenericDao<T> implements InterfaceDao<T> {
    @PersistenceContext
    protected EntityManager em;
    
    public GenericDao(){}
       
    public void persist(T bean) throws Exception {
        em.persist(bean);
    }

    public T merge(T bean) throws Exception {
        return em.merge(bean);
    }

    public void excluir(T bean) throws Exception {
        em.remove(em.merge(bean));
    }
    
    public void excluir(T bean, Integer id) throws Exception {
        em.remove(em.getReference(bean.getClass(), id));
    }
   
    public void excluir(Class<T> clazz, Integer id) throws Exception {
        Query query = em.createQuery("DELETE FROM " + clazz.getName() + " o WHERE o.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
    
    public T getBean(Integer id, Class<T> clazz) throws Exception {
        return em.find(clazz, id);
    }
    
    public List<T> getBeans(Class<T> clazz) throws Exception {
        CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(clazz);
        Root<T> bean = criteria.from(clazz);
        criteria.select(bean);
        return em.createQuery(criteria).getResultList();
    }

    @Override
    public void clear() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
