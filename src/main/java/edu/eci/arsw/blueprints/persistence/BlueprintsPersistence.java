/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Tour;
import edu.eci.arsw.blueprints.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
public interface BlueprintsPersistence {
    
    /**
     * 
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public void saveUser(Usuario u) throws BlueprintPersistenceException;
    
    /**
     * 
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Usuario getUser(String username) throws BlueprintNotFoundException;
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Tour getTour(String name, String username) throws BlueprintNotFoundException;
    
    /**
     * Regresa todos los blueprints hasta el momento
     * @return un conjunto con todos los blueprints hasta el momento
     */
    public List<Tour> getAllTours();
    
    public void SaveTour(Tour t);
    
    public void editUser(String username,String correo, Long telefono, String foto) throws BlueprintPersistenceException;
        
    public void ComprarTour(Tour t,String username);
}
