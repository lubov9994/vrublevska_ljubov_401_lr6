/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.diststudy.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Администратор
 */
public interface EntityDAO<T> {
    public static final String NAME_UNIT = "jdbc/sample";
    
    public void create ( T entity )throws SQLException;
    public T getById ( int id ) throws SQLException;
    public void update ( T entity ) throws SQLException;
    public boolean delete ( int id ) throws SQLException;
    public List<T> getAll() throws SQLException;
 
}
