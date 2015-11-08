/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.diststudy.dao;

/**
 * @author 
 */

import javaee.diststudy.entity.Teacher;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;


public class TeacherManager implements EntityDAO<Teacher> {

    //@PersistenceContext(unitName = "javaee")
    private EntityManager entityManager = Persistence.createEntityManagerFactory("javaee").createEntityManager();
        
    @Override
    public void create(Teacher entity) throws SQLException {
        int newId = generateId();
        entity.setId(newId);
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist( entity );
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new SQLException( e );
        }

    }

    @Override
    public Teacher getById(int id) throws SQLException {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    public void update( Teacher entity) throws SQLException {
        
        try {
            entityManager.getTransaction().begin();
            entityManager.merge( entity );
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new SQLException( e );
        }

    }

    @Override
    public boolean delete(int id) throws SQLException {
        
        try {
            entityManager.getTransaction().begin();
            entityManager.remove( getById( id ) );
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new SQLException( e );
        }
        
        return true;
    }
    

    @Override
    public List<Teacher> getAll() throws SQLException {
//        String query = "select  c.id, c.first_name, c.second_name, c.last_name, c.birthday, c.degree, c.kafedra"
//                + " from " + Teacher.class.getSimpleName() + " c ";
        String query = "select  c "
                + " from " + Teacher.class.getSimpleName() + " c ";
        
        return entityManager.createQuery( query )
                .getResultList();
    }
    
     public List<Teacher> getByDegree( int degr ) throws SQLException {
        Query query = entityManager.createQuery("SELECT e FROM "+Teacher.class.getSimpleName() +" e WHERE e.degree = :first");
        query.setParameter("first", degr);
        
        return query.getResultList();
    }

    public List<Teacher> getBySurname( String surname ) throws SQLException {
        Query query = entityManager.createQuery("SELECT e FROM "+ Teacher.class.getSimpleName() +" as e  WHERE e.lastName like :secondName");
        query.setParameter("secondName", surname+"%");
        List<Teacher> list = query.getResultList();
        return list;
    }
     
    public List getByName( String name ) throws SQLException {
      
        return entityManager.createNamedQuery("Teacher.findByFirstName")
                .setParameter("firstName", name)
                .getResultList();
    }
     
    public List getByKafedra( int kafedra ) throws SQLException {
      
        return entityManager.createNamedQuery("Teacher.findByKafedra")
                .setParameter("kafedra", kafedra)
                .getResultList();
    }
    
    private int generateId () throws SQLException {
        String query = "select max( c.id ) as id from " + Teacher.class.getSimpleName() + " c ";
        int max = (int) entityManager.createQuery(query).getSingleResult();   
        
        return max + 1;
    }
    
    
 
}
