package com.projetobasico.modelDao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EntityJpaDao<PK, T> {

	private String persistenceUnitName = "projetoPU";
	
	protected EntityManager entityManager;
 
    public EntityJpaDao() {
        EntityManagerFactory factory = 
        		Persistence
        		.createEntityManagerFactory(persistenceUnitName);
    	this.entityManager = factory.createEntityManager();
    }
    
    public void commit() {
    	entityManager.getTransaction().commit();    	
    }
    
    public void begin() {
    	entityManager.getTransaction().begin();
    }
    
    public void rollback() {
    	entityManager.getTransaction().rollback();
    }
    

    public T getById(PK pk) {
        return (T) entityManager.find(getTypeClass(), pk);
    }
    
    protected void insert(T entity) {
        entityManager.persist(entity);
    }
 
    protected void update(T entity) {
        entityManager.merge(entity);
    }
 
    public void delete(T entity) {
        begin();
    	entityManager.remove(entity);
    	commit();
    }


    public List<T> findAll() {
        return entityManager.createQuery(("FROM " + getTypeClass().getName()))
                .getResultList();
    }


    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
    
}

