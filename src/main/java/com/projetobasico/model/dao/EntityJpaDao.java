package com.projetobasico.model.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe Abastrata DAO genérica
 */
public abstract class EntityJpaDao<PK, T> {
	//informar o nome da unidade de persistencia 
	//criada no persistence.xml
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
    
    /**
     * Busca uma entidade pelo ID
     * @param entity
     */
    public T  getById(PK pk) {
        return (T) entityManager.find(getTypeClass(), pk);
    }
    
    /**
     * Insere uma entidade
     * @param entity
     */
    protected void insert(T entity) {
        entityManager.persist(entity);
    }
 
    /**
     * Atualiza uma entidade
     * @param entity
     */
    protected void update(T entity) {
        entityManager.merge(entity);
    }
 
    /**
     * Exclui uma entidade
     * @param entity
     */
    public void delete(T entity) {
        begin();
    	entityManager.remove(entity);
    	commit();
    }

    /**
     * Retorna a lista de elementos
     */
    public List<T> findAll() {
        return entityManager.createQuery(("FROM " + getTypeClass().getName()))
                .getResultList();
    }


    /**
     * Retorna a classe da entidade
     */
    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
    
}

