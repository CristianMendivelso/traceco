/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;


import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.model.Tour;
import edu.eci.arsw.blueprints.model.Usuario;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp = null;
   
 
     public void saveUser(Usuario u) throws BlueprintPersistenceException{
        bpp.saveUser(u);
     }
    
    
    public Usuario getUser(String username) throws BlueprintNotFoundException{
        return bpp.getUser(username);
    }
    
    
    public Tour getTour(String name, String username) throws BlueprintNotFoundException{
        return bpp.getTour(name, username);
    }
    
    
    public List<Tour> getAllTours() throws BlueprintNotFoundException{
        return bpp.getAllTours();
    }
    
    public void SaveTour(Tour t) throws BlueprintNotFoundException{
        bpp.SaveTour(t);
    }
    
    public void editUser(Usuario u) throws BlueprintPersistenceException{
        bpp.editUser(u.getUsername(), u.getCorreo(), u.getTelefono(), u.getFoto());
    }
        
    public void ComprarTour(Tour t,String username) throws BlueprintNotFoundException{
        bpp.ComprarTour(t,username);
    }
    
}