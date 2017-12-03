/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Tour;
import edu.eci.arsw.blueprints.model.Usuario;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */

@RestController
@RequestMapping(value = "/traceco")
public class BlueprintAPIController {
    @Autowired BlueprintsServices bps = null;
    
    @RequestMapping(path = "/users",method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody Usuario u){
        try {
            bps.saveUser(u);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable String username){
        try {
            return new ResponseEntity<>(bps.getUser(username),HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    
    @RequestMapping(path = "/tours/{name}/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getTour(@PathVariable String name,@PathVariable String username){
        try {

            return new ResponseEntity<>(bps.getTour(name, username),HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path = "/tours", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTours(){
        try {
            return new ResponseEntity<>(bps.getAllTours(),HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path = "/tours",method = RequestMethod.POST)
    public ResponseEntity<?> SaveTour(@RequestBody Tour t){

        try {
            bps.SaveTour(t) ;
          return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        
    }
    
    
    @RequestMapping(path = "/users/edit",method = RequestMethod.POST)
    public ResponseEntity<?> editUser(@RequestBody Usuario u){
        try {
            bps.editUser(u) ;
          return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        
    }
    
    @RequestMapping(path = "/users/edit/{username}",method = RequestMethod.POST)
    public ResponseEntity<?> ComprarTour(@RequestBody Tour t,@PathVariable String username){
        try {
            
            bps.ComprarTour(t, username);
          return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        
    }

}



